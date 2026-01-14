package br.org.carameloCode.erp.modulo.crm.api.model.historicorelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = HistoricoRelacionamento.class)
public enum CPHistoricoRelacionamento {
	_ID, _DATAHORA, _DESCRICAO, _ATIVIDADEREFERENCIA, _PROSPECTO, _RELACIONAMENTOANTERIOR, _NOVORELACIONAMENTO, _USUARIO;

	public static final String id = "id";
	public static final String datahora = "dataHora";
	public static final String descricao = "descricao";
	public static final String atividadereferencia = "atividadeReferencia";
	public static final String prospecto = "prospecto";
	public static final String relacionamentoanterior = "relacionamentoAnterior";
	public static final String novorelacionamento = "novoRelacionamento";
	public static final String usuario = "usuario";
}