package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EscopoPesqHorarioPublicado.class)
public enum ValidadoresEscopoPesqHorarioPublicado {
	DATAINICIAL, ATIVO, DIASEMANASEGUNDA, DIASEMANATERCA, DIASEMANAQUARTA, DIASEMANAQUINTA, DIASEMANASEXTA, DIASEMANASABADO, DIASEMANADOMINGO
}