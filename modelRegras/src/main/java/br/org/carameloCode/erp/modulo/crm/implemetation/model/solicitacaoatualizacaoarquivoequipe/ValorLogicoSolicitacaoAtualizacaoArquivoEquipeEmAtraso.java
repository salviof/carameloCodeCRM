package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatualizacaoarquivoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValorLogicoSolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAtualizacaoArquivoEquipe(calculo = ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe.EMATRASO)
public class ValorLogicoSolicitacaoAtualizacaoArquivoEquipeEmAtraso
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoSolicitacaoAtualizacaoArquivoEquipeEmAtraso(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public SolicitArqAtualizacaoEqp getSolicitacaoAtualizacaoArquivoEquipe() {
		return (SolicitArqAtualizacaoEqp) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}