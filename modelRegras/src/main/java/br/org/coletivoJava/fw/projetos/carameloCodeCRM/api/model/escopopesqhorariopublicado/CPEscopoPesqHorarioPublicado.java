package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EscopoPesqHorarioPublicado.class)
public enum CPEscopoPesqHorarioPublicado {
	_PUBLICADO, _DATAHORATOKENPUBLICOEXPIRA, _TOKENPUBLICADO, _LINKDEACESSO, _PIXELGOOGLE, _PIXELFACEBOOK, _PIXELMAUTIC;

	public static final String publicado = "publicado";
	public static final String datahoratokenpublicoexpira = "dataHoraTokenPublicoExpira";
	public static final String tokenpublicado = "tokenPublicado";
	public static final String linkdeacesso = "linkDeAcesso";
	public static final String pixelgoogle = "pixelGoogle";
	public static final String pixelfacebook = "pixelFacebook";
	public static final String pixelmautic = "pixelMautic";
}