package br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.DisparoEmMassa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DisparoEmMassa.class)
public enum CPDisparoEmMassa {
	_ID, _NOME, _DATADISPARO, _TIPOMENSAGEM, _STATUS, _ENVIARPARACONTATOSSECUNDARIOS, _METARELACIONAMENTO, _RELACIONAMENTOS, _RELACIONAMENTOSDISPONIVEIS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String datadisparo = "dataDisparo";
	public static final String tipomensagem = "tipoMensagem";
	public static final String status = "status";
	public static final String enviarparacontatossecundarios = "enviarParaContatosSecundarios";
	public static final String metarelacionamento = "metaRelacionamento";
	public static final String relacionamentos = "relacionamentos";
	public static final String relacionamentosdisponiveis = "relacionamentosDisponiveis";
}