package br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PessoaJuridica.class)
public enum ValidadoresPessoaJuridica {
	SITE, EMAIL, RELACIONAMENTO, LOCALIZACAO, USUARIOSRESPONSAVEIS, RESPONSAVEL
}