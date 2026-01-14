/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.fluxoAtividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.zonaRelacionamento.ResultadoTipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author desenvolvedor
 */
public interface ItfFabFluxoDeAtividades extends ComoFabrica {

    @Override
    public TipoRelacionamento getRegistro();

    public abstract TipoRelacionamento getAtividadeInicial();

    public abstract ResultadoTipoRelacionamento getResultado();

}
