package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmligacaorealizada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmligacaorealizada.ValorLogicoAtividadeCrmLigacaoRealizada;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmligacaorealizada.ValoresLogicosAtividadeCrmLigacaoRealizada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.AtividadeCrmLigacaoRealizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoAtividadeCrmLigacaoRealizada(calculo = ValoresLogicosAtividadeCrmLigacaoRealizada.DADOSREVISADOS)
public class ValorLogicoAtividadeCrmLigacaoRealizadaDadosRevisados
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoAtividadeCrmLigacaoRealizadaDadosRevisados(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public AtividadeCrmLigacaoRealizada getAtividadeCrmLigacaoRealizada() {
		return (AtividadeCrmLigacaoRealizada) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}