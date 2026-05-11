package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.reservahorario;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.reservahorario.ValidacaoReservaHorarioFinalReservaAtendente;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.reservahorario.ValidadorReservaHorario;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.reservahorario.ValidadoresReservaHorario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorReservaHorario(validador = ValidadoresReservaHorario.FINALRESERVAATENDENTE)
public class ValidacaoExtErpcaramReservaHorarioFinalReservaAtendente
		extends
			ValidacaoReservaHorarioFinalReservaAtendente {

	public ValidacaoExtErpcaramReservaHorarioFinalReservaAtendente(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		return super.validar(o);
	}

	public ReservaHorario getReservaHorario() {
		return getObjetoDoAtributo();
	}
}