package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EscopoPesquisaMelhorHorario.class)
public enum ValoresLogicosEscopoPesquisaMelhorHorario {
	ATENDENTESPOSSIVEIS, QTDRESERVASREALIZADAS, QTDRESERVASDISPONIVEIS, DATAINICIAL, DATAHORAMAXIMOPESQUISA, ATIVO, TOKENDEACESSO, DIASEMANASEGUNDA, DIASEMANATERCA, DIASEMANAQUARTA, DIASEMANAQUINTA, DIASEMANASEXTA, DIASEMANASABADO, DIASEMANADOMINGO
}