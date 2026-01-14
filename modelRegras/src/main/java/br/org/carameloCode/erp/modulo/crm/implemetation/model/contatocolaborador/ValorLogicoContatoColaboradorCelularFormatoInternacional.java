package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatocolaborador;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValorLogicoContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValoresLogicosContatoColaborador;

@ValorLogicoContatoColaborador(calculo = ValoresLogicosContatoColaborador.CELULARFORMATOINTERNACIONAL)
public class ValorLogicoContatoColaboradorCelularFormatoInternacional
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoContatoColaboradorCelularFormatoInternacional(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}