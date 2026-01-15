/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabTipoPesquisaLeads implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoPesquisaLead.class, nomeObjeto = "Meus Leads", id = 1)
    MEUS_LEADS,
    @InfoObjetoDaFabrica(classeObjeto = TipoPesquisaLead.class, nomeObjeto = "Origens Públicas", id = 2)
    ORIGEM_PUBLICAS,
    @InfoObjetoDaFabrica(classeObjeto = TipoPesquisaLead.class, nomeObjeto = "Origens Privadas", id = 3)
    ORIGEM_PRIVADA,
    @InfoObjetoDaFabrica(classeObjeto = TipoPesquisaLead.class, nomeObjeto = "Leads Urgentes", id = 4)
    LEADS_URGENTES,
    @InfoObjetoDaFabrica(classeObjeto = TipoPesquisaLead.class, nomeObjeto = "Pesquisa Livre", id = 5)
    PESQUISA_LIVRE;

    @Override
    public TipoPesquisaLead getRegistro() {
        return (TipoPesquisaLead) ComoFabricaComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

}
