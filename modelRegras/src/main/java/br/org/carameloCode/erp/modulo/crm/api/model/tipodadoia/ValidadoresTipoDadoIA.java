package br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoDadoIA.class)
public enum ValidadoresTipoDadoIA {
	FABRICATIPOATRIBUTO, VALORPADRAO
}