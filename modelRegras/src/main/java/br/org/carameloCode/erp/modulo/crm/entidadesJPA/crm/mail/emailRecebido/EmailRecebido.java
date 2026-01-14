/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexadoEmail.ArquivoAnexadoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.LogEmailRecebidoLido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.categoriaMailRecebido.CategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmailObjetos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringsExtrator;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.comunicacao.ComoEmailSimples;
import java.util.Date;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(plural = "e-mails rescebidos", tags = {"Email recebido"})
public class EmailRecebido extends EmailCrm implements ComoEmailSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL, label = "Remetente")
    private String remetente;
    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL)
    private String destinatario;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)

    @Column(unique = true)
    private String codigoEmailServidor;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEnvio;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraRecebimentoServerMail;

    @ManyToOne(targetEntity = CategoriaEmailRecebido.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private CategoriaEmailRecebido categoriaEmailRecebido;

    @OneToMany(targetEntity = ArquivoAnexadoEmailRecebido.class, mappedBy = "emailCrm", cascade = {CascadeType.REMOVE})
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ArquivoAnexadoEmailRecebido> arquivosRecebidos;

    @ManyToOne(targetEntity = ContatoProspecto.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, label = "Prospecto")
    private ContatoProspecto contato;

    @InfoCampoValorLogico(nomeCalculo = "lido por mim")
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean foiLidoPorUsuarioDestinatario;

    @InfoCampoValorLogico(nomeCalculo = "foi ignorado")
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean foiIgnorado;

    @InfoCampoValorLogico(nomeCalculo = "lido por", atualizarSempreQueSalvar = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @Transient
    private List<UsuarioCRM> foiLidoListaUsuarios;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<LogEmailRecebidoLido> logsLeituraMailRecebido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean consideradoSpan;

    public EmailRecebido(Message mensagem) throws MessagingException {

        super(mensagem.getSubject(),
                UtilCRCEmailObjetos.getAddresEmFormatoSeparadoVirgula(mensagem.getRecipients(Message.RecipientType.TO)),
                UtilCRCEmailObjetos.getAddresEmFormatoSeparadoVirgula(mensagem.getRecipients(Message.RecipientType.BCC)),
                UtilCRCEmailObjetos.getAddresEmFormatoSeparadoVirgula(mensagem.getRecipients(Message.RecipientType.BCC)),
                UtilCRCStringsExtrator.extrairEmail(mensagem.getFrom()[0].toString()));

    }

    @Deprecated
    public EmailRecebido() {

    }

    public List<ArquivoAnexadoEmailRecebido> getArquivosRecebidos() {
        return arquivosRecebidos;
    }

    public void setArquivosRecebidos(List<ArquivoAnexadoEmailRecebido> arquivosRecebidos) {
        this.arquivosRecebidos = arquivosRecebidos;
    }

    public String getCodigoEmailServidor() {
        return codigoEmailServidor;
    }

    public void setCodigoEmailServidor(String codigoEmailServidor) {
        this.codigoEmailServidor = codigoEmailServidor;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEmailDestinatario() {
        return destinatario;
    }

    public String getEmailRementente() {
        return remetente;
    }

    public ContatoProspecto getContato() {
        return contato;
    }

    public void setContato(ContatoProspecto contato) {
        this.contato = contato;
    }

    public boolean isEmailEnviadoUsuario() {
        return false;
    }

    public boolean isEmailRecebidoUsuario() {
        return true;
    }

    @Override
    public String getEmailsDestinatarios() {
        return destinatario;
    }

    @Override
    public String getEmailRemetente() {
        return remetente;
    }

    @Override
    public Date getDataHoraEmailArmazenado() {
        return getDataHoraInsercao();
    }

    @Override
    public String getTextoMensagem() {
        return getTexto();
    }

    public Date getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public void setDataHoraEnvio(Date dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    public Date getDataHoraRecebimentoServerMail() {
        return dataHoraRecebimentoServerMail;
    }

    public void setDataHoraRecebimentoServerMail(Date dataHoraRecebimentoServerMail) {
        this.dataHoraRecebimentoServerMail = dataHoraRecebimentoServerMail;
    }
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Texto formatado")
    @InfoCampo(label = "Texto formatado")
    private String textoComUrlImagemEmAnexo;

    public String getTextoComUrlImagemEmAnexo() {
        return textoComUrlImagemEmAnexo;
    }

    public void setTextoComUrlImagemEmAnexo(String textoComUrlImagemEmAnexo) {
        this.textoComUrlImagemEmAnexo = textoComUrlImagemEmAnexo;
    }

    public boolean isFoiLidoPorUsuarioDestinatario() {
        return foiLidoPorUsuarioDestinatario;
    }

    public void setFoiLidoPorUsuarioDestinatario(boolean foiLidoPorUsuarioDestinatario) {
        this.foiLidoPorUsuarioDestinatario = foiLidoPorUsuarioDestinatario;
    }

    public List<LogEmailRecebidoLido> getLogsLeituraMailRecebido() {
        return logsLeituraMailRecebido;
    }

    public void setLogsLeituraMailRecebido(List<LogEmailRecebidoLido> logsLeituraMailRecebido) {
        this.logsLeituraMailRecebido = logsLeituraMailRecebido;
    }

    public List<UsuarioCRM> getFoiLidoListaUsuarios() {
        return foiLidoListaUsuarios;
    }

    public void setFoiLidoListaUsuarios(List<UsuarioCRM> foiLidoListaUsuarios) {
        this.foiLidoListaUsuarios = foiLidoListaUsuarios;
    }

    public boolean isConsideradoSpan() {
        return consideradoSpan;
    }

    public void setConsideradoSpan(boolean consideradoSpan) {
        this.consideradoSpan = consideradoSpan;
    }

    public CategoriaEmailRecebido getCategoriaEmailRecebido() {
        return categoriaEmailRecebido;
    }

    public void setCategoriaEmailRecebido(CategoriaEmailRecebido categoriaEmailRecebido) {
        this.categoriaEmailRecebido = categoriaEmailRecebido;
    }

    public boolean isFoiIgnorado() {
        return foiIgnorado;
    }

    public void setFoiIgnorado(boolean foiIgnorado) {
        this.foiIgnorado = foiIgnorado;
    }

    public boolean isUmEmailDesclassificado() {
        if (categoriaEmailRecebido != null) {
            if (categoriaEmailRecebido.equals(FabCategoriaEmailRecebido.DESCLASSIFICADO.getRegistro())) {
                return true;
            }
        }
        return false;
    }

}
