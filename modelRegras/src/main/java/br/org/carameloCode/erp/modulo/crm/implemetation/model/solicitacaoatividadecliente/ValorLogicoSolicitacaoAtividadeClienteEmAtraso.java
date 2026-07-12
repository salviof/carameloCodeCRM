package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadecliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValorLogicoSolicitacaoAtividadeCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadecliente.ValoresLogicosSolicitacaoAtividadeCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtividadeCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAtividadeCliente(calculo = ValoresLogicosSolicitacaoAtividadeCliente.EMATRASO)
public class ValorLogicoSolicitacaoAtividadeClienteEmAtraso
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoAtividadeClienteEmAtraso(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitacaoAtividadeCliente getSolicitacaoAtividadeCliente() {
		return (SolicitacaoAtividadeCliente) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}