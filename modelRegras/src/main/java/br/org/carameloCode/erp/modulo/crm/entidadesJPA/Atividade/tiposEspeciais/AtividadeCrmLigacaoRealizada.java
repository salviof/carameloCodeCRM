/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.AudioVoip;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.telefone.Telefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Ligação realizada", plural = "Ligações realizadas")
public class AtividadeCrmLigacaoRealizada extends AtividadeCRM implements ComoAtividadeVoip {

    @ManyToOne(targetEntity = Telefone.class, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private Telefone telefoneVoip;

    @ManyToOne(targetEntity = ContatoProspecto.class, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ContatoProspecto contatoProspecto;

    @ManyToOne(targetEntity = AudioVoip.class, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private AudioVoip audioVoip;

    public ContatoProspecto getContatoProspecto() {
        return contatoProspecto;
    }

    public void setContatoProspecto(ContatoProspecto contatoProspecto) {
        this.contatoProspecto = contatoProspecto;
    }

    @Override
    public Telefone getTelefoneVoip() {
        return telefoneVoip;
    }

    @Override
    public void setTelefoneVoip(Telefone telefoneVoip) {
        this.telefoneVoip = telefoneVoip;
    }

    public AudioVoip getAudioVoip() {
        return audioVoip;
    }

    public void setAudioVoip(AudioVoip audioVoip) {
        this.audioVoip = audioVoip;
    }

}
