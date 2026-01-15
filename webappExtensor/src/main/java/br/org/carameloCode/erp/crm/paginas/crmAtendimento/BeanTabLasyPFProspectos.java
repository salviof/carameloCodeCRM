/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFacesBeanModel.dataListLasy.BP_DataModelLasy;

import java.util.List;

/**
 *
 * @author salviofurbino
 * @since 16/10/2019
 * @version 1.0
 */
public class BeanTabLasyPFProspectos extends BP_DataModelLasy<PessoaJuridica> {

    public BeanTabLasyPFProspectos(List<? extends PessoaJuridica> listaCompleta) {
        super((List) listaCompleta, true);
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount); //chamada super do metodo (implementação classe pai)
    }

    @Override
    public int getRowCount() {
        return super.getRowCount(); //chamada super do metodo (implementação classe pai)
    }

}
