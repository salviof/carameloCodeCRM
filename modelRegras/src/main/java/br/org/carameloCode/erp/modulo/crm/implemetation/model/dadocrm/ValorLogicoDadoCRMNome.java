package br.org.carameloCode.erp.modulo.crm.implemetation.model.dadocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.dadocrm.ValorLogicoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.dadocrm.ValoresLogicosDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoDadoCRM(calculo = ValoresLogicosDadoCRM.NOME)
public class ValorLogicoDadoCRMNome extends ValorLogicoCalculoGenerico {

	public ValorLogicoDadoCRMNome(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public DadoCRM getDadoCRM() {
		return (DadoCRM) getCampoInst().getObjetoRaizDoAtributo();
	}
}