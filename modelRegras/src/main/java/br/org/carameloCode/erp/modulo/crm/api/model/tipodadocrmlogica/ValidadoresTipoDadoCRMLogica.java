package br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlogica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRMLogica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoDadoCRMLogica.class)
public enum ValidadoresTipoDadoCRMLogica {
	FABRICATIPOATRIBUTO, VALORPADRAO
}