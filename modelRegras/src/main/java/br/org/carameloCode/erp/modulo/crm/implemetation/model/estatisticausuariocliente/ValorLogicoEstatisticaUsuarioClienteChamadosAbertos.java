package br.org.carameloCode.erp.modulo.crm.implemetation.model.estatisticausuariocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.estatisticausuariocliente.ValorLogicoEstatisticaUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.estatisticausuariocliente.ValoresLogicosEstatisticaUsuarioCliente;

@ValorLogicoEstatisticaUsuarioCliente(calculo = ValoresLogicosEstatisticaUsuarioCliente.CHAMADOSABERTOS)
public class ValorLogicoEstatisticaUsuarioClienteChamadosAbertos
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoEstatisticaUsuarioClienteChamadosAbertos(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}