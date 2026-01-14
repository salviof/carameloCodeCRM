package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatualizacaoarquivoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValorLogicoSolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe;

@ValorLogicoSolicitacaoAtualizacaoArquivoEquipe(calculo = ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe.NOME)
public class ValorLogicoSolicitacaoAtualizacaoArquivoEquipeNome
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoSolicitacaoAtualizacaoArquivoEquipeNome(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}