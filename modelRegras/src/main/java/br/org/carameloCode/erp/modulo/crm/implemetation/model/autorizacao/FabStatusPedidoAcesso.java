/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaStatusComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public enum FabStatusPedidoAcesso implements ComoFabricaStatusComPersistencia {
    @InfoObjetoDaFabrica(classeObjeto = StatusPedidoAcesso.class, id = 1, nomeObjeto = "Aguardando resposta")
    AGUARDANDO_RESPOSTA,
    @InfoObjetoDaFabrica(classeObjeto = StatusPedidoAcesso.class, id = 2, nomeObjeto = "Acesso Concedido")
    CONCEDIDO,
    @InfoObjetoDaFabrica(classeObjeto = StatusPedidoAcesso.class, id = 3, nomeObjeto = "Acesso Negado")
    NEGADO;

    @Override
    public StatusPedidoAcesso getRegistro() {
        return (StatusPedidoAcesso) ComoFabricaStatusComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComoAcaoDoSistema> opcoesPorStatus() {
        return new ArrayList<>();
    }

}
