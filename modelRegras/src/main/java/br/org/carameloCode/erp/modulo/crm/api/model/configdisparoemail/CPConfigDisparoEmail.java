package br.org.carameloCode.erp.modulo.crm.api.model.configdisparoemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.ConfigDisparoEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ConfigDisparoEmail.class)
public enum CPConfigDisparoEmail {
	_ID, _NOME;

	public static final String id = "id";
	public static final String nome = "nome";
}