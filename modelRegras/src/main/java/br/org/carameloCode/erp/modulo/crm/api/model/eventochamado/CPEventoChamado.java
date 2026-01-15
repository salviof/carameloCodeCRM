package br.org.carameloCode.erp.modulo.crm.api.model.eventochamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.EventoChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EventoChamado.class)
public enum CPEventoChamado {
	_ID, _AGENTE, _DATAHORA, _DESCRICAO, _CHAMADO;

	public static final String id = "id";
	public static final String agente = "agente";
	public static final String datahora = "dataHora";
	public static final String descricao = "descricao";
	public static final String chamado = "chamado";
}