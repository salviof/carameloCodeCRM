/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.UtilCRCDemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ErroSincronizando;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ProcessadorDemandaAbs;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.DemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.LinkEntidadesSistemasERP;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticContatoRest;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticMetaTags;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCHtmlFormat;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.oauth.FabStatusToken;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import groovy.json.JsonBuilder;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.util.HashSet;
import java.util.Set;
import org.coletivoJava.fw.projetos.integracao.api.model.linkentidadessistemaserp.CPLinkEntidadesSistemasERP;

/**
 *
 * @author salvio
 */
public class MauticSincPessoa extends ProcessadorDemandaAbs {

    public MauticSincPessoa(DemandaSinc pDemanda) {
        super(pDemanda);
    }

    private String zeraNulo(String pValor) {
        if (pValor == null) {
            return "";
        } else {
            return pValor;
        }
    }

    public Pessoa getPessoa() {
        return (Pessoa) getEntidade();
    }

    /**
     * Envia os valores dos dados dinâmicos para os campos personalizados da
     * empresa. Antes, garante que cada campo existe no Mautic (o
     * MauticSincCampoDinamico só consulta a API após o segundosAtualizacao)
     */
    private void atualizarDadosDInamicos(String pCodigo) throws ErroSincronizando {
        JsonObjectBuilder jbdadosdinamicJson = Json.createObjectBuilder();
        Set<Long> tiposSincronizados = new HashSet<>();
        boolean temDados = false;
        for (DadoCRM dado : getPessoa().getDadosColetados()) {
            TipoDadoCRM tipoDado = dado.getTipoDadoCRM();
            if (tipoDado == null) {
                continue;
            }
            if (tiposSincronizados.add(tipoDado.getId())) {
                executarSincronizacaoDependencia(new MauticSincCampoDinamico(
                        UtilSBPersistencia.mergeRegistro(UtilCRCDemandaSinc.gerarDemanda(tipoDado, MauticSincCampoDinamico.class))));
            }
            FabTypesMauticDadoDInamico.getTypeByTipoDado(tipoDado)
                    .adicionarValor(jbdadosdinamicJson, UtilMauticSinc.gerarAlias(tipoDado), dado);
            temDados = true;
        }
        if (!temDados) {
            return;
        }
        ItfRespostaWebServiceSimples resp = FabMauticContatoRest.ATUALIZAR_DADOS_DINAMICOS.getAcao(pCodigo, jbdadosdinamicJson.build()).getResposta();
        if (resp == null || !resp.isSucesso()) {
            throw new ErroSincronizando("Falha atualizando dados dinâmicos da empresa " + getPessoa().getNome()
                    + " id " + pCodigo + " - " + (resp == null ? "sem resposta" : resp.getRespostaTexto()));
        }
    }

