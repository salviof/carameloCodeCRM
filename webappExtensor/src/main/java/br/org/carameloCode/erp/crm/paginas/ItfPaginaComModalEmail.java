/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas;

import br.org.carameloCode.erp.crm.paginas.crmAtendimento.ServicoEmail;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_Pagina;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author salviofurbino
 * @since 29/04/2019
 * @version 1.0
 */
public interface ItfPaginaComModalEmail extends ItfB_Pagina {

    public void onModalNovoContatoEmailClose(SelectEvent event);

    public void onModalNovoContatoEmailOpen(AjaxBehaviorEvent event);

    public void onModalAnexoEmailClose(SelectEvent event);

    public void renovarEntityManager();

}
