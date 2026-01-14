package br.org.carameloCode.erp.modulo.crm.api.model.caixapostalestatisticas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostalEstatisticas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = CaixaPostalEstatisticas.class)
public enum ValoresLogicosCaixaPostalEstatisticas {
	QUANTIDADENAOLIDOCONHECIDO, QUANTIDADENAOLIDODESCONHECIDO, QUANTIDADENAOLIDOPESSOAL
}