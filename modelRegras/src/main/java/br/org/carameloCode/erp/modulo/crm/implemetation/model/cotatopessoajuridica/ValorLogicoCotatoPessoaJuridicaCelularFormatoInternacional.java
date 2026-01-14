package br.org.carameloCode.erp.modulo.crm.implemetation.model.cotatopessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica.ValorLogicoCotatoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica.ValoresLogicosCotatoPessoaJuridica;

@ValorLogicoCotatoPessoaJuridica(calculo = ValoresLogicosCotatoPessoaJuridica.CELULARFORMATOINTERNACIONAL)
public class ValorLogicoCotatoPessoaJuridicaCelularFormatoInternacional
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoCotatoPessoaJuridicaCelularFormatoInternacional(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}