package br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoDadoCrmLinkIntegracao.class)
public enum ValidadoresTipoDadoCrmLinkIntegracao {
	NOMECLASSELOGICA, FABRICATIPOATRIBUTO, VALORPADRAO
}