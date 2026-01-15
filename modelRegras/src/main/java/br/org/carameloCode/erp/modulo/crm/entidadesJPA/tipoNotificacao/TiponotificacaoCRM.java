/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import br.org.coletivojava.erp.notificacao.padrao.model.tipoNotificacao.TipoNotificacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Tipo de notificação CRM ", plural = "Tipos de notificações ")
@Entity
public class TiponotificacaoCRM extends TipoNotificacao {

    @ManyToOne(targetEntity = TipoMensagemMktWhatsApp.class)
    private TipoMensagemMktWhatsApp tipoMensagem;

    @Override
    public boolean isEntidadeExtendida() {
        return true;
    }

}
