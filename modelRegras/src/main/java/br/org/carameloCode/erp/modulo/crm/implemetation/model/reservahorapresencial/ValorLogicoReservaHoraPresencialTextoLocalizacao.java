package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial.ValorLogicoReservaHorarioEncontroPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial.ValoresLogicosReservaHorarioEncontroPresencial;

@ValorLogicoReservaHorarioEncontroPresencial(calculo = ValoresLogicosReservaHorarioEncontroPresencial.TEXTOLOCALIZACAO)
public class ValorLogicoReservaHoraPresencialTextoLocalizacao
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoReservaHoraPresencialTextoLocalizacao(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}