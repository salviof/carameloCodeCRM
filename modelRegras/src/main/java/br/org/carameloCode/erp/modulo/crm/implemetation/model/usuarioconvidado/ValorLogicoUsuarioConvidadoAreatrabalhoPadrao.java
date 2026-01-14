package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuarioconvidado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado.ValorLogicoUsuarioConvidado;
import br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado.ValoresLogicosUsuarioConvidado;

@ValorLogicoUsuarioConvidado(calculo = ValoresLogicosUsuarioConvidado.AREATRABALHOPADRAO)
public class ValorLogicoUsuarioConvidadoAreatrabalhoPadrao
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoUsuarioConvidadoAreatrabalhoPadrao(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}