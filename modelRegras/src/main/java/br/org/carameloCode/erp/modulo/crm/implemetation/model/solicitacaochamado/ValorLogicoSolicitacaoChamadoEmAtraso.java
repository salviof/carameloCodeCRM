package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaochamado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValorLogicoSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaochamado.ValoresLogicosSolicitacaoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoChamado(calculo = ValoresLogicosSolicitacaoChamado.EMATRASO)
public class ValorLogicoSolicitacaoChamadoEmAtraso
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoChamadoEmAtraso(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitacaoChamado getSolicitacaoChamado() {
		return (SolicitacaoChamado) getCampoInst().getObjetoRaizDoAtributo();
	}
}