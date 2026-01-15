/*
 * To change this license header, choose License Headers FabCategoriaEmailRecebido Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template FabCategoriaEmailRecebido the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabCategoriaEmailRecebido implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = CategoriaEmailRecebido.class, id = 1, nomeObjeto = "Desclassificado")
    DESCLASSIFICADO,
    @InfoObjetoDaFabrica(classeObjeto = CategoriaEmailRecebido.class, id = 2, nomeObjeto = "Email Pessoa Conhecida")
    EMAIL_PROSPECTO,
    @InfoObjetoDaFabrica(classeObjeto = CategoriaEmailRecebido.class, id = 3, nomeObjeto = "Email Privados")
    EMAIL_PRIVADO;

    @Override
    public CategoriaEmailRecebido getRegistro() {
        return (CategoriaEmailRecebido) ComoFabricaComPersistencia.super.getRegistro(); //To change body of generated methods, choose Tools | Templates.
    }

}
