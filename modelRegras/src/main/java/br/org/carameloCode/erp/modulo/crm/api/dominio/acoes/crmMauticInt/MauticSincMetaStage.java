package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ErroSincronizando;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ProcessadorDemandaAbs;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.DemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.LinkEntidadesSistemasERP;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticMetaTags;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

/**
 *
 * @author salvio
 */
public class MauticSincMetaStage extends ProcessadorDemandaAbs {

    public MauticSincMetaStage(DemandaSinc pMeta) {
        super(pMeta);
    }

    @Override
    public MetaRelacionamento getEntidade() {
        return (MetaRelacionamento) super.getEntidade(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    private void listarEstagios() {
        ItfRespostaWebServiceSimples resposta = FabMauticMetaTags.ESTAGIO_LISTAR.getAcao().getResposta();
        if (resposta.isSucesso()) {
            JsonObject jsonRespEndpoint = resposta.getRespostaComoObjetoJson();

            if (jsonRespEndpoint.getInt("total") == 0) {
                return;
            }
            JsonArray listaEStagios = jsonRespEndpoint.getJsonArray("stages");

            for (JsonValue ob : listaEStagios) {
                ob.asJsonObject().getInt("weight");
            }
        }
    }

    public String getNaoInformadoSeNulo(String pParametro) {
        if (pParametro == null) {
            return "n informado";
        }
        return pParametro;
    }

    @Override
    public LinkEntidadesSistemasERP sincronizar() throws ErroSincronizando {

        Integer peso = getEntidade().getPeso();
        LinkEntidadesSistemasERP linkExistente = getLinkExistente();
        //
        ItfRespostaWebServiceSimples respDadoAtualizado;
        if (linkExistente == null) {
            //pesquisa se tem um estagil, com esse peso, que não esteja vinculada a outra meta
            ItfRespostaWebServiceSimples respostaJson = FabMauticMetaTags.ESTAGIO_POR_PESO.getAcao(getEntidade().getPeso()).getResposta();
            JsonObject jsonRespEndpoint = respostaJson.getRespostaComoObjetoJson();
            Integer codigoExterno = null;

            if (jsonRespEndpoint.getInt("total") == 1) {
                JsonArray listaEStagios = jsonRespEndpoint.getJsonArray("stages");
                JsonObject estagio = listaEStagios.getJsonObject(0);
                Integer codigoEstagioRemotoMesmoPeso = estagio.getInt("id");
                LinkEntidadesSistemasERP linkMesmoPeso = getLinkExistenteCodigoRemoto(codigoEstagioRemotoMesmoPeso.toString());
                if (linkMesmoPeso != null) {
                    throw new ErroSincronizando("Falha já existe um outro estágio vinculado ao peso " + linkMesmoPeso.getCodigoInterno());
                }
                respDadoAtualizado = FabMauticMetaTags.ESTAGIO_CTR_SALVAR_EDITAR_ESTAGIO.getAcao(codigoEstagioRemotoMesmoPeso, getEntidade().getNome(), getEntidade().getDescricaoEtapaVisaoPrestador(), getEntidade().getPeso(), true).getResposta();
                if (respDadoAtualizado.isSucesso()) {

                    return gerarLinkExistente(codigoEstagioRemotoMesmoPeso.toString());
                } else {
                    throw new ErroSincronizando("Falha atualizando estagio peso " + getEntidade().getPeso() + " - " + respDadoAtualizado.getRespostaTexto());
                }

            } else {
                respDadoAtualizado = FabMauticMetaTags.ESTAGIO_CTR_NOVO_ESTAGIO.getAcao(getEntidade().getNome(), getEntidade().getDescricaoEtapaVisaoPrestador(), getEntidade().getPeso(), true).getResposta();
                if (respDadoAtualizado.isSucesso()) {
                    Integer idCriado = respDadoAtualizado.getRespostaComoObjetoJson().getJsonObject("stage").getInt("id");
                    return gerarLinkExistente(idCriado.toString());
                } else {
                    throw new ErroSincronizando("Falha atualizando estagio peso " + getEntidade().getPeso() + " - " + respDadoAtualizado.getRespostaTexto());
                }
            }

        } else {
            if (isExpirouUltimoUpdate()) {
                respDadoAtualizado = FabMauticMetaTags.ESTAGIO_CTR_SALVAR_EDITAR_ESTAGIO.getAcao(linkExistente.getCodigoSistemaRemoto(), getEntidade().getNome(), getEntidade().getDescricaoEtapaVisaoPrestador(), getEntidade().getPeso(), true).getResposta();
                if (respDadoAtualizado.isSucesso()) {
                    Integer idCriado = respDadoAtualizado.getRespostaComoObjetoJson().getJsonObject("stage").getInt("id");
                    return gerarLinkExistente(idCriado.toString());
                } else {
                    throw new ErroSincronizando("Falha atualizando estagio id " + getEntidade().getId() + " - " + respDadoAtualizado.getRespostaTexto());
                }
            } else {
                return linkExistente;
            }

        }

    }

}
