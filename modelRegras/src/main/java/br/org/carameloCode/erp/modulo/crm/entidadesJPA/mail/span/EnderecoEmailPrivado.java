/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.span;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Privado", plural = "Privado")
public class EnderecoEmailPrivado extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @ManyToOne(targetEntity = CaixaPostal.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private CaixaPostal caixaPostal;
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    @ManyToOne(targetEntity = EmailRecebido.class)
    private EmailRecebido emailOrigem;
    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private UsuarioCRM usuarioRelato;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CaixaPostal getCaixaPostal() {
        return caixaPostal;
    }

    public void setCaixaPostal(CaixaPostal caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    public EmailRecebido getEmailOrigem() {
        return emailOrigem;
    }

    public void setEmailOrigem(EmailRecebido emailOrigem) {
        this.emailOrigem = emailOrigem;
    }

    public UsuarioCRM getUsuarioRelato() {
        return usuarioRelato;
    }

    public void setUsuarioRelato(UsuarioCRM usuarioRelato) {
        this.usuarioRelato = usuarioRelato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
