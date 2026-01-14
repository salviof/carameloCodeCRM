package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmlicacaorealizada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmlicacaorealizada.ValorLogicoAtividadeCrmLicacaoRealizada;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmlicacaorealizada.ValoresLogicosAtividadeCrmLicacaoRealizada;

@ValorLogicoAtividadeCrmLicacaoRealizada(calculo = ValoresLogicosAtividadeCrmLicacaoRealizada.ATIVIDADEMESMOTIPOABERTAUSUARIOLOGADO)
public class ValorLogicoAtividadeCrmLicacaoRealizadaAtividadeMesmoTipoAbertaUsuarioLogado
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoAtividadeCrmLicacaoRealizadaAtividadeMesmoTipoAbertaUsuarioLogado(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}