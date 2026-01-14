/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;

/**
 *
 * @author sfurbino
 */
public interface ItfPaginaComGestaoChamado {

    public boolean isMostrarResumo();

    public void alternarExibicaoResumo();

    public ComoChatSalaBean getCanalDoChamado();

    public void abrirChamado();

    public void setMostrarResumo(boolean mostrarResumo);

    public ChamadoCliente getChamado();

}
