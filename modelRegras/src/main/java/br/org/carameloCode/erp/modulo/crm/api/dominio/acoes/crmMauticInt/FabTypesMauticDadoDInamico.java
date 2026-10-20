/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import jakarta.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tipos de campo personalizado do Mautic usados para os dados dinâmicos do CRM,
 * com a conversão do valor do DadoCRM para o formato aceito pela API.
 *
 * @author salvio
 */
public enum FabTypesMauticDadoDInamico {
    SELECT,
    NUMBER,
    BOOLEAN,
    DATE,
    TIME,
    DATETIME,
    EMAIL,
    URL,
    TEL,
    HTML,
    TEXTAREA,
    TEXT;

    /**
     * Valor do dado no formato aceito pela API do Mautic (String, Long, Double
     * ou Integer), ou null quando o dado está vazio
     */
    public Object getValor(DadoCRM pDado) {
        ItfCampoInstanciado campo = pDado.getCampoInstanciado();
        if (campo == null || campo.isVazio()) {
            return null;
        }
        switch (this) {
            case NUMBER:
                if (pDado.getTipoDadoCRM().getFabricaTipoAtributo().getTipoPrimitivo() == TIPO_PRIMITIVO.DECIMAL) {
                    return campo.getValorComoDouble();
                }
                return campo.getValorComoLong();
            case BOOLEAN:
                //o Mautic grava o boolean como 1 ou 0
                return campo.getValorComoBoolean() ? 1 : 0;
            case DATE:
                return formatarData(campo.getValorComoDate(), "yyyy-MM-dd");
            case TIME:
                return formatarData(campo.getValorComoDate(), "HH:mm");
            case DATETIME:
                return formatarData(campo.getValorComoDate(), "yyyy-MM-dd HH:mm:ss");
            case SELECT:
            case EMAIL:
            case URL:
            case HTML:
                //valor sem formatação: opção do select, endereço ou html
                return pDado.getValor();
            case TEL:
            case TEXTAREA:
            case TEXT:
                return campo.getValorTextoFormatado();
            default:
                throw new AssertionError(this.name());
        }
    }

    /**
     * Adiciona o valor do dado ao JSON de atualização da empresa, indexado pelo
     * alias do campo; dado vazio é enviado como null (limpa o campo no Mautic)
     */
    public void adicionarValor(JsonObjectBuilder pJson, String pAlias, DadoCRM pDado) {
        Object valor = getValor(pDado);
        if (valor == null) {
            pJson.addNull(pAlias);
        } else if (valor instanceof Long) {
            pJson.add(pAlias, (Long) valor);
        } else if (valor instanceof Double) {
            pJson.add(pAlias, (Double) valor);
        } else if (valor instanceof Integer) {
            pJson.add(pAlias, (Integer) valor);
        } else {
            pJson.add(pAlias, valor.toString());
        }
    }

    private static String formatarData(Date pData, String pFormato) {
        if (pData == null) {
            return null;
        }
        return new SimpleDateFormat(pFormato).format(pData);
    }

    /**
     * Nome do type na API do Mautic (o name() de enum é final no Java, por isso
     * o type é devolvido pelo toString())
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public String getTypeNameJson() {
        return toString();
    }

    public static FabTypesMauticDadoDInamico getTypeByTipoDado(TipoDadoCRM pTipoDado) {
        if (pTipoDado.getOpcoesPersonalizada() != null && !pTipoDado.getOpcoesPersonalizada().isEmpty()) {
            return SELECT;
        }
        switch (pTipoDado.getFabricaTipoAtributo().getTipoPrimitivo()) {
            case INTEIRO:
            case NUMERO_LONGO:
            case DECIMAL:
                return NUMBER;
            case BOOLEAN:
                return BOOLEAN;
            case DATAS:
                switch (pTipoDado.getFabricaTipoAtributo()) {
                    case DATA:
                        return DATE;
                    case HORA:
                        return TIME;
                    default:
                        return DATETIME;
                }
            case LETRAS:
                switch (pTipoDado.getFabricaTipoAtributo()) {
                    case EMAIL:
                        return EMAIL;
                    case URL:
                    case SITE:
                        return URL;
                    case TELEFONE_CELULAR:
                    case TELEFONE_FIXO_INTERNACIONAL:
                    case TELEFONE_FIXO_NACIONAL:
                    case TELEFONE_GENERICO:
                        return TEL;
                    case HTML:
                    case HTML_TEMPLATE:
                        return HTML;
                    case DESCRITIVO:
                        return TEXTAREA;
                    default:
                        return TEXT;
                }
            case ENTIDADE:
                return TEXT;

            case OUTROS_OBJETOS:
                return TEXT;

            default:
                return TEXT;
        }
    }
}
