package br.org.carameloCode.erp.modulo.crm.api.model.caixapostal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CaixaPostal.class)
public enum ValidadoresCaixaPostal {
	ENDERECOSERVIDOR
}