package br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = UsuarioConvidado.class)
public enum CPUsuarioConvidado {
	_LEADSCOMPARTILHADOS;

	public static final String leadscompartilhados = "leadsCompartilhados";
}