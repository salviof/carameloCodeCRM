/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author salviofurbino
 * @since 11/05/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(tags = {"Controle Caixa de Correio"}, plural = "Controles de caixa de correio")
public class ControleCaixaDeEntrada extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @Column(nullable = false)
    private String email;

    private String nomePasta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @Column(nullable = false)
    private String codigoPrimeiroEmail;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @Column(nullable = false)
    private String codigoUltimoEmail;
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    @Column(nullable = false)
    private int quantidadeEmails;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean umaCaixaDeSapan;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "usuariPrincipal")
    private UsuarioCRM usuarioPrincipal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoPrimeiroEmail() {
        return codigoPrimeiroEmail;
    }

    public void setCodigoPrimeiroEmail(String codigoPrimeiroEmail) {
        this.codigoPrimeiroEmail = codigoPrimeiroEmail;
    }

    public String getCodigoUltimoEmail() {
        return codigoUltimoEmail;
    }

    public void setCodigoUltimoEmail(String codigoUltimoEmail) {
        this.codigoUltimoEmail = codigoUltimoEmail;
    }

    public int getQuantidadeEmails() {
        return quantidadeEmails;
    }

    public void setQuantidadeEmails(int quantidadeEmails) {
        this.quantidadeEmails = quantidadeEmails;
    }

    public String getNomePasta() {
        return nomePasta;
    }

    public void setNomePasta(String nomePasta) {
        this.nomePasta = nomePasta;
    }

    public boolean isUmaCaixaDeSapan() {
        return umaCaixaDeSapan;
    }

    public void setUmaCaixaDeSapan(boolean umaCaixaDeSapan) {
        this.umaCaixaDeSapan = umaCaixaDeSapan;
    }

    public UsuarioCRM getUsuarioPrincipal() {
        return usuarioPrincipal;
    }

    public void setUsuarioPrincipal(UsuarioCRM usuarioPrincipal) {
        this.usuarioPrincipal = usuarioPrincipal;
    }

}
