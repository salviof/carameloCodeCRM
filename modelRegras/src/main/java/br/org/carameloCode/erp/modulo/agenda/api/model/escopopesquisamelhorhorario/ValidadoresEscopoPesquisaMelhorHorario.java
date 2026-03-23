package br.org.carameloCode.erp.modulo.agenda.api.model.escopopesquisamelhorhorario;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EscopoPesquisaMelhorHorario.class)
public enum ValidadoresEscopoPesquisaMelhorHorario {
	DATAINICIAL, ATIVO, DIASEMANASEGUNDA, DIASEMANATERCA, DIASEMANAQUARTA, DIASEMANAQUINTA, DIASEMANASEXTA, DIASEMANASABADO, DIASEMANADOMINGO
}