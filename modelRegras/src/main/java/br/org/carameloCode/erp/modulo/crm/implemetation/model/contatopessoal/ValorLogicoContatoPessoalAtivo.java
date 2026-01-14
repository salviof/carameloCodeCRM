package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoal;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValorLogicoContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValoresLogicosContatoPessoal;

@ValorLogicoContatoPessoal(calculo = ValoresLogicosContatoPessoal.ATIVO)
public class ValorLogicoContatoPessoalAtivo extends ValorLogicoCalculoGenerico {

	ValorLogicoContatoPessoalAtivo(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}