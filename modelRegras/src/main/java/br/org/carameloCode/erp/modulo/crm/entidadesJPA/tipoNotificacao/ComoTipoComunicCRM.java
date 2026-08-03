/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoComunicacao;

/**
 *
 * @author salvio
 */
public interface ComoTipoComunicCRM extends ComoTipoComunicacao {

    public TipoMensagemMktWhatsApp getTipoMensagem();

}
