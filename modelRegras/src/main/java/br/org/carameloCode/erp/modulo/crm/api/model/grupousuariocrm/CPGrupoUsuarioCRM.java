package br.org.carameloCode.erp.modulo.crm.api.model.grupousuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.GrupoUsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = GrupoUsuarioCRM.class)
public enum CPGrupoUsuarioCRM {
	_MODULOPRINCIPAL, _URLCHATMATRIX;

	public static final String moduloprincipal = "moduloPrincipal";
	public static final String urlchatmatrix = "urlChatMatrix";
}