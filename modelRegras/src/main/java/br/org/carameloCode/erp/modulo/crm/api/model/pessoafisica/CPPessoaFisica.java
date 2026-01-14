package br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PessoaFisica.class)
public enum CPPessoaFisica {
	_CPF, _TIPOEMPRESA, _PORTE, _SITE;

	public static final String cpf = "cpf";
	public static final String tipoempresa = "tipoEmpresa";
	public static final String porte = "porte";
	public static final String site = "site";
}