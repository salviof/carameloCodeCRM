/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.opcaoPersonalizada.OpcaoPersonalizada;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ErroSincronizando;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.acoes.processadorDemandaSinc.ProcessadorDemandaAbs;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.DemandaSinc;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.LinkEntidadesSistemasERP;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticFields;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Sincroniza o TipoDadoCRM com um campo personalizado de empresa (company) do
 * Mautic.
 *
 * O alias do campo não pode ser alterado no Mautic: se o alias mudar no CRM, um
 * novo campo é criado e o link passa a apontar para ele.
 *
 * @author salvio
 */
public class MauticSincCampoDinamico extends ProcessadorDemandaAbs {

    private static final String GRUPO_CAMPOS = "professional";

    public MauticSincCampoDinamico(DemandaSinc pDEmanda) {
        super(pDEmanda);
        segundosAtualizacao = 10000;
    }

    @Override
    public TipoDadoCRM getEntidade() {
        return (TipoDadoCRM) super.getEntidade();
    }

    /**
     * O sistema usa o nome em maiúsculas, o Mautic aceita apenas minúsculas,
     * números e _ no alias
     */
    private String getAlias() {
        return UtilMauticSinc.gerarAlias(getEntidade());
    }

    private String getValorPadrao() {
        return getEntidade().getValorPadrao() == null ? "" : getEntidade().getValorPadrao();
    }

    /**
     * Todos os dados são obrigatórios no CRM, mas no Mautic o campo não é
     * obrigatório para não bloquear cadastros de outras origens
     */
    private boolean isCampoObrigatorio() {
        return false;
    }

    private boolean isTemOpcoes() {
        List<OpcaoPersonalizada> opcoes = getEntidade().getOpcoesPersonalizada();
        return opcoes != null && !opcoes.isEmpty();
    }

    private String getType() {

        return FabTypesMauticDadoDInamico.getTypeByTipoDado(getEntidade()).getTypeNameJson();
    }

    /**
     * Propriedades por tipo: opções do select, textos sim/não do boolean e
     * casas decimais do number. Nulo quando o tipo não usa propriedades
     */
    private JsonObject getPropriedades(String pType) {
        JsonObjectBuilder propriedades = Json.createObjectBuilder();
        switch (pType) {
            case "select":
                JsonArrayBuilder lista = Json.createArrayBuilder();
                for (OpcaoPersonalizada opcao : getEntidade().getOpcoesPersonalizada()) {
                    lista.add(Json.createObjectBuilder()
                            .add("label", opcao.getNome())
                            .add("value", opcao.getNome()));
                }
                propriedades.add("list", lista);
                break;
            case "boolean":
                propriedades.add("no", UtilCRCStringValidador.isNuloOuEmbranco(getEntidade().getTextoNegativo()) ? "Não" : getEntidade().getTextoNegativo());
                propriedades.add("yes", UtilCRCStringValidador.isNuloOuEmbranco(getEntidade().getTextoPositivo()) ? "Sim" : getEntidade().getTextoPositivo());
                break;
            case "number":
                boolean decimal = getEntidade().getFabricaTipoAtributo().getTipoPrimitivo() == com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO.DECIMAL;
                propriedades.add("roundmode", 4);
                propriedades.add("scale", decimal ? 2 : 0);
                break;
            default:
                return null;
        }
        return propriedades.build();
    }

    private String getTexto(JsonValue pValor) {
        if (pValor == null || pValor.getValueType() == JsonValue.ValueType.NULL) {
            return "";
        }
        if (pValor instanceof JsonString) {
            return ((JsonString) pValor).getString();
        }
        return pValor.toString();
    }

