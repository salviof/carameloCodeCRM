/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.cliente;

import java.util.List;

/**
 *
 * @author sfurbino
 */
public class Cliente {

    private String mailChimpCode;
    private String facebookPixelID;
    private String googleAnaliticsID;
    private String nome;
    private String dominio;
    private List<String> conteudos;

    public String getMailChimpCode() {
        return mailChimpCode;
    }

    public void setMailChimpCode(String mailChimpCode) {
        this.mailChimpCode = mailChimpCode;
    }

    public String getFacebookPixelID() {
        return facebookPixelID;
    }

    public void setFacebookPixelID(String facebookPixelID) {
        this.facebookPixelID = facebookPixelID;
    }

    public String getGoogleAnaliticsID() {
        return googleAnaliticsID;
    }

    public void setGoogleAnaliticsID(String googleAnaliticsID) {
        this.googleAnaliticsID = googleAnaliticsID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public List<String> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<String> conteudos) {
        this.conteudos = conteudos;
    }

}
