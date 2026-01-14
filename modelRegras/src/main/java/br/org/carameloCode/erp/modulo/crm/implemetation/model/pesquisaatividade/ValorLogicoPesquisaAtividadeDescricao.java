package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.DESCRICAO)
public class ValorLogicoPesquisaAtividadeDescricao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
}
