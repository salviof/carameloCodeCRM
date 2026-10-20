/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ErroSincronizando;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ProcessadorDemandaAbs;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.DemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.LinkEntidadesSistemasERP;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticContatoRest;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticModeloEmail;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.util.Collection;
import java.util.Collections;

/**
 * Sincroniza o ModeloEmailMarketing com um e-mail marketing (emailType "list")
 * do Mautic.
 *
 * @author salvio
 */
public class MauticSincEmailMarketing extends ProcessadorDemandaAbs {

    private static final int QTD_POR_PAGINA = Integer.parseInt(FabMauticContatoRest.QTD_PADRAO_LISTAGEM);

    public MauticSincEmailMarketing(DemandaSinc pMeta) {
        super(pMeta);
    }

    @Override
    public ModeloEmailMarketing getEntidade() {
        return (ModeloEmailMarketing) super.getEntidade();
    }

    private String zeraNulo(String pValor) {
        return pValor == null ? "" : pValor;
    }

    /**
     * O nome é obrigatório no Mautic, sem nome usa o assunto
     */
    private String getNomeEmail() {
        if (!UtilCRCStringValidador.isNuloOuEmbranco(getEntidade().getNome())) {
            return getEntidade().getNome().trim();
        }
        return zeraNulo(getEntidade().getAssunto()).trim();
    }

    /**
     * O Mautic pode devolver a lista de e-mails como array ou como objeto
     * indexado pelo id
     */
    private Collection<JsonValue> getListaEmails(JsonObject pJsonResposta) {
        JsonValue emails = pJsonResposta.get("emails");
        if (emails == null || emails.getValueType() == JsonValue.ValueType.NULL) {
            return Collections.emptyList();
        }
        if (emails.getValueType() == JsonValue.ValueType.ARRAY) {
            return emails.asJsonArray();
        }
        return emails.asJsonObject().values();
    }

    /**
     * Pesquisa no Mautic um modelo de e-mail marketing (emailType "template")
     * com o mesmo nome
     *
     * @return código do e-mail remoto, ou null se não encontrado
     */
    private Integer getCodigoEmailRemotoMesmoNome() throws ErroSincronizando {
        String nome = getNomeEmail();
        int inicio = 0;
        while (true) {
            ItfRespostaWebServiceSimples resposta = FabMauticModeloEmail.EMAIL_LISTAR.getAcao(inicio).getResposta();
            if (!resposta.isSucesso()) {
                throw new ErroSincronizando("Falha listando e-mails - " + resposta.getRespostaTexto());
            }
            JsonObject jsonRespEndpoint = resposta.getRespostaComoObjetoJson();
            Collection<JsonValue> listaEmails = getListaEmails(jsonRespEndpoint);
            for (JsonValue ob : listaEmails) {
                JsonObject email = ob.asJsonObject();
                if ("template".equals(email.getString("emailType", ""))
                        && nome.equalsIgnoreCase(email.getString("name", "").trim())) {
                    return email.getInt("id");
                }
            }
            inicio += QTD_POR_PAGINA;
            if (listaEmails.size() < QTD_POR_PAGINA || inicio >= jsonRespEndpoint.getInt("total", 0)) {
                return null;
            }
        }
    }

    /**
     * Troca os placeholders do CRM ([contatoPrincipal.nome], [dados.x]...)
     * pelos tokens do Mautic ({contactfield=...}, {companyfield=...})
     */
    private String traduzirPlaceHolders(String pTexto) {
        MapaSubstituicao placeHoldersTraducao = UtilMauticSinc.getPlaceHolders();
        String novoTExto = placeHoldersTraducao.substituirEmString(zeraNulo(pTexto));
        return novoTExto;
    }

    private ItfRespostaWebServiceSimples editarEmailRemoto(String pCodigoRemoto) {
        return FabMauticModeloEmail.MODELO_EMAIL_MARKETING_CTR_SALVAR_EDITAR.getAcao(pCodigoRemoto,
                getNomeEmail(), traduzirPlaceHolders(getEntidade().getAssunto()), traduzirPlaceHolders(getEntidade().getTextoModelo()),
                zeraNulo(getEntidade().getDescricao()), true).getResposta();
    }

    /**
     * Decide entre atualizar e criar: primeiro pelo LinkEntidadesSistemasERP,
     * depois pelo nome do e-mail no Mautic; se nenhum existir, cria.
     */
    @Override
    public LinkEntidadesSistemasERP sincronizar() throws ErroSincronizando {
        LinkEntidadesSistemasERP linkExistente = getLinkExistente();
        ItfRespostaWebServiceSimples respEdicaoLink = null;
        if (linkExistente != null) {
            //sempre atualiza, o conteúdo pode ter sido alterado (ex.: pela IA)
            respEdicaoLink = editarEmailRemoto(linkExistente.getCodigoSistemaRemoto());
            if (respEdicaoLink.isSucesso()) {
                return gerarLinkExistente(linkExistente.getCodigoSistemaRemoto());
            }
            //o e-mail pode ter sido removido no Mautic, segue pela pesquisa por nome
        }

        Integer codigoEmailMesmoNome = getCodigoEmailRemotoMesmoNome();
        if (codigoEmailMesmoNome != null) {
            String codigoRemoto = codigoEmailMesmoNome.toString();
            boolean mesmoEmailDoLink = linkExistente != null && codigoRemoto.equals(linkExistente.getCodigoSistemaRemoto());
            if (mesmoEmailDoLink) {
                throw new ErroSincronizando("Falha atualizando e-mail marketing " + getNomeEmail() + " - " + respEdicaoLink.getRespostaTexto());
            }
            LinkEntidadesSistemasERP linkMesmoNome = getLinkExistenteCodigoRemoto(codigoRemoto);
            if (linkMesmoNome != null) {
                throw new ErroSincronizando("Falha já existe outro modelo vinculado ao e-mail " + getNomeEmail() + " " + linkMesmoNome.getCodigoInterno());
            }
            ItfRespostaWebServiceSimples resp = editarEmailRemoto(codigoRemoto);
            if (!resp.isSucesso()) {
                throw new ErroSincronizando("Falha atualizando e-mail marketing " + getNomeEmail() + " - " + resp.getRespostaTexto());
            }
            return gerarLinkExistente(codigoRemoto);
        }

        ItfRespostaWebServiceSimples resp = FabMauticModeloEmail.MODELO_EMAIL_MARKETING_CTR_SALVAR_NOVO.getAcao(
                getNomeEmail(), traduzirPlaceHolders(getEntidade().getAssunto()), traduzirPlaceHolders(getEntidade().getTextoModelo()),
                zeraNulo(getEntidade().getDescricao()), true).getResposta();
        if (!resp.isSucesso()) {
            throw new ErroSincronizando("Falha criando e-mail marketing " + getNomeEmail() + " - " + resp.getRespostaTexto());
        }
        Integer idCriado = resp.getRespostaComoObjetoJson().getJsonObject("email").getInt("id");
        return gerarLinkExistente(idCriado.toString());
    }

}
