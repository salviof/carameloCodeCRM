/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabPorteProspectoEmpresa implements ComoFabrica {

    PEQUENA, MEDIA, GRANDE, INDEFINIDO;

    @Override
    public Porte getRegistro() {

        switch (this) {
            case PEQUENA:
                return new Porte(1l, "pequena", "Empresas pequenas (Alcance local)");
            case MEDIA:
                return new Porte(2l, "Média", "Empresas pequenas (Alcance local)");
            case GRANDE:
                return new Porte(3l, "grande", "Empresas de Grande porte (Alcance Nacional)");
            case INDEFINIDO:
                return new Porte(4l, "Indefinido", "Não definido");

            default:
                throw new AssertionError(this.name());
        }

    }

}
