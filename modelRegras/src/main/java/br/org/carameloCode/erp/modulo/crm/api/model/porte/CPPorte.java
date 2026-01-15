package br.org.carameloCode.erp.modulo.crm.api.model.porte;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.porteEmpresa.Porte;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Porte.class)
public enum CPPorte {
	_ID, _NOME, _DESCRICAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
}