    /**
     * Compara o campo do Mautic com o TipoDadoCRM, o Mautic pode devolver
     * números das propriedades como texto
     */
    private boolean isPrecisaAtualizar(JsonObject pCampoRemoto, JsonObject pPropriedades) {
        if (!getEntidade().getLabel().equals(pCampoRemoto.getString("label", ""))) {
            return true;
        }
        if (!getValorPadrao().equals(getTexto(pCampoRemoto.get("defaultValue")))) {
            return true;
        }
        if (isCampoObrigatorio() != pCampoRemoto.getBoolean("isRequired", false)) {
            return true;
        }
        if (pPropriedades != null) {
            JsonValue propriedadesRemotas = pCampoRemoto.get("properties");
            if (propriedadesRemotas == null || propriedadesRemotas.getValueType() != JsonValue.ValueType.OBJECT) {
                return true;
            }
            JsonObject remotas = propriedadesRemotas.asJsonObject();
            for (String chave : pPropriedades.keySet()) {
                if (!getTexto(pPropriedades.get(chave)).equals(getTexto(remotas.get(chave)))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void editarCampoRemoto(String pCodigoRemoto, JsonObject pPropriedades) throws ErroSincronizando {
        ItfRespostaWebServiceSimples resp = FabMauticFields.CAMPO_CTR_SALVAR_EDITAR.getAcao(pCodigoRemoto,
                getEntidade().getLabel(), GRUPO_CAMPOS, getValorPadrao(), isCampoObrigatorio(), true, pPropriedades).getResposta();
        if (!resp.isSucesso()) {
            throw new ErroSincronizando("Falha atualizando campo " + getAlias() + " - " + resp.getRespostaTexto());
        }
    }

    /**
     * Atualiza o campo remoto somente se houver diferença
     */
    private void atualizarSeNecessario(JsonObject pCampoRemoto, String pCodigoRemoto, JsonObject pPropriedades) throws ErroSincronizando {
        if (isPrecisaAtualizar(pCampoRemoto, pPropriedades)) {
            editarCampoRemoto(pCodigoRemoto, pPropriedades);
        }
    }

    /**
     * O Mautic pode devolver a lista como array ou como objeto indexado pelo id
     */
    private Collection<JsonValue> getListaCampos(JsonObject pJsonResposta) {
        JsonValue campos = pJsonResposta.get("fields");
        if (campos == null || campos.getValueType() == JsonValue.ValueType.NULL) {
            return Collections.emptyList();
        }
        if (campos.getValueType() == JsonValue.ValueType.ARRAY) {
            return campos.asJsonArray();
        }
        return campos.asJsonObject().values();
    }

    private JsonObject getCampoRemotoPorAlias(String pAlias) throws ErroSincronizando {
        ItfRespostaWebServiceSimples resp = FabMauticFields.CAMPO_DETALHES_POR_ALIAS.getAcao(pAlias).getResposta();
        if (!resp.isSucesso()) {
            throw new ErroSincronizando("Falha pesquisando o campo " + pAlias + " - " + resp.getRespostaTexto());
        }
        for (JsonValue valor : getListaCampos(resp.getRespostaComoObjetoJson())) {
            JsonObject campo = valor.asJsonObject();
            if (pAlias.equals(campo.getString("alias", ""))) {
                return campo;
            }
        }
        return null;
    }

    private String criarCampoRemoto(String pType, JsonObject pPropriedades) throws ErroSincronizando {
        ItfRespostaWebServiceSimples resp = FabMauticFields.CAMPO_CTR_SALVAR_NOVO.getAcao(getEntidade().getLabel(), getAlias(), pType,
                GRUPO_CAMPOS, getValorPadrao(), isCampoObrigatorio(), true, pPropriedades).getResposta();
        if (!resp.isSucesso()) {
            throw new ErroSincronizando("Falha criando campo " + getAlias() + " - " + resp.getRespostaTexto());
        }
        return String.valueOf(resp.getRespostaComoObjetoJson().getJsonObject("field").getInt("id"));
    }

    /**
     * Aponta o link existente para outro campo remoto, sem duplicar o link
     */
    private LinkEntidadesSistemasERP apontarLink(LinkEntidadesSistemasERP pLink, String pCodigoRemoto) {
        if (pLink == null) {
            return gerarLinkExistente(pCodigoRemoto);
        }
        pLink.setCodigoSistemaRemoto(pCodigoRemoto);
        LinkEntidadesSistemasERP link = UtilSBPersistencia.mergeRegistro(pLink);
        registrarAtualizacao();
        return link;
    }

    @Override
    public LinkEntidadesSistemasERP sincronizar() throws ErroSincronizando {
        LinkEntidadesSistemasERP linkExistente = getLinkExistente();
        if (linkExistente != null && !isExpirouUltimoUpdate()) {
            return linkExistente;
        }

        String alias = getAlias();
        String type = getType();
        JsonObject propriedades = getPropriedades(type);

        if (linkExistente != null) {
            ItfRespostaWebServiceSimples resp = FabMauticFields.CAMPO_DETALHES.getAcao(linkExistente.getCodigoSistemaRemoto()).getResposta();
            if (resp.isSucesso()) {
                JsonObject campoRemoto = resp.getRespostaComoObjetoJson().getJsonObject("field");
                //o Mautic não aceita renomear o alias, se mudou no CRM cria um novo campo
                if (alias.equals(campoRemoto.getString("alias", ""))) {
                    atualizarSeNecessario(campoRemoto, linkExistente.getCodigoSistemaRemoto(), propriedades);
                    //mesmo sem alteração registra, evitando novas consultas até expirar segundosAtualizacao
                    registrarAtualizacao();
                    return linkExistente;
                }
            }
            //campo removido no Mautic ou alias alterado no CRM: segue pela pesquisa por alias
        }

        JsonObject campoMesmoAlias = getCampoRemotoPorAlias(alias);
        if (campoMesmoAlias != null) {
            String codigoRemoto = String.valueOf(campoMesmoAlias.getInt("id"));
            LinkEntidadesSistemasERP linkMesmoAlias = getLinkExistenteCodigoRemoto(codigoRemoto);
            boolean vinculadoAOutroDado = linkMesmoAlias != null
                    && !linkMesmoAlias.getCodigoInterno().equals(getEntidade().getId().toString());
            if (!vinculadoAOutroDado) {
                atualizarSeNecessario(campoMesmoAlias, codigoRemoto, propriedades);
                return apontarLink(linkExistente, codigoRemoto);
            }
            //alias já vinculado a outro dado: cria um novo campo, o Mautic gera um alias único
        }

        return apontarLink(linkExistente, criarCampoRemoto(type, propriedades));
    }

}
