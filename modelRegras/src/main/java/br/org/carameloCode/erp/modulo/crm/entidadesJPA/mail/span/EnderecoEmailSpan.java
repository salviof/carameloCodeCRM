/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.span;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Spanner", plural = "Spanners")
public class EnderecoEmailSpan extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)

    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String emailRemetente;

    @ManyToOne(targetEntity = EmailRecebido.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private UsuarioCRM usuarioRelato;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String codigoEmailDefinidoSpan;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "bloquearDominio")
    private Boolean bloquearTodoDominio;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, somenteLeitura = true)
    @InfoCampoValorLogico(nomeCalculo = "bloquearDominio")
    private String dominioEmail;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuarioDefiniuSpan;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataHoraCadastroSpan;

    private String assuntoDefinidoSpan;
    private String ultimoAssunto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, somenteLeitura = true)
    private int quantidadeRecebida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioCRM getUsuarioRelato() {
        return usuarioRelato;
    }

    public void setUsuarioRelato(UsuarioCRM usuarioRelato) {
        this.usuarioRelato = usuarioRelato;
    }

    public int getQuantidadeRecebida() {
        return quantidadeRecebida;
    }

    public void setQuantidadeRecebida(int quantidadeRecebida) {
        this.quantidadeRecebida = quantidadeRecebida;
    }

    public String getUltimoAssunto() {
        return ultimoAssunto;
    }

    public void setUltimoAssunto(String ultimoAssunto) {
        this.ultimoAssunto = ultimoAssunto;
    }

    public String getCodigoEmailDefinidoSpan() {
        return codigoEmailDefinidoSpan;
    }

    public void setCodigoEmailDefinidoSpan(String codigoEmailDefinidoSpan) {
        this.codigoEmailDefinidoSpan = codigoEmailDefinidoSpan;
    }

    public String getAssuntoDefinidoSpan() {
        return assuntoDefinidoSpan;
    }

    public void setAssuntoDefinidoSpan(String assuntoDefinidoSpan) {
        this.assuntoDefinidoSpan = assuntoDefinidoSpan;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public Boolean getBloquearTodoDominio() {
        return bloquearTodoDominio;
    }

    public void setBloquearTodoDominio(Boolean bloquearTodoDominio) {
        this.bloquearTodoDominio = bloquearTodoDominio;
    }

    public String getDominioEmail() {
        return dominioEmail;
    }

    public void setDominioEmail(String dominioEmail) {
        this.dominioEmail = dominioEmail;
    }

    public UsuarioCRM getUsuarioDefiniuSpan() {
        return usuarioDefiniuSpan;
    }

    public void setUsuarioDefiniuSpan(UsuarioCRM usuarioDefiniuSpan) {
        this.usuarioDefiniuSpan = usuarioDefiniuSpan;
    }

    public Date getDataHoraCadastroSpan() {
        return dataHoraCadastroSpan;
    }

    public void setDataHoraCadastroSpan(Date dataHoraCadastroSpan) {
        this.dataHoraCadastroSpan = dataHoraCadastroSpan;
    }

}
