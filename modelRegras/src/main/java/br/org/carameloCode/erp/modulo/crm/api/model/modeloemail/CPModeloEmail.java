package br.org.carameloCode.erp.modulo.crm.api.model.modeloemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ModeloEmail.class)
public enum CPModeloEmail {
	_ID, _ASSUNTO, _DESCRICAO, _TEXTOMODELO, _USUARIOCRIOU, _USUARIOEDITOU, _DATAHORACRIOU, _DATAHORAEDITOU;

	public static final String id = "id";
	public static final String assunto = "assunto";
	public static final String descricao = "descricao";
	public static final String textomodelo = "textoModelo";
	public static final String usuariocriou = "usuariocriou";
	public static final String usuarioeditou = "usuarioEditou";
	public static final String datahoracriou = "dataHoraCriou";
	public static final String datahoraeditou = "dataHoraEditou";
}