    @Override
    public LinkEntidadesSistemasERP sincronizar() throws ErroSincronizando {
        if (!FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getGestaoToken().isTemTokemAtivo()) {
            FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getGestaoToken().gerarNovoToken();
            if (!FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getGestaoToken().isTemTokemAtivo()) {
                throw new ErroSincronizando("O mautic não está conectado, impossível atualizar o Leed no maútic");
            }
        }
        LinkEntidadesSistemasERP link = getLinkExistente();
        if (link != null) {
            if (!isExpirouUltimoUpdate()) {
                return link;
            }
        }
        String idEmpresa = "0";
        MetaRelacionamento meta = getPessoa().getMeta();
        if (meta != null) {
            MauticSincMetaStage metaSinc = new MauticSincMetaStage(UtilCRCDemandaSinc.gerarDemanda(meta, MauticSincMetaStage.class));
            executarSincronizacaoDependencia(metaSinc);
            if (getCodigoRemotoVinculado(MetaRelacionamento.class, meta.getId()) == null) {
                throw new ErroSincronizando("Vínculo do estágio não encontrado para a meta " + meta.getNome());
            }
        }
        for (TagAtendimento tag : getPessoa().getTagsAtendimento()) {
            MauticSincTag tagSinc = new MauticSincTag(UtilCRCDemandaSinc.gerarDemanda(tag, MauticSincTag.class));

            executarSincronizacaoDependencia(tagSinc);
        }

        String observacao = "";
        Pessoa pessoaJuridicaAtualizada = (Pessoa) getEntidade();

        if (pessoaJuridicaAtualizada.getOrigem() != null) {
            observacao += "[" + pessoaJuridicaAtualizada.getOrigem().getNome() + "]";
        } else {
            observacao += "[Sem_Origem_Definida]";
        }
        if (pessoaJuridicaAtualizada.getCPinst("porte").getValor() != null) {
            observacao += "[" + pessoaJuridicaAtualizada.getCPinst("porte").getValorComoEntidadeSimples().getNome() + "]";
        } else {
            observacao += "[Sem_Porte_Definido]";
        }
        if (pessoaJuridicaAtualizada.getObservacao() != null) {
            observacao = observacao + " " + pessoaJuridicaAtualizada.getObservacao();
        }

        String email = pessoaJuridicaAtualizada.getEmail();
        String site = null;
        // getComoPessoaJuridica() relata erro quando a pessoa é física
        if (PessoaJuridica.class.isAssignableFrom(UtilCRCReflexaoObjeto.getClassExtraindoProxy(pessoaJuridicaAtualizada.getClass().getSimpleName()))) {
            site = pessoaJuridicaAtualizada.getComoPessoaJuridica().getSite();
        }
        String telolofone = pessoaJuridicaAtualizada.getTelefonePrincipal();
        observacao += "[codigoIntra:" + String.valueOf(pessoaJuridicaAtualizada.getId()) + "]";

        if (!FabMauticContatoRest.LISTAREMPRESA_COM_FILTRO.getGestaoToken().getStatusToken().equals(FabStatusToken.ATIVO)) {
            FabMauticContatoRest.LISTAREMPRESA_COM_FILTRO.getGestaoToken().getComoGestaoOauth().validarToken();
        }
        //MAximo companuy description é 60 caracteres
        observacao = UtilCRCHtmlFormat.retirarTagsDeHtml(observacao);
        observacao = UtilCRCStringFiltros.getPrimeirasXLetrasDaString(observacao, 60);

        if (UtilCRCStringValidador.isNAO_NuloNemBranco(pessoaJuridicaAtualizada.getEmail())
                && !pessoaJuridicaAtualizada.getEmail().contains("informado")) {

            try {

                if (link != null) {
                    idEmpresa = link.getCodigoSistemaRemoto();
                } else {
                    ItfAcaoApiRest registroUnico = FabMauticContatoRest.LISTAREMPRESA_COM_FILTRO.getAcao(pessoaJuridicaAtualizada.getEmail());
                    ItfRespostaWebServiceSimples resposta = registroUnico.getResposta();

                    if (Integer.valueOf(resposta.getRespostaComoObjetoJson().getString("total")) >= 1) {
                        try {
                            JsonObject empresas = resposta.getRespostaComoObjetoJson().getJsonObject("companies");
                            for (Object codEmpresa : empresas.keySet()) {
                                idEmpresa = codEmpresa.toString();
                            }
                        } catch (Throwable t) {
                            //PessoaJuridica não existe
                        }
                    }
                }
            } catch (Throwable t) {
                throw new UnsupportedOperationException("Falha conectando com Mautic");
            }
            ItfAcaoApiRest conexaoAtualizarEmpresa = null;
            if (idEmpresa == null || Integer.valueOf(idEmpresa) != 0) {
                conexaoAtualizarEmpresa = FabMauticContatoRest.EMPRESA_CTR_SALVAR_EDITAR_EMPRESA.getAcao(idEmpresa,
                        pessoaJuridicaAtualizada.getNome(),
                        zeraNulo(email),
                        zeraNulo(site),
                        zeraNulo(telolofone),
                        zeraNulo(observacao)
                );
            } else {
                conexaoAtualizarEmpresa = FabMauticContatoRest.EMPRESA_CTR_SALVAR_NOVA_EMPRESA.getAcao(
                        pessoaJuridicaAtualizada.getNome(),
                        zeraNulo(email),
                        zeraNulo(site),
                        zeraNulo(telolofone),
                        zeraNulo(observacao)
                );
            }
            if (link == null) {
                link = gerarLinkExistente(idEmpresa);
            }
            if (conexaoAtualizarEmpresa == null) {
                throw new ErroSincronizando("Falha criando comando de atualização da empresa " + pessoaJuridicaAtualizada.getNome());
            } else {
                ItfRespostaWebServiceSimples respAtualizacaoEmpresa = conexaoAtualizarEmpresa.getResposta();
                if (respAtualizacaoEmpresa == null) {
                    throw new ErroSincronizando("Sem resposta do Mautic atualizando a empresa " + pessoaJuridicaAtualizada.getNome());
                } else {
                    if (!respAtualizacaoEmpresa.isSucesso()) {
                        throw new ErroSincronizando("Falha atualizando a empresa " + pessoaJuridicaAtualizada.getNome()
                                + " id " + idEmpresa + " - " + respAtualizacaoEmpresa.getRespostaTexto());
                    } else {
                        idEmpresa = String.valueOf(respAtualizacaoEmpresa.getRespostaComoObjetoJson().getJsonObject("company").getInt("id"));
                        // o link da empresa é gravado antes dos contatos, que o utilizam para se vincular à empresa
                        link = gerarOuAtualizarLink(link, idEmpresa);
                        //depois da atualização principal, que usa PUT e sobrescreveria os campos personalizados
                        atualizarDadosDInamicos(idEmpresa);
                        for (ContatoProspecto ct : pessoaJuridicaAtualizada.getContatosProspecto()) {
                            if (!UtilCRCStringValidador.isNuloOuEmbranco(ct.getEmail())
                                    && !UtilCRCStringValidador.isNuloOuEmbranco(ct.getNome())) {
                                MauticSincSalvarContato contatoSInc = new MauticSincSalvarContato(UtilSBPersistencia.mergeRegistro(UtilCRCDemandaSinc.gerarDemanda(ct, MauticSincSalvarContato.class)));
                                executarSincronizacaoDependencia(contatoSInc);
                            }

                        }
                    }
                }

            }
            if (idEmpresa.equals("0")) {
                throw new ErroSincronizando("Código da empresa não foi encontrado");
            }

        }
        if (link != null && idEmpresa.equals("0")) {
            // sem e-mail válido a empresa não é enviada; mantém o vínculo anterior
            return link;
        }
        return gerarOuAtualizarLink(link, idEmpresa);
    }

