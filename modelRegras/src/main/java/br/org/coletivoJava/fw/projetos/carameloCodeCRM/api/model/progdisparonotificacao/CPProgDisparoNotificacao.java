package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.progdisparonotificacao;

import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.transporte.ProgDisparoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ProgDisparoNotificacao.class)
public enum CPProgDisparoNotificacao {
	_DATAHORAPROGRAMADA;

	public static final String datahoraprogramada = "dataHoraProgramada";
}