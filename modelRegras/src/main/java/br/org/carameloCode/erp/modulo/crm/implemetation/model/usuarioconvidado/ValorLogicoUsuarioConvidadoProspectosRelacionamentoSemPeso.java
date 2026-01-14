package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuarioconvidado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado.ValorLogicoUsuarioConvidado;
import br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado.ValoresLogicosUsuarioConvidado;

@ValorLogicoUsuarioConvidado(calculo = ValoresLogicosUsuarioConvidado.PROSPECTOSRELACIONAMENTOSEMPESO)
public class ValorLogicoUsuarioConvidadoProspectosRelacionamentoSemPeso
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoUsuarioConvidadoProspectosRelacionamentoSemPeso(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}