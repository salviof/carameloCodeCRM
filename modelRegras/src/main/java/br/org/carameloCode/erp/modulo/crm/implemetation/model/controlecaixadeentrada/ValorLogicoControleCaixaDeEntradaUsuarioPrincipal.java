package br.org.carameloCode.erp.modulo.crm.implemetation.model.controlecaixadeentrada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.controlecaixadeentrada.ValorLogicoControleCaixaDeEntrada;
import br.org.carameloCode.erp.modulo.crm.api.model.controlecaixadeentrada.ValoresLogicosControleCaixaDeEntrada;

@ValorLogicoControleCaixaDeEntrada(calculo = ValoresLogicosControleCaixaDeEntrada.USUARIOPRINCIPAL)
public class ValorLogicoControleCaixaDeEntradaUsuarioPrincipal
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoControleCaixaDeEntradaUsuarioPrincipal(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}