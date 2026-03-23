package br.org.carameloCode.erp.modulo.agenda.api.model.escopopesqhorariopublicado;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EscopoPesqHorarioPublicado.class)
public enum ValoresLogicosEscopoPesqHorarioPublicado {
	LINKDEACESSO, ATENDENTESPOSSIVEIS, QTDRESERVASREALIZADAS, QTDRESERVASDISPONIVEIS, DATAINICIAL, LISTAHORARIOSDISPONIVEIS, DISPONIBILIDADESDOESCOPO, DATAHORAMAXIMOPESQUISA, ATIVO, TOKENDEACESSO, DIASEMANASEGUNDA, DIASEMANATERCA, DIASEMANAQUARTA, DIASEMANAQUINTA, DIASEMANASEXTA, DIASEMANASABADO, DIASEMANADOMINGO
}