package br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoDadoCRM.class)
public enum ValidadoresTipoDadoCRM {
	FABRICATIPOATRIBUTO, VALORPADRAO
}