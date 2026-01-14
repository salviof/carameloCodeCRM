package br.org.carameloCode.erp.modulo.crm.api.model.tipoempresa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.TipoEmpresa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoEmpresa.class)
public enum CPTipoEmpresa {
	_ID, _NOME, _DESCRICAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
}