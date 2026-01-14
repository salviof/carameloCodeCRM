/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabSastisfacaoClienteResolucao implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = SatisfacaoChamado.class, nomeObjeto = "Não Resolvido", id = 1)
    NAO_RESOLVIDO,
    @InfoObjetoDaFabrica(classeObjeto = SatisfacaoChamado.class, nomeObjeto = "Resolvido", id = 2)
    RESOLVIDO,
    @InfoObjetoDaFabrica(classeObjeto = SatisfacaoChamado.class, nomeObjeto = "Muito bem resolvido", id = 3)
    RESOLVIDO_SUPREENDENTE;

    @Override
    public SatisfacaoChamado getRegistro() {
        SatisfacaoChamado satisfacao = (SatisfacaoChamado) ComoFabricaComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
        switch (this) {
            case NAO_RESOLVIDO:
                satisfacao.setIcone("fa fa-thumbs-o-down");
                break;
            case RESOLVIDO:
                satisfacao.setIcone("fa fa-thumbs-o-up");
                break;
            case RESOLVIDO_SUPREENDENTE:
                satisfacao.setIcone("fa fa-star-o");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return satisfacao;
    }

}
