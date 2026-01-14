/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.chamado;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComGestaoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;

/**
 *
 * @author sfurbino
 */
public abstract class MBGestaoChamados extends MB_paginaCadastroEntidades<ChamadoCliente> implements ItfPaginaComGestaoChamado {

    private boolean mostrarResumo = true;

    @Override
    public boolean isMostrarResumo() {
        return mostrarResumo;
    }

    @Override
    public void setMostrarResumo(boolean mostrarResumo) {
        this.mostrarResumo = mostrarResumo;
    }

    @Override
    public void alternarExibicaoResumo() {
        mostrarResumo = !mostrarResumo;

    }

}
