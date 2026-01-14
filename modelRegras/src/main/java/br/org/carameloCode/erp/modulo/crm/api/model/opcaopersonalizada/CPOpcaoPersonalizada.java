package br.org.carameloCode.erp.modulo.crm.api.model.opcaopersonalizada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.opcaoPersonalizada.OpcaoPersonalizada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = OpcaoPersonalizada.class)
public enum CPOpcaoPersonalizada {
	_ID, _NOME, _DESCRICAO, _TIPODADO, _CODIGOGRUPOOPCOES;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String tipodado = "tipoDado";
	public static final String codigogrupoopcoes = "codigoGrupoOpcoes";
}