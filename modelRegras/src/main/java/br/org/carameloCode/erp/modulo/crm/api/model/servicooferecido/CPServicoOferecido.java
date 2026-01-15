package br.org.carameloCode.erp.modulo.crm.api.model.servicooferecido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.ServicoOferecido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ServicoOferecido.class)
public enum CPServicoOferecido {
	_ID, _NOME, _DESCRICAO, _TIPOSERVICO, _PROSPECTO, _VALORSETUP, _VALORMENSAL, _ORCAMENTO, _SERVICOATIVO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String tiposervico = "tipoServico";
	public static final String prospecto = "prospecto";
	public static final String valorsetup = "valorSetup";
	public static final String valormensal = "valorMensal";
	public static final String orcamento = "orcamento";
	public static final String servicoativo = "servicoAtivo";
}