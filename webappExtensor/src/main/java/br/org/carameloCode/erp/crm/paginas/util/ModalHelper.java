/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.util;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 * @author salviofurbino
 * @since 29/04/2019
 * @version 1.0
 */
public abstract class ModalHelper {

    private static final String NOME_CARD_EMPRESA_BASICO = "cardEmpresaBasico";
    public static final String NOME_CARD_EMPRESA = "cardEmpresa";
    private static final String NOME_CARD_CONTATOS = "cardEmpresaContatos";
    private static final String NOME_CARD_ANEXOS = "cardEmpresaAnexos";
    private static final String NOME_CARD_OPCOES_EMAIL = "opcoesEmail";

    public static String getIDCardEmpresaBasico(Pessoa pProspecto) {
        return NOME_CARD_EMPRESA_BASICO + pProspecto.getId();
    }

    public static String getIDCardEmpresa(Pessoa pProspecto) {
        return NOME_CARD_EMPRESA + pProspecto.getId();
    }

    public static String getIDCardContato(Pessoa pProspecto) {
        return NOME_CARD_CONTATOS + pProspecto.getId();
    }

    public static String getIDCardAnexos(PessoaJuridica pProspecto) {
        return NOME_CARD_ANEXOS + pProspecto.getId();
    }

    public static boolean atualizarCardEmpresaBasico(Pessoa pProspecto) {
        try {
            UtilSBWP_JSFTools.atualizaPorId(getIDCardEmpresaBasico(pProspecto), false);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean atualizarCardEmpresa(PessoaJuridica pProspecto) {
        try {
            UtilSBWP_JSFTools.atualizaPorId(getIDCardEmpresa(pProspecto), false);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean atualizarCardOpcoesEmail(Pessoa pProspecto) {
        try {
            UtilSBWP_JSFTools.atualizaPorId(NOME_CARD_OPCOES_EMAIL, false);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean atualizarCardContato(Pessoa pProspecto) {
        try {
            UtilSBWP_JSFTools.atualizaPorId(getIDCardContato(pProspecto), false);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean atualizarCardAnexos(PessoaJuridica pProspecto) {
        try {
            UtilSBWP_JSFTools.atualizaPorId(getIDCardAnexos(pProspecto), false);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

}
