package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuarioconvidado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado.ValorLogicoUsuarioConvidado;
import br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado.ValoresLogicosUsuarioConvidado;

@ValorLogicoUsuarioConvidado(calculo = ValoresLogicosUsuarioConvidado.CAIXASPOSTAIS)
public class ValorLogicoUsuarioConvidadoCaixasPostais
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoUsuarioConvidadoCaixasPostais(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}