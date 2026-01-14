/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação confirnação cliente", plural = "Solicitações de confirmações de cliente")
public class SolicitacaoConfirmacaoEquipe extends Solicitacao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML)
    @Column(length = 4000)
    private String descricaoConfirmacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    @Column(length = 4000)
    private String observacaoSolicitado;

    public String getDescricaoConfirmacao() {
        return descricaoConfirmacao;
    }

    public void setDescricaoConfirmacao(String descricaoConfirmacao) {
        this.descricaoConfirmacao = descricaoConfirmacao;
    }

    public String getObservacaoSolicitado() {
        return observacaoSolicitado;
    }

    public void setObservacaoSolicitado(String observacaoSolicitado) {
        this.observacaoSolicitado = observacaoSolicitado;
    }

}
