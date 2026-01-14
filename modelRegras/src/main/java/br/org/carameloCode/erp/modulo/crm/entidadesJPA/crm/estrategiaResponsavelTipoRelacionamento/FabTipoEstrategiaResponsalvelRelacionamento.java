/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.estrategiaResponsavelTipoRelacionamento;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salviofurbino
 */
public enum FabTipoEstrategiaResponsalvelRelacionamento implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoEstrategiaResponsavelRelacionamento.class, id = 1, nomeObjeto = "")
    RODIZIO,
    @InfoObjetoDaFabrica(classeObjeto = TipoEstrategiaResponsavelRelacionamento.class, id = 3, nomeObjeto = "")
    TODOS,
    @InfoObjetoDaFabrica(classeObjeto = TipoEstrategiaResponsavelRelacionamento.class, id = 4, nomeObjeto = "")
    AGENDA_MAIS_LIVRE,
    @InfoObjetoDaFabrica(classeObjeto = TipoEstrategiaResponsavelRelacionamento.class, id = 5, nomeObjeto = "")
    ORIGEM_E_RESPONSAVEL,
    @InfoObjetoDaFabrica(classeObjeto = TipoEstrategiaResponsavelRelacionamento.class, id = 6, nomeObjeto = "")
    API_WEB_SERVICE_PERSONALIZADO

}
