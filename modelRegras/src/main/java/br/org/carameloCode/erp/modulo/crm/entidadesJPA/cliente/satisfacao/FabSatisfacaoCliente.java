/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabSatisfacaoCliente implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = SatisfacaoCliente.class, nomeObjeto = "Insatisfeito", id = 1)
    INSATISFEITO,
    @InfoObjetoDaFabrica(classeObjeto = SatisfacaoCliente.class, nomeObjeto = "Satisfeito", id = 2)
    SATISFEITO,
    @InfoObjetoDaFabrica(classeObjeto = SatisfacaoCliente.class, nomeObjeto = "Muito satisfeito", id = 3)
    MUITOSATISFEITO;

    @Override
    public SatisfacaoCliente getRegistro() {

        SatisfacaoCliente satisfacao = (SatisfacaoCliente) ComoFabricaComPersistencia.super.getRegistro();
        switch (this) {
            case INSATISFEITO:
                satisfacao.setIcone("fa fa-thumbs-o-down");
                break;
            case SATISFEITO:
                satisfacao.setIcone("fa fa-thumbs-o-up");
                break;
            case MUITOSATISFEITO:
                satisfacao.setIcone("fa fa-star-o");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return satisfacao;
    }

}
