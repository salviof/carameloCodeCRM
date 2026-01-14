package br.org.carameloCode.erp.modulo.crm.api.model.statusintegracao;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.StatusIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = StatusIntegracao.class)
public enum CPStatusIntegracao {
	_PROPRIEDASDES, _ID, _NOMEINTEGRACAO, _SISTEMAFOIINTEGRADO, _USUARIOFOIINTEGRADO, _ICONE, _DIAGNOSTICO, _CAMINHOARQUIVOCONFIG, _INTEGRACAO;

	public static final String propriedasdes = "propriedasdes";
	public static final String id = "id";
	public static final String nomeintegracao = "nomeIntegracao";
	public static final String sistemafoiintegrado = "sistemaFoiIntegrado";
	public static final String usuariofoiintegrado = "usuarioFoiIntegrado";
	public static final String icone = "icone";
	public static final String diagnostico = "diagnostico";
	public static final String caminhoarquivoconfig = "caminhoArquivoConfig";
	public static final String integracao = "integracao";
}