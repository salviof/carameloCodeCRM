package br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoal.ContatoPessoal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ContatoPessoal.class)
public enum CPContatoPessoal {
	_RESPONSAVEL;

	public static final String responsavel = "responsavel";
}