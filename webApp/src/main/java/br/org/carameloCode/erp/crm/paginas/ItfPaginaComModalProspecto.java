/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas;

import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author salviofurbino
 * @since 29/04/2019
 * @version 1.0
 */
public interface ItfPaginaComModalProspecto {

    public void onModalProspectoClose(SelectEvent event);

    public void onModalProspectoOpen(AjaxBehaviorEvent event);

}
