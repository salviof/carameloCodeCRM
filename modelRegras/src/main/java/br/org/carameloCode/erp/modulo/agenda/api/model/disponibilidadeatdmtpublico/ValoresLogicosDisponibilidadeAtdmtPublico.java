package br.org.carameloCode.erp.modulo.agenda.api.model.disponibilidadeatdmtpublico;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.DisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DisponibilidadeAtdmtPublico.class)
public enum ValoresLogicosDisponibilidadeAtdmtPublico {
	DIASEMANASEGUNDA, DIASEMANATERCA, DIASEMANAQUARTA, DIASEMANAQUINTA, DIASEMANASEXTA, DIASEMANASABADO, DIASEMANADOMINGO, LISTADEATENDENTESPOSSIVEIS
}