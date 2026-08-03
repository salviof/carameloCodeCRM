package br.org.carameloCode.erp.modulo.crm.api.model.reservahorariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHorarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ReservaHorarioCRM.class)
public enum ValoresLogicosReservaHorarioCRM {
	DESCRICAO, VALORRESERVA, CHAVEPIX, FOIPAGO, CODIGORESERVA, DISPONIVELPARACONFIRMACAO
}