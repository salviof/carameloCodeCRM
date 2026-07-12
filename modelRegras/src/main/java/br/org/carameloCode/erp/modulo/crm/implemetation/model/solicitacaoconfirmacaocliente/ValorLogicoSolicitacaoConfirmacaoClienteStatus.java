package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValorLogicoSolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaocliente.ValoresLogicosSolicitacaoConfirmacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoConfirmacaoCliente(calculo = ValoresLogicosSolicitacaoConfirmacaoCliente.STATUS)
public class ValorLogicoSolicitacaoConfirmacaoClienteStatus
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoConfirmacaoClienteStatus(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitacaoConfirmacaoCliente getSolicitacaoConfirmacaoCliente() {
		return (SolicitacaoConfirmacaoCliente) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}