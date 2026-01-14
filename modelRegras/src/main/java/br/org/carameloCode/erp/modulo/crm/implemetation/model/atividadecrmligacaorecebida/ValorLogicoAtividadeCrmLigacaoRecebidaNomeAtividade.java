package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmligacaorecebida;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmligacaorecebida.ValorLogicoAtividadeCrmLigacaoRecebida;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmligacaorecebida.ValoresLogicosAtividadeCrmLigacaoRecebida;

@ValorLogicoAtividadeCrmLigacaoRecebida(calculo = ValoresLogicosAtividadeCrmLigacaoRecebida.NOMEATIVIDADE)
public class ValorLogicoAtividadeCrmLigacaoRecebidaNomeAtividade
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoAtividadeCrmLigacaoRecebidaNomeAtividade(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}