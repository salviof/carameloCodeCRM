/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidorEmailAvancado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.CampoMapValores;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Caixa postal"}, plural = "Caixas postais")
public class CaixaPostal extends EntidadeORMNormal implements ItfServidorEmailAvancado {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String usuarioRecepcao;

    private String usuarioSMTP;
    private String senhaSMTP;
    private String senhaRecepcao;

    private int portaRecepcao;

    private int portaSMTP;

    private boolean usarSSLRecepcao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraUltimoEmailRecebido;
    @InfoCampoValidadorLogico()
    private String enderecoServidor;

    private boolean usarSSLSMTP;
    private boolean usarTSLSMTP;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "caixaPostal_UsuarioCRM",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "caixaPostal_id"))
    @InfoCampoValorLogico(nomeCalculo = "Usuários ")
    private List<CaixaPostal> usuarios;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, somenteLeitura = true)
    @ManyToOne(targetEntity = CaixaPostalEstatisticas.class, cascade = CascadeType.ALL)
    @InfoCampoValorLogico(nomeCalculo = "estatosticas")
    private CaixaPostalEstatisticas estatisticas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    private boolean ativo;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL)
    private String emailRemetente;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String nomeRemetente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuarioRecepcao() {
        return usuarioRecepcao;
    }

    public void setUsuarioRecepcao(String usuarioRecepcao) {
        this.usuarioRecepcao = usuarioRecepcao;
        this.setUsuarioSMTP(usuarioRecepcao);
    }

    public String getUsuarioSMTP() {
        return usuarioSMTP;
    }

    public void setUsuarioSMTP(String usuarioSMTP) {
        this.usuarioSMTP = usuarioSMTP;
    }

    public String getSenhaRecepcao() {
        return senhaRecepcao;
    }

    public void setSenhaRecepcao(String senhaRecepcao) {
        this.senhaRecepcao = senhaRecepcao;
    }

    public int getPortaRecepcao() {
        return portaRecepcao;
    }

    @Override
    public int portaRecepcao() {
        return ItfServidorEmailAvancado.super.portaRecepcao(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPortaRecepcao(int portaRecepcao) {
        this.portaRecepcao = portaRecepcao;
    }

    public boolean isUsarSSLRecepcao() {
        return usarSSLRecepcao;
    }

    public void setUsarSSLRecepcao(boolean usarSSLRecepcao) {
        this.usarSSLRecepcao = usarSSLRecepcao;
    }

    public Date getDataHoraUltimoEmailRecebido() {
        return dataHoraUltimoEmailRecebido;
    }

    public void setDataHoraUltimoEmailRecebido(Date dataHoraUltimoEmailRecebido) {
        this.dataHoraUltimoEmailRecebido = dataHoraUltimoEmailRecebido;
    }

    public String getEnderecoServidor() {
        return enderecoServidor;
    }

    public void setEnderecoServidor(String EnderecoServidor) {
        this.enderecoServidor = EnderecoServidor;
    }

    @Override
    public String getEmail() {
        return usuarioRecepcao;
    }

    @Override
    public String getSenha() {
        return senhaRecepcao;
    }

    public String getSenhaSMTP() {
        return senhaSMTP;
    }

    public void setSenhaSMTP(String senhaSMTP) {
        this.senhaSMTP = senhaSMTP;
    }

    public int getPortaSMTP() {
        return portaSMTP;
    }

    public void setPortaSMTP(int portaSMTP) {
        this.portaSMTP = portaSMTP;
    }

    @Override
    public String getFromEmail() {
        return usuarioRecepcao;
    }

    @Override
    public boolean isUsarTSLSMTP() {
        return ItfServidorEmailAvancado.super.isUsarTSLSMTP(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUsarSSLSMTP() {
        return usarSSLSMTP;
    }

    public void setUsarSSLSMTP(boolean usarSSLSMTP) {
        this.usarSSLSMTP = usarSSLSMTP;
    }

    public List<CaixaPostal> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<CaixaPostal> usuarios) {
        this.usuarios = usuarios;
    }

    public CampoMapValores getCamposEsperados() {
        return camposEsperados;
    }

    public void setCamposEsperados(CampoMapValores camposEsperados) {
        this.camposEsperados = camposEsperados;
    }

    public void setUsarTSLSMTP(boolean usarTSLSMTP) {
        this.usarTSLSMTP = usarTSLSMTP;

    }

    public CaixaPostalEstatisticas getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(CaixaPostalEstatisticas estatisticas) {
        this.estatisticas = estatisticas;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    @Override
    public String getRemetenteNome() {
        return nomeRemetente;
    }

    @Override
    public String getFromNome() {
        return nomeRemetente;
    }

}
