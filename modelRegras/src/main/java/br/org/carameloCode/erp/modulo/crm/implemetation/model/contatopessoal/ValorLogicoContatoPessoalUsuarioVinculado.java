package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoal;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValorLogicoContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValoresLogicosContatoPessoal;

@ValorLogicoContatoPessoal(calculo = ValoresLogicosContatoPessoal.USUARIOVINCULADO)
public class ValorLogicoContatoPessoalUsuarioVinculado
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoContatoPessoalUsuarioVinculado(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}