    /**
     * Reaproveita o link da empresa, evitando um novo registro a cada
     * sincronização
     */
    private LinkEntidadesSistemasERP gerarOuAtualizarLink(LinkEntidadesSistemasERP pLink, String pIdEmpresa) {
        if (pLink == null) {
            return gerarLinkExistente(pIdEmpresa);
        }
        if (!pIdEmpresa.equals(pLink.getCodigoSistemaRemoto())) {
            pLink.setCodigoSistemaRemoto(pIdEmpresa);
            pLink = UtilSBPersistencia.mergeRegistro(pLink);
        }
        registrarAtualizacao();
        return pLink;
    }

    /**
     * Executa a sincronização de uma entidade da qual a pessoa depende (meta,
     * tags) e aguarda a conclusão
     */
    private void executarSincronizacaoDependencia(ProcessadorDemandaAbs pProcessador) throws ErroSincronizando {
        UtilCRCDemandaSinc.iniciarEregistrarProcessamento(pProcessador);
        if (!pProcessador.aguardarProcessamento()) {
            throw new ErroSincronizando("Falha sincronizando " + pProcessador.getDemanda().getNomeClasseEntidade()
                    + " " + pProcessador.getDemanda().getCodigoEntidade() + " - " + pProcessador.getMensagemErro());
        }
    }

    private String getCodigoRemotoVinculado(Class pEntidade, Long pCodigoInterno) {
        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(LinkEntidadesSistemasERP.class, em);
        consulta.addcondicaoCampoIgualA(CPLinkEntidadesSistemasERP.entidade, pEntidade.getSimpleName());
        consulta.addcondicaoCampoIgualA(CPLinkEntidadesSistemasERP.codigointerno, pCodigoInterno.toString());
        LinkEntidadesSistemasERP link = consulta.getPrimeiroRegistro();
        if (link == null) {
            return null;
        }
        return link.getCodigoSistemaRemoto();
    }

}
