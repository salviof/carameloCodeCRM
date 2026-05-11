package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.reservahorario;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.reservahorario.ValorLogicoReservaHorarioDisponivelParaConfirmacao;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.reservahorario.ValorLogicoReservaHorario;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.reservahorario.ValoresLogicosReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoReservaHorario(calculo = ValoresLogicosReservaHorario.DISPONIVELPARACONFIRMACAO)
public class ValorLogicoExtErpcaramReservaHorarioDisponivelParaConfirmacao
		extends
			ValorLogicoReservaHorarioDisponivelParaConfirmacao {

	public ValorLogicoExtErpcaramReservaHorarioDisponivelParaConfirmacao(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}