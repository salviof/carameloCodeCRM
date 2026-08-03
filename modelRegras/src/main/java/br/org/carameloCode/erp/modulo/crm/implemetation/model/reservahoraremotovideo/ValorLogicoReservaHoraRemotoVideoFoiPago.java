package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValorLogicoReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValoresLogicosReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoReservaHoraRemotoVideo(calculo = ValoresLogicosReservaHoraRemotoVideo.FOIPAGO)
public class ValorLogicoReservaHoraRemotoVideoFoiPago
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoReservaHoraRemotoVideoFoiPago(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	public ReservaHoraRemotoVideo getReservaHoraRemotoVideo() {
		return (ReservaHoraRemotoVideo) getCampoInst()
				.getObjetoRaizDoAtributo();
	}
}