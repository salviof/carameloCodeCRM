/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Escopos publicados", tags = "Escopo publicado")
public class EscopoPesqHorarioPublicado extends EscopoPesquisaMelhorHorario {

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean publicado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, label = "Validade do Token")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraTokenPublicoExpira;

    @ManyToOne(targetEntity = TokenAcessoDinamico.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private TokenAcessoDinamico tokenPublicado;

    @ManyToOne(targetEntity = OrigemProspecto.class)
    @Transient
    private OrigemProspecto origem;

    @InfoCampoValorLogico(atualizarSempreQueSalvar = false, nomeCalculo = "Url compartilhamento")
    @InfoCampo(tipo = FabTipoAtributoObjeto.URL, somenteLeitura = true)
    private String linkDeAcesso;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, somenteLeitura = true)
    private String pixelGoogle;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, somenteLeitura = true)
    private String pixelFacebook;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, somenteLeitura = true)
    private String pixelMautic;

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public Date getDataHoraTokenPublicoExpira() {
        return dataHoraTokenPublicoExpira;
    }

    public void setDataHoraTokenPublicoExpira(Date dataHoraTokenPublicoExpira) {
        this.dataHoraTokenPublicoExpira = dataHoraTokenPublicoExpira;
    }

    public TokenAcessoDinamico getTokenPublicado() {
        return tokenPublicado;
    }

    public void setTokenPublicado(TokenAcessoDinamico tokenPublicado) {
        this.tokenPublicado = tokenPublicado;
    }

    public String getLinkDeAcesso() {
        return linkDeAcesso;
    }

    public void setLinkDeAcesso(String linkDeAcesso) {
        this.linkDeAcesso = linkDeAcesso;
    }

    public String getPixelGoogle() {
        return pixelGoogle;
    }

    public void setPixelGoogle(String pixelGoogle) {
        this.pixelGoogle = pixelGoogle;
    }

    public String getPixelFacebook() {
        return pixelFacebook;
    }

    public void setPixelFacebook(String pixelFacebook) {
        this.pixelFacebook = pixelFacebook;
    }

    public String getPixelMautic() {
        return pixelMautic;
    }

    public void setPixelMautic(String pixelMautic) {
        this.pixelMautic = pixelMautic;
    }

    public OrigemProspecto getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemProspecto origem) {
        this.origem = origem;
    }

}
