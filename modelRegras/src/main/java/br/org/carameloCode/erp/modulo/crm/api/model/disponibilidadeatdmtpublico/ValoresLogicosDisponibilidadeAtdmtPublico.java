package br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DisponibilidadeAtdmtPublico.class)
public enum ValoresLogicosDisponibilidadeAtdmtPublico {
	DIASEMANASEGUNDA, DIASEMANATERCA, DIASEMANAQUARTA, DIASEMANAQUINTA, DIASEMANASEXTA, DIASEMANASABADO, DIASEMANADOMINGO, LISTADEATENDENTESPOSSIVEIS
}