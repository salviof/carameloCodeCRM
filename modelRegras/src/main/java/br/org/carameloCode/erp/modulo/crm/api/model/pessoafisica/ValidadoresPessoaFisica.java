package br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PessoaFisica.class)
public enum ValidadoresPessoaFisica {
	SITE, EMAIL, RELACIONAMENTO, LOCALIZACAO, USUARIOSRESPONSAVEIS, RESPONSAVEL
}