/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ErroSincronizando;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ProcessadorDemandaAbs;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.DemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.LinkEntidadesSistemasERP;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticContatoRest;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticMetaTags;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;

/**
 *
 * @author salvio
 */
public class MauticSincSalvarContato extends ProcessadorDemandaAbs {

    public MauticSincSalvarContato(DemandaSinc pDEmanda) {
        super(pDEmanda);
    }

    @Override
    public ContatoProspecto getEntidade() {
        return (ContatoProspecto) super.getEntidade();
    }

    /**
     * O link da empresa é gerado pelo MauticSincPessoa antes de sincronizar os
     * contatos
     */
    private String getCodigoEmpresa() throws ErroSincronizando {
        // o prospecto é um proxy lazy de Pessoa, e o link é gravado com a classe real (ex: PessoaJuridica)
        Pessoa prospecto = (Pessoa) Hibernate.unproxy(getEntidade().getProspecto());
        LinkEntidadesSistemasERP linkEmpresa = getLinkExistenteOutraEntidade(prospecto);
        if (linkEmpresa == null || UtilCRCStringValidador.isNuloOuEmbranco(linkEmpresa.getCodigoSistemaRemoto())) {
            throw new ErroSincronizando("A empresa " + getEntidade().getProspecto().getNome() + " ainda não foi sincronizada com o Mautic");
        }
        return linkEmpresa.getCodigoSistemaRemoto();
    }

    private String zeraNulo(String pValor) {
        if (pValor == null) {
            return "";
        } else {
            return pValor;
        }
    }

    /**
     * @return id do contato no Mautic com o e-mail, ou "0" se não existir
     */
    private String getIdContatoMesmoEmail(String pEmail) throws ErroSincronizando {
        ItfRespostaWebServiceSimples resposta = FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getAcao(pEmail).getResposta();
        if (resposta == null || !resposta.isSucesso()) {
            throw new ErroSincronizando("Falha pesquisando o contato " + pEmail + " - "
                    + (resposta == null ? "sem resposta" : resposta.getRespostaTexto()));
        }
        JsonObject jsonResposta = resposta.getRespostaComoObjetoJson();
        if (jsonResposta == null) {
            return "0";
        }
        // sem resultados o Mautic devolve "contacts": [] em vez de um objeto indexado pelo id
        JsonValue contatos = jsonResposta.get("contacts");
        if (contatos == null || contatos.getValueType() != JsonValue.ValueType.OBJECT
                || contatos.asJsonObject().isEmpty()) {
            return "0";
        }
        return contatos.asJsonObject().keySet().iterator().next();
    }

    @Override
    public LinkEntidadesSistemasERP sincronizar() throws ErroSincronizando {
        String contatoPrimeiroNome = zeraNulo(getEntidade().getPrimeiroNome());
        String contatoSobrenome = "";
        if (!UtilCRCStringValidador.isNuloOuEmbranco(getEntidade().getPrimeiroNome())) {
            contatoSobrenome = zeraNulo(getEntidade().getNome()).replace(getEntidade().getPrimeiroNome(), "").trim();
        }

        String contatoEmail = zeraNulo(getEntidade().getEmail());
        String contatoMobile = zeraNulo(getEntidade().getCelular());
        String idEmpresa = getCodigoEmpresa();
        String idContato = getIdContatoMesmoEmail(contatoEmail);

        // as tags são vinculadas depois, pelos endpoints de atribuir/retirar tag
        ItfRespostaWebServiceSimples respContato = FabMauticContatoRest.CONTATO_CTR_SALVAR_EDITAR_CONTATO.
                getAcao(idContato, contatoEmail, contatoPrimeiroNome,
                        contatoSobrenome, contatoMobile, null
                ).getResposta();
        if (respContato == null || !respContato.isSucesso()) {
            throw new ErroSincronizando("Falha atualizando contato " + contatoEmail + " - "
                    + (respContato == null ? "sem resposta" : respContato.getRespostaTexto()));
        }
        JsonObject jsonContato = respContato.getRespostaComoObjetoJson().getJsonObject("contact");
        idContato = String.valueOf(jsonContato.getInt("id"));

        // o contato recebe somente as tags do prospecto
        vincularTagsContato(idContato, jsonContato, getCodigosRemotosTagsProspecto());

        MetaRelacionamento meta = getEntidade().getProspecto().getMeta();
        if (meta != null) {
            LinkEntidadesSistemasERP linkEstagio = getLinkExistenteOutraEntidade(meta);
            if (linkEstagio == null) {
                throw new ErroSincronizando("Vínculo do estágio não encontrado para a meta " + meta.getNome());
            }
            vincularEstagioContato(idContato, linkEstagio.getCodigoSistemaRemoto());
        }

        ItfRespostaWebServiceSimples respAdicionar = FabMauticContatoRest.EMPRESA_CTR_SALVAR_ADICIONAR_CONTATO.getAcao(idEmpresa, idContato).getResposta();
        if (respAdicionar == null || !respAdicionar.isSucesso()) {
            throw new ErroSincronizando("Falha vinculando o contato " + idContato + " à empresa " + idEmpresa + " - "
                    + (respAdicionar == null ? "sem resposta" : respAdicionar.getRespostaTexto()));
        }
        return gerarOuAtualizarLink(idContato);
    }

