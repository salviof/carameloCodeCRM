package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.USUARIOSDISPONIVEIS)
public class ValorLogicoPesquisaAtividadeUsuariosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeUsuariosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        return super.getValor(pEntidade); //To change body of generated methods, choose Tools | Templates.
    }

}
