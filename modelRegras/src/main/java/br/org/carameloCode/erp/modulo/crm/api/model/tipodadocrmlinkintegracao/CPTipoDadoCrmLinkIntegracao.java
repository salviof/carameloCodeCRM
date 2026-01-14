package br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoDadoCrmLinkIntegracao.class)
public enum CPTipoDadoCrmLinkIntegracao {
	_IMAGEM, _NOMECLASSELOGICA, _MOSTRARPARACLIENTE;

	public static final String imagem = "imagem";
	public static final String nomeclasselogica = "nomeClasseLogica";
	public static final String mostrarparacliente = "mostrarParaCliente";
}