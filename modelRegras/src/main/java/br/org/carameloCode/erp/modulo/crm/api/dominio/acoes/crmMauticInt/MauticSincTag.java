/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ErroSincronizando;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ProcessadorDemandaAbs;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.DemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.LinkEntidadesSistemasERP;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticContatoRest;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticMetaTags;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author salvio
 */
public class MauticSincTag extends ProcessadorDemandaAbs {

    private static final int QTD_POR_PAGINA = Integer.parseInt(FabMauticContatoRest.QTD_PADRAO_LISTAGEM);

    public MauticSincTag(DemandaSinc pDemanda) {
        super(pDemanda);
    }

    @Override
    public TagAtendimento getEntidade() {
        return (TagAtendimento) super.getEntidade();
    }

    private String zeraNulo(String pValor) {
        if (pValor == null) {
            return "";
        } else {
            return pValor;
        }
    }

    /**
     * O Mautic pode devolver a lista de tags como array ou como objeto
     * indexado pelo id
     */
    private Collection<JsonValue> getListaTags(JsonObject pJsonResposta) {
        JsonValue tags = pJsonResposta.get("tags");
        if (tags == null || tags.getValueType() == JsonValue.ValueType.NULL) {
            return Collections.emptyList();
        }
        if (tags.getValueType() == JsonValue.ValueType.ARRAY) {
            return tags.asJsonArray();
        }
        return tags.asJsonObject().values();
    }

    /**
     * Pesquisa no Mautic uma tag com o mesmo nome (o nome da tag é único no
     * Mautic)
     *
     * @return código da tag remota, ou null se não encontrada
     */
    private Integer getCodigoTagRemotaMesmoNome() throws ErroSincronizando {
        String nome = getEntidade().getNome().trim();
        int inicio = 0;
        while (true) {
            ItfRespostaWebServiceSimples resposta = FabMauticMetaTags.TAG_LISTAR.getAcao(inicio).getResposta();
            if (!resposta.isSucesso()) {
                throw new ErroSincronizando("Falha listando tags - " + resposta.getRespostaTexto());
            }
            JsonObject jsonRespEndpoint = resposta.getRespostaComoObjetoJson();
            Collection<JsonValue> listaTags = getListaTags(jsonRespEndpoint);
            for (JsonValue ob : listaTags) {
                JsonObject tag = ob.asJsonObject();
                if (nome.equalsIgnoreCase(tag.getString("tag", "").trim())) {
                    return tag.getInt("id");
                }
            }
            inicio += QTD_POR_PAGINA;
            if (listaTags.size() < QTD_POR_PAGINA || inicio >= jsonRespEndpoint.getInt("total", 0)) {
                return null;
            }
        }
    }

    @Override
    public LinkEntidadesSistemasERP sincronizar() throws ErroSincronizando {

        LinkEntidadesSistemasERP linkExistente = getLinkExistente();
        //
        ItfRespostaWebServiceSimples respDadoAtualizado;
        if (linkExistente == null) {
            //pesquisa se tem uma tag, com esse nome, que não esteja vinculada a outra tag
            Integer codigoTagRemotaMesmoNome = getCodigoTagRemotaMesmoNome();

            if (codigoTagRemotaMesmoNome != null) {
                LinkEntidadesSistemasERP linkMesmoNome = getLinkExistenteCodigoRemoto(codigoTagRemotaMesmoNome.toString());
                if (linkMesmoNome != null) {
                    throw new ErroSincronizando("Falha já existe uma outra tag vinculada ao nome " + getEntidade().getNome() + " " + linkMesmoNome.getCodigoInterno());
                }
                respDadoAtualizado = FabMauticMetaTags.TAG_CTR_SALVAR_EDITAR_TAG.getAcao(codigoTagRemotaMesmoNome, getEntidade().getNome(), zeraNulo(getEntidade().getDescritivo())).getResposta();
                if (respDadoAtualizado.isSucesso()) {
                    return gerarLinkExistente(codigoTagRemotaMesmoNome.toString());
                } else {
                    throw new ErroSincronizando("Falha atualizando tag " + getEntidade().getNome() + " - " + respDadoAtualizado.getRespostaTexto());
                }

            } else {
                respDadoAtualizado = FabMauticMetaTags.TAG_CTR_SALVAR_NOVA_TAG.getAcao(getEntidade().getNome(), zeraNulo(getEntidade().getDescritivo())).getResposta();
                if (respDadoAtualizado.isSucesso()) {
                    Integer idCriado = respDadoAtualizado.getRespostaComoObjetoJson().getJsonObject("tag").getInt("id");
                    return gerarLinkExistente(idCriado.toString());
                } else {
                    throw new ErroSincronizando("Falha criando tag " + getEntidade().getNome() + " - " + respDadoAtualizado.getRespostaTexto());
                }
            }

        } else {
            if (isExpirouUltimoUpdate()) {
                respDadoAtualizado = FabMauticMetaTags.TAG_CTR_SALVAR_EDITAR_TAG.getAcao(linkExistente.getCodigoSistemaRemoto(), getEntidade().getNome(), zeraNulo(getEntidade().getDescritivo())).getResposta();
                if (respDadoAtualizado.isSucesso()) {
                    Integer idAtualizado = respDadoAtualizado.getRespostaComoObjetoJson().getJsonObject("tag").getInt("id");
                    return gerarLinkExistente(idAtualizado.toString());
                } else {
                    throw new ErroSincronizando("Falha atualizando tag id " + getEntidade().getId() + " - " + respDadoAtualizado.getRespostaTexto());
                }
            } else {
                return linkExistente;
            }

        }

    }

}
