package br.org.carameloCode.erp.modulo.crm.api.model.cotatopessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoaJuridica.CotatoPessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CotatoPessoaJuridica.class)
public enum ValidadoresCotatoPessoaJuridica {
	NOME, EMAIL
}