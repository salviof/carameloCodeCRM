/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

/**
 *
 * @author SalvioF
 */
public class BFluxoItem {

    private String x;
    private String y;
    private String conector;
    private String identificador;
    private String acaoGeradora;
    private String conteudo;
    private final TIPO_ITEM tipoItem;

    public BFluxoItem(TIPO_ITEM pTipoItem) {
        tipoItem = pTipoItem;
    }

    public static enum TIPO_ITEM {
        RELACIONAMENTO_NEGATIVO, RELACIONAMENTO_ATUAL, RELACIONAMENTO_POSITIVO, RELACIONAMENTO_NEUTRO
    };

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public TIPO_ITEM getTipoItem() {
        return tipoItem;
    }

    public String getAcaoGeradora() {
        return acaoGeradora;
    }

    public void setAcaoGeradora(String acaoGeradora) {
        this.acaoGeradora = acaoGeradora;
    }

}
