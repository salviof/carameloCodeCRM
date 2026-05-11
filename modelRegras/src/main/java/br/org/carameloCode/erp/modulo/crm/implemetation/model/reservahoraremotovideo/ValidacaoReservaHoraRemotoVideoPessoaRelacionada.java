package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValidadorReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahoraremotovideo.ValidadoresReservaHoraRemotoVideo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorReservaHoraRemotoVideo(validador = ValidadoresReservaHoraRemotoVideo.PESSOARELACIONADA)
public class ValidacaoReservaHoraRemotoVideoPessoaRelacionada
		extends
			ValidacaoGenerica<ReservaHoraRemotoVideo> {

	public ValidacaoReservaHoraRemotoVideoPessoaRelacionada(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode
				.getServicoMensagemFireForget()
				.enviarMsgErroAoUsuario(
						"A Validação do campo  Pessoa Relacionada não foi implementada");
		return new ArrayList<>();
	}

	public ReservaHoraRemotoVideo getReservaHoraRemotoVideo() {
		return getObjetoDoAtributo();
	}
}