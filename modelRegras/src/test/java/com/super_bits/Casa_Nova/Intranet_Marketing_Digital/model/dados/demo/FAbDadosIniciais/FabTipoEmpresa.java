/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.TipoEmpresa;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoEmpresa implements ComoFabricaComPersistencia {
    @InfoObjetoDaFabrica(classeObjeto = TipoEmpresa.class, id = 1, nomeObjeto = "Indústria")
    INDRUSTRIA,
    @InfoObjetoDaFabrica(classeObjeto = TipoEmpresa.class, id = 2, nomeObjeto = "Serviços")
    SERVICOS,
    @InfoObjetoDaFabrica(classeObjeto = TipoEmpresa.class, id = 3, nomeObjeto = "Comércio")
    COMERCIO;

    @Override
    public TipoEmpresa getRegistro() {
        return (TipoEmpresa) ComoFabricaComPersistencia.super.getRegistro(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
