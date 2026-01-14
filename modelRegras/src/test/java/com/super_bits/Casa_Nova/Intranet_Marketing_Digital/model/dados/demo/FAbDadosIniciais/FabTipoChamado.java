/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.TipoChamado;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabTipoChamado implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoChamado.class, nomeObjeto = "Suporte Técnico", id = 1)
    SUPORTE_TECNICO,
    @InfoObjetoDaFabrica(classeObjeto = TipoChamado.class, nomeObjeto = "Sugestão", id = 2)
    SUGESTAO,

}
