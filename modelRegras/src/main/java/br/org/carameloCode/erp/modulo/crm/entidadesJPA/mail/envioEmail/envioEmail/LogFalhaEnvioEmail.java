/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Logs de falha de envio", plural = "Logs de falha de envio")
public class LogFalhaEnvioEmail extends EntidadeORMNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FabTipoLogFalhaEnvioEmail tipoLog;
    private String mensagemRetorno;
    private String cabecalhoMensagemRetorno;
    private String obervacoes;
    private String mensagemAoUsuario;

    @ManyToOne(targetEntity = EnvioEmail.class)
    private EnvioEmail email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FabTipoLogFalhaEnvioEmail getTipoLog() {
        return tipoLog;
    }

    public void setTipoLog(FabTipoLogFalhaEnvioEmail tipoLog) {
        this.tipoLog = tipoLog;
    }

    public String getMensagemRetorno() {
        return mensagemRetorno;
    }

    public void setMensagemRetorno(String mensagemRetorno) {
        this.mensagemRetorno = mensagemRetorno;
    }

    public String getCabecalhoMensagemRetorno() {
        return cabecalhoMensagemRetorno;
    }

    public void setCabecalhoMensagemRetorno(String cabecalhoMensagemRetorno) {
        this.cabecalhoMensagemRetorno = cabecalhoMensagemRetorno;
    }

    public String getObervacoes() {
        return obervacoes;
    }

    public void setObervacoes(String obervacoes) {
        this.obervacoes = obervacoes;
    }

    public String getMensagemAoUsuario() {
        return mensagemAoUsuario;
    }

    public void setMensagemAoUsuario(String mensagemAoUsuario) {
        this.mensagemAoUsuario = mensagemAoUsuario;
    }

    public EnvioEmail getEmail() {
        return email;
    }

    public void setEmail(EnvioEmail email) {
        this.email = email;
    }

}
