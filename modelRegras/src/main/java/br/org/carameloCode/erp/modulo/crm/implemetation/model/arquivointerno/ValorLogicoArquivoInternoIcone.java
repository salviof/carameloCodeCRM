package br.org.carameloCode.erp.modulo.crm.implemetation.model.arquivointerno;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivointerno.ValorLogicoArquivoInterno;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivointerno.ValoresLogicosArquivoInterno;

@ValorLogicoArquivoInterno(calculo = ValoresLogicosArquivoInterno.ICONE)
public class ValorLogicoArquivoInternoIcone extends ValorLogicoCalculoGenerico {

	ValorLogicoArquivoInternoIcone(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}