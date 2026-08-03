/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacaoUsrComUsr;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ItfEntidadeExtensivelMultiplasSequencias;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Tipo de notificação entre Usuarios CRM", plural = "Tipos de notificações ")
@Entity
public class TipoNtfrCRMUsrToUsr extends TipoNotificacaoUsrComUsr implements ComoTipoComunicCRM, ItfEntidadeExtensivelMultiplasSequencias {

    @ManyToOne(targetEntity = TipoMensagemMktWhatsApp.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Mensagem Whatsapp")
    private TipoMensagemMktWhatsApp tipoMensagem;

    public TipoMensagemMktWhatsApp getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagemMktWhatsApp tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    @Override
    public String nomeSequenciaIdentificacao() {
        return ComoTipoComunicCRM.class.getSimpleName();
    }

}
