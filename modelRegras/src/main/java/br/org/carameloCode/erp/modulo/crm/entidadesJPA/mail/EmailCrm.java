/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.comunicacao.ComoEmailSimples;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author salviofurbino
 * @since 18/04/2019
 * @version 1.0
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEmail")
@InfoObjetoSB(tags = {"e-mail "}, plural = "e-mails")
@EntityListeners(ListenerEntidadePadrao.class)
@Entity
public class EmailCrm extends EntidadeORMNormal implements ComoEmailSimples {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue
    private Long id;

    public EmailCrm() {
    }

    public EmailCrm(
            String assunto,
            String emailsParaMensagemOriginal,
            String emailsCCMensagemOriginal,
            String emailsCCOMensagemOriginal,
            String emailDeMensagemOriginal) {
        this.assunto = assunto;
        this.emailsParaMensagemOriginal = emailsParaMensagemOriginal;
        this.emailsCCMensagemOriginal = emailsCCMensagemOriginal;
        this.emailsCCOMensagemOriginal = emailsCCOMensagemOriginal;
        this.emailDeMensagemOriginal = emailDeMensagemOriginal;

    }

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    @ManyToOne(targetEntity = EmailCrm.class)
    @InfoCampoValidadorLogico()
    private EmailCrm emailSolicitante;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEmail;
    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, label = "Texto do Email")
    @Column(columnDefinition = "LONGTEXT")
    @InfoCampoValorLogico(nomeCalculo = "Processamento de texto")
    private String texto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Assunto do e-mail", valorMinimo = 3
    //obrigatorio = true
    )
    //    @Min(10) Minimo retirado pois dificulta questões de gestão de estado enquanto no modo rascunho
    @InfoCampoValidadorLogico(descricao = "Validação de assunto, limite 10 palavras para envio")
    private String assunto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @Column(length = 5000)
    private String emailsParaMensagemOriginal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String emailsCCMensagemOriginal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String emailsCCOMensagemOriginal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String emailDeMensagemOriginal;

    @ManyToOne(targetEntity = Pessoa.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, label = "Prospecto")
    private Pessoa prospecto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Recebido")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailRecebido")
    private boolean umEmailRecebido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "É um email de atividade?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailDeAtividade")
    private boolean umEmailDeAtividade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "É um email no modo rascunho?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umaEmailModoRascunho")
    private boolean umEmailModoRascunho;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "É um email privado?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailPrivado")
    private boolean umEmailPrivado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "É um email Compatilhado")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailCompartilhado")
    private boolean umEmailCompartilhado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "É um email de Prospecto?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailDeProspecto")
    private boolean umEmailDeProspecto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Parte de conversa externa?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailPublico")
    private boolean umaConversaExterna;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Parte de conversa Interna?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailInterno")
    private boolean umaConversaInterna;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "É um e-mail resposta?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailResposta")
    private boolean umEmailResposta;
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "É um e-mal Recebido?")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailPassivo")
    private boolean umEmailPassivo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "é um e-mail Ativo")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "umEmailAtivo")
    private boolean umEmailAtivo;

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private UsuarioSB usuarioCriou;

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    private UsuarioSB usuarioEditou;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataHoraArmazenamento;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO)
    private Date dataHoraUltimaAlteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, somenteLeitura = true)
    @InfoCampoValorLogico(nomeCalculo = "Arquivos anexados")
    @Transient
    private List<ArquivoAnexado> arquivos;

    @InfoCampoValorLogico(nomeCalculo = "Conteudo html Processado")
    @InfoCampo(label = "Conteúdo do e-mail")
    @Transient
    private String conteudoHtmlProcessado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    @InfoCampoValorLogico(nomeCalculo = "Icone TipoEmail")
    private String iconeTipoEmail;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    @InfoCampoValorLogico(nomeCalculo = "Icone Alerta")
    private String iconeAlerta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    @InfoCampoValorLogico(nomeCalculo = "Cor Tipo Email")
    private String corTipoEmail;

    @ManyToOne(targetEntity = CaixaPostal.class)
    private CaixaPostal caixaPostal;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "acoes disponíveis")
    private List<AcaoDoSistema> acoesDisponiveis;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public UsuarioSB getUsuarioCriou() {
        return usuarioCriou;
    }

    public void setUsuarioCriou(UsuarioSB usuarioCriou) {
        this.usuarioCriou = usuarioCriou;
    }

    public UsuarioSB getUsuarioEditou() {
        return usuarioEditou;
    }

    public void setUsuarioEditou(UsuarioSB usuarioEditou) {
        this.usuarioEditou = usuarioEditou;
    }

    public Date getDataHoraArmazenamento() {
        return dataHoraArmazenamento;
    }

    public void setDataHoraArmazenamento(Date dataHoraArmazenamento) {
        this.dataHoraArmazenamento = dataHoraArmazenamento;
    }

    public Date getDataHoraUltimaAlteracao() {
        return dataHoraUltimaAlteracao;
    }

    public void setDataHoraUltimaAlteracao(Date dataHoraUltimaAlteracao) {
        this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
    }

    public Pessoa getProspecto() {

        return prospecto;
    }

    public void setProspecto(Pessoa prospecto) {
        this.prospecto = prospecto;
    }

    @Override
    public String getTextoMensagem() {
        return texto;
    }

    @Override
    public String getEmailsDestinatarios() {
        return emailsParaMensagemOriginal;
    }

    @Override
    public String getEmailRemetente() {
        return emailsParaMensagemOriginal;
    }

    @Override
    public Date getDataHoraEmailArmazenado() {
        return dataHoraArmazenamento;
    }

    public String getEmailsParaMensagemOriginal() {
        return emailsParaMensagemOriginal;
    }

    public void setEmailsParaMensagemOriginal(String emailsParaMensagemOriginal) {
        this.emailsParaMensagemOriginal = emailsParaMensagemOriginal;
    }

    public String getEmailsCCMensagemOriginal() {
        return emailsCCMensagemOriginal;
    }

    public void setEmailsCCMensagemOriginal(String emailsCCMensagemOriginal) {
        this.emailsCCMensagemOriginal = emailsCCMensagemOriginal;
    }

    public String getEmailsCCOMensagemOriginal() {
        return emailsCCOMensagemOriginal;
    }

    public void setEmailsCCOMensagemOriginal(String emailsCCOMensagemOriginal) {
        this.emailsCCOMensagemOriginal = emailsCCOMensagemOriginal;
    }

    public String getEmailDeMensagemOriginal() {
        return emailDeMensagemOriginal;
    }

    public void setEmailDeMensagemOriginal(String emailDeMensagemOriginal) {
        this.emailDeMensagemOriginal = emailDeMensagemOriginal;
    }

    public boolean isUmEmailRecebido() {
        return umEmailRecebido;
    }

    public void setUmEmailRecebido(boolean umEmailRecebido) {
        this.umEmailRecebido = umEmailRecebido;
    }

    public boolean isUmEmailDeAtividade() {
        umEmailDeAtividade = (this instanceof EnvioEmailAtividade);
        return umEmailDeAtividade;
    }

    public void setUmEmailDeAtividade(boolean umEmailDeAtividade) {
        this.umEmailDeAtividade = umEmailDeAtividade;
    }

    public boolean isUmEmailModoRascunho() {
        return umEmailModoRascunho;
    }

    public void setUmEmailModoRascunho(boolean umEmailModoRascunho) {
        this.umEmailModoRascunho = umEmailModoRascunho;
    }

    public boolean isUmEmailPrivado() {
        return umEmailPrivado;
    }

    public void setUmEmailPrivado(boolean umEmailPrivado) {
        this.umEmailPrivado = umEmailPrivado;
    }

    public boolean isUmEmailCompartilhado() {
        return umEmailCompartilhado;
    }

    public void setUmEmailCompartilhado(boolean umEmailCompartilhado) {
        this.umEmailCompartilhado = umEmailCompartilhado;
    }

    public boolean isUmEmailResposta() {
        return umEmailResposta;
    }

    public void setUmEmailResposta(boolean umEmailResposta) {
        this.umEmailResposta = umEmailResposta;
    }

    public boolean isUmEmailPassivo() {
        return umEmailPassivo;
    }

    public void setUmEmailPassivo(boolean umEmailPassivo) {
        this.umEmailPassivo = umEmailPassivo;
    }

    public boolean isUmEmailAtivo() {
        return umEmailAtivo;
    }

    public void setUmEmailAtivo(boolean umEmailAtivo) {
        this.umEmailAtivo = umEmailAtivo;
    }

    /**
     *
     * Um e-mail que foi solicitado por outro e-mail configura em um e-mail
     * passivo, no email ativo o email solicitante é sempre nulo, e ao
     * obrigatório no e-mail passivo
     *
     * @return
     */
    public EmailCrm getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(EmailCrm emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public boolean isUmEmailDeProspecto() {
        return umEmailDeProspecto;
    }

    public void setUmEmailDeProspecto(boolean umEmailDeProspecto) {
        this.umEmailDeProspecto = umEmailDeProspecto;
    }

    public boolean isUmaConversaExterna() {
        return umaConversaExterna;
    }

    public void setUmaConversaExterna(boolean umaConversaExterna) {
        this.umaConversaExterna = umaConversaExterna;
    }

    public boolean isUmaConversaInterna() {
        return umaConversaInterna;
    }

    public void setUmaConversaInterna(boolean umaConversaInterna) {
        this.umaConversaInterna = umaConversaInterna;
    }

    public EnvioEmail getComoEnvioEmail() {
        try {
            return (EnvioEmail) this;
        } catch (Throwable t) {
            return null;
        }
    }

    public EmailRecebido getComoEmailRecebido() {
        try {
            return (EmailRecebido) this;
        } catch (Throwable t) {
            return null;
        }
    }

    public EnvioEmailAtividade getComoEmailDeAtividade() {
        try {
            return (EnvioEmailAtividade) this;
        } catch (Throwable t) {
            return null;
        }
    }

    public List<ArquivoAnexado> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<ArquivoAnexado> arquivos) {
        this.arquivos = arquivos;
    }

    public String getConteudoHtmlProcessado() {
        return conteudoHtmlProcessado;
    }

    public void setConteudoHtmlProcessado(String conteudoHtmlProcessado) {
        this.conteudoHtmlProcessado = conteudoHtmlProcessado;
    }

    public String getIconeTipoEmail() {
        return iconeTipoEmail;
    }

    public void setIconeTipoEmail(String iconeTipoEmail) {
        this.iconeTipoEmail = iconeTipoEmail;
    }

    public String getCorTipoEmail() {
        return corTipoEmail;
    }

    public void setCorTipoEmail(String corTipoEmail) {
        this.corTipoEmail = corTipoEmail;
    }

    public CaixaPostal getCaixaPostal() {
        return caixaPostal;
    }

    public void setCaixaPostal(CaixaPostal caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    @Override
    public List<ComoAcaoDoSistema> getAcoesDisponiveis() {
        return (List) acoesDisponiveis;
    }

    public void setAcoesDisponiveis(List<AcaoDoSistema> acoesDisponiveis) {
        this.acoesDisponiveis = acoesDisponiveis;
    }

    public String getIconeAlerta() {
        return iconeAlerta;
    }

    public void setIconeAlerta(String iconeAlerta) {
        this.iconeAlerta = iconeAlerta;
    }

}