    /**
     * Reaproveita o link do contato, evitando um novo registro a cada
     * sincronização
     */
    private LinkEntidadesSistemasERP gerarOuAtualizarLink(String pIdContato) {
        LinkEntidadesSistemasERP link = getLinkExistente();
        if (link == null) {
            return gerarLinkExistente(pIdContato);
        }
        if (!pIdContato.equals(link.getCodigoSistemaRemoto())) {
            link.setCodigoSistemaRemoto(pIdContato);
            link = UtilSBPersistencia.mergeRegistro(link);
        }
        registrarAtualizacao();
        return link;
    }

    /**
     * Códigos no Mautic das tags do prospecto, obtidos pelo link de cada
     * TagAtendimento. As tags são sincronizadas pelo MauticSincPessoa antes
     * dos contatos
     */
    private Set<String> getCodigosRemotosTagsProspecto() throws ErroSincronizando {
        Set<String> codigos = new HashSet<>();
        for (TagAtendimento tag : getEntidade().getProspecto().getTagsAtendimento()) {
            LinkEntidadesSistemasERP linkTag = getLinkExistenteOutraEntidade(tag);
            if (linkTag == null || UtilCRCStringValidador.isNuloOuEmbranco(linkTag.getCodigoSistemaRemoto())) {
                throw new ErroSincronizando("A tag " + tag.getNome() + " ainda não foi sincronizada com o Mautic");
            }
            codigos.add(linkTag.getCodigoSistemaRemoto());
        }
        return codigos;
    }

    /**
     * Atribui ao contato as tags do prospecto (pelos ids no Mautic), e retira
     * as tags gerenciadas pelo CRM que não estão mais no prospecto. Tags
     * criadas no próprio Mautic, sem vínculo com uma TagAtendimento, são
     * mantidas.
     */
    private void vincularTagsContato(String pIdContato, JsonObject pJsonContato, Set<String> pCodigosTagsProspecto) throws ErroSincronizando {
        Map<String, String> tagsContatoPorCodigo = new HashMap<>();
        JsonValue tagsContato = pJsonContato.get("tags");
        if (tagsContato != null && tagsContato.getValueType() == JsonValue.ValueType.ARRAY) {
            for (JsonValue ob : tagsContato.asJsonArray()) {
                JsonObject tag = ob.asJsonObject();
                tagsContatoPorCodigo.put(String.valueOf(tag.getInt("id")), tag.getString("tag", ""));
            }
        }

        Set<String> codigosGerenciadosCRM = getCodigosRemotosTagVinculados(tagsContatoPorCodigo.keySet());
        // a retirada é feita pelo nome exato da tag no Mautic, com o prefixo "-"
        List<String> tagsRetirar = tagsContatoPorCodigo.entrySet().stream()
                .filter(tg -> codigosGerenciadosCRM.contains(tg.getKey()))
                .filter(tg -> !pCodigosTagsProspecto.contains(tg.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        List<String> codigosAtribuir = pCodigosTagsProspecto.stream()
                .filter(codigo -> !tagsContatoPorCodigo.containsKey(codigo))
                .collect(Collectors.toList());

        if (!codigosAtribuir.isEmpty()) {
            ItfRespostaWebServiceSimples resp;
            try {
                resp = FabMauticMetaTags.ATRIBUIR_TAG_CONTATO.getAcao(pIdContato, codigosAtribuir).getResposta();
            } catch (UnsupportedOperationException e) {
                // validação dos parâmetros ou id de tag não encontrado no Mautic
                throw new ErroSincronizando("Falha atribuindo tags " + codigosAtribuir + " ao contato " + pIdContato + " - " + e.getMessage());
            }
            if (resp == null || !resp.isSucesso()) {
                throw new ErroSincronizando("Falha atribuindo tags ao contato " + pIdContato + " - "
                        + (resp == null ? "sem resposta" : resp.getRespostaTexto()));
            }
        }
        if (!tagsRetirar.isEmpty()) {
            ItfRespostaWebServiceSimples resp = FabMauticMetaTags.RETIRAR_TAG_CONTATO.getAcao(pIdContato, tagsRetirar).getResposta();
            if (resp == null || !resp.isSucesso()) {
                throw new ErroSincronizando("Falha retirando tags do contato " + pIdContato + " - "
                        + (resp == null ? "sem resposta" : resp.getRespostaTexto()));
            }
        }
    }

    private void vincularEstagioContato(String pIdContato, String pCodigoEstagio) throws ErroSincronizando {
        ItfRespostaWebServiceSimples resp = FabMauticMetaTags.VINCULAR_ESTAGIO_CONTATO.getAcao(pCodigoEstagio, pIdContato).getResposta();
        if (resp == null || !resp.isSucesso()) {
            throw new ErroSincronizando("Falha vinculando o estágio " + pCodigoEstagio + " ao contato " + pIdContato + " - "
                    + (resp == null ? "sem resposta" : resp.getRespostaTexto()));
        }
    }

    /**
     * Dentre as tags que o contato possui no Mautic, retorna os códigos das que
     * estão vinculadas a alguma TagAtendimento (gerenciadas pelo CRM)
     */
    private Set<String> getCodigosRemotosTagVinculados(Set<String> pCodigosTagsContato) {
        if (pCodigosTagsContato.isEmpty()) {
            return new HashSet<>();
        }
        List<String> codigos = em.createQuery("select l.codigoSistemaRemoto from LinkEntidadesSistemasERP l"
                + " where l.entidade = :entidade and l.codigoSistemaRemoto in :codigos", String.class)
                .setParameter("entidade", TagAtendimento.class.getSimpleName())
                .setParameter("codigos", new ArrayList<>(pCodigosTagsContato))
                .getResultList();
        return new HashSet<>(codigos);
    }

}
