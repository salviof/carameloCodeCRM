/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salvio
 */
public enum FabTipoOrigemDemo implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = OrigemProspecto.class, id = 1, nomeObjeto = "Leads via site")
    LEADS_VIA_SITE;

}
