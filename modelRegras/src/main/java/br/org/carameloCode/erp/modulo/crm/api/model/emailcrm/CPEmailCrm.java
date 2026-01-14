package br.org.carameloCode.erp.modulo.crm.api.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EmailCrm.class)
public enum CPEmailCrm {
	_ID, _EMAILSOLICITANTE, _TIPOEMAIL, _TEXTO, _ASSUNTO, _EMAILSPARAMENSAGEMORIGINAL, _EMAILSCCMENSAGEMORIGINAL, _EMAILSCCOMENSAGEMORIGINAL, _EMAILDEMENSAGEMORIGINAL, _PROSPECTO, _UMEMAILRECEBIDO, _UMEMAILDEATIVIDADE, _UMEMAILMODORASCUNHO, _UMEMAILPRIVADO, _UMEMAILCOMPARTILHADO, _UMEMAILDEPROSPECTO, _UMACONVERSAEXTERNA, _UMACONVERSAINTERNA, _UMEMAILRESPOSTA, _UMEMAILPASSIVO, _UMEMAILATIVO, _USUARIOCRIOU, _USUARIOEDITOU, _DATAHORAARMAZENAMENTO, _DATAHORAULTIMAALTERACAO, _ARQUIVOS, _CONTEUDOHTMLPROCESSADO, _ICONETIPOEMAIL, _ICONEALERTA, _CORTIPOEMAIL, _CAIXAPOSTAL, _ACOESDISPONIVEIS;

	public static final String id = "id";
	public static final String emailsolicitante = "emailSolicitante";
	public static final String tipoemail = "tipoEmail";
	public static final String texto = "texto";
	public static final String assunto = "assunto";
	public static final String emailsparamensagemoriginal = "emailsParaMensagemOriginal";
	public static final String emailsccmensagemoriginal = "emailsCCMensagemOriginal";
	public static final String emailsccomensagemoriginal = "emailsCCOMensagemOriginal";
	public static final String emaildemensagemoriginal = "emailDeMensagemOriginal";
	public static final String prospecto = "prospecto";
	public static final String umemailrecebido = "umEmailRecebido";
	public static final String umemaildeatividade = "umEmailDeAtividade";
	public static final String umemailmodorascunho = "umEmailModoRascunho";
	public static final String umemailprivado = "umEmailPrivado";
	public static final String umemailcompartilhado = "umEmailCompartilhado";
	public static final String umemaildeprospecto = "umEmailDeProspecto";
	public static final String umaconversaexterna = "umaConversaExterna";
	public static final String umaconversainterna = "umaConversaInterna";
	public static final String umemailresposta = "umEmailResposta";
	public static final String umemailpassivo = "umEmailPassivo";
	public static final String umemailativo = "umEmailAtivo";
	public static final String usuariocriou = "usuarioCriou";
	public static final String usuarioeditou = "usuarioEditou";
	public static final String datahoraarmazenamento = "dataHoraArmazenamento";
	public static final String datahoraultimaalteracao = "dataHoraUltimaAlteracao";
	public static final String arquivos = "arquivos";
	public static final String conteudohtmlprocessado = "conteudoHtmlProcessado";
	public static final String iconetipoemail = "iconeTipoEmail";
	public static final String iconealerta = "iconeAlerta";
	public static final String cortipoemail = "corTipoEmail";
	public static final String caixapostal = "caixaPostal";
	public static final String acoesdisponiveis = "acoesDisponiveis";
}