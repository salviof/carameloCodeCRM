package br.org.carameloCode.erp.modulo.crm.api.model.funil;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.Funil;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Funil.class)
public enum CPFunil {
	_ID, _NOMEFUNIL, _DESCRICAO, _METAS;

	public static final String id = "id";
	public static final String nomefunil = "nomeFunil";
	public static final String descricao = "descricao";
	public static final String metas = "metas";
}