package br.org.carameloCode.erp.modulo.crm.api.model.modeloemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ModeloEmail.class)
public enum ValoresLogicosModeloEmail {
	ASSUNTO, TEXTOMODELO
}