package br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoFormulario.class)
public enum CPTipoFormulario {
	_ID, _NOME, _URLPUBLICA, _CODIGOTYPEBOT, _URLSERVICOTYPEBOT, _INTEGRARDADOS, _REPROCESSARQUANDOHOUVERERRODADOS, _NOTIFICARATENDENTE, _USUARIOPADRAO, _ORIGEMPADRAO, _TIPOEMPRESA, _RESPOSTAS, _ULTIMAS50RESPCODIGOS;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String urlpublica = "urlPublica";
	public static final String codigotypebot = "codigoTypebot";
	public static final String urlservicotypebot = "urlServicoTypebot";
	public static final String integrardados = "integrarDados";
	public static final String reprocessarquandohouvererrodados = "reprocessarQuandoHouverErroDados";
	public static final String notificaratendente = "notificarAtendente";
	public static final String usuariopadrao = "usuarioPadrao";
	public static final String origempadrao = "origemPadrao";
	public static final String tipoempresa = "tipoEmpresa";
	public static final String respostas = "respostas";
	public static final String ultimas50respcodigos = "ultimas50RespCodigos";
}