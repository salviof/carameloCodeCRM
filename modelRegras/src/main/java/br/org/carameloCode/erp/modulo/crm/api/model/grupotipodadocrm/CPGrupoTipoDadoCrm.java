package br.org.carameloCode.erp.modulo.crm.api.model.grupotipodadocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.GrupoTipoDadoCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = GrupoTipoDadoCrm.class)
public enum CPGrupoTipoDadoCrm {
	_ID, _TIPOSDADOS, _NOME;

	public static final String id = "id";
	public static final String tiposdados = "tiposDados";
	public static final String nome = "nome";
}