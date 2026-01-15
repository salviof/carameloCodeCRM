/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.zonaRelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.FabTipoRelacionamentoMarketingDigital;

/**
 *
 * @author desenvolvedor
 */
public enum FabResultadoTipoRelacionamento implements ComoFabricaResultado {
    POSITIVO,
    NEGATIVO,
    NEUTRO;

    @Override
    public ResultadoTipoRelacionamento getRegistro() {
        ResultadoTipoRelacionamento resultado = new ResultadoTipoRelacionamento(this);
        resultado.setId((long) this.ordinal() + 1);

        switch (this) {
            case POSITIVO:
                resultado.setNome("Negócio Fechado");
                resultado.setCorResultado(FabTipoRelacionamentoMarketingDigital.COR_RELACIONAMENTO_POSITIVO);
                break;
            case NEGATIVO:
                resultado.setNome("Negócio não concretizado");
                resultado.setCorResultado(FabTipoRelacionamentoMarketingDigital.COR_RELACIONAMENTO_NEGATIVO);
                break;
            case NEUTRO:
                resultado.setNome("Possível fechar negocio");
                resultado.setCorResultado(FabTipoRelacionamentoMarketingDigital.COR_RELACIONAMENTO_NEUTRO);
                break;
            default:
                throw new AssertionError(this.name());

        }
        return resultado;
    }

}
