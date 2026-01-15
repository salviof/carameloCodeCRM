/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salviofurbino
 */
public enum FabMetasRelacionamento implements ComoFabricaComPersistencia {
    @InfoObjetoDaFabrica(classeObjeto = MetaRelacionamento.class, id = 1, nomeObjeto = "Encontrado")
    ENCONTRADO,
    @InfoObjetoDaFabrica(classeObjeto = MetaRelacionamento.class, id = 2, nomeObjeto = "Em negociação")
    EM_NEGOCIACAO,
    @InfoObjetoDaFabrica(classeObjeto = MetaRelacionamento.class, id = 3, nomeObjeto = "Cliente")
    CLIENTE;

    @Override
    public MetaRelacionamento getRegistro() {
        return (MetaRelacionamento) ComoFabricaComPersistencia.super.getRegistro(); //chamada super do metodo (implementação classe pai)
    }

}
