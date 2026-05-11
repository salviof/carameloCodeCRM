package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorariocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariocrm.ValidadorReservaHorarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariocrm.ValidadoresReservaHorarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorReservaHorarioCRM(validador = ValidadoresReservaHorarioCRM.INICIORESERVAATENDENTE)
public class ValidacaoReservaHorarioCRMInicioReservaAtendente
		extends
			ValidacaoGenerica<ReservaHorarioCRM> {

	public ValidacaoReservaHorarioCRMInicioReservaAtendente(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode
				.getServicoMensagemFireForget()
				.enviarMsgErroAoUsuario(
						"A Validação do campo  Inicio Reserva Atendente não foi implementada");
		return new ArrayList<>();
	}

	public ReservaHorarioCRM getReservaHorarioCRM() {
		return getObjetoDoAtributo();
	}
}