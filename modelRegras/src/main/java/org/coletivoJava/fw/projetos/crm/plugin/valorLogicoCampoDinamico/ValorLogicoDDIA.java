/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import br.org.coletivoJava.fw.api.erp.ia.escopo.ERP_IA;
import br.org.coletivoJava.fw.api.erp.ia.escopo.ItfErpIA;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDIA extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDIA(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        TipoDadoIA tipodado = getDadoDinamico().getTipoDadoCRM().getComoTipoDadoIA();

        tipodado.getConteudoPerguntaIA();

        ERP_IA erp = tipodado.getImplementacaoPadrao();

        ItfErpIA erpImpl = erp.getImplementacaoDoContexto();

        //erpImpl.obterResposta(tipodado.getModel(), SBCore.getUsuarioLogado(), "");
        return super.getValor(pEntidade); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }

}
