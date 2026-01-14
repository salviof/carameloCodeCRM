/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail;

import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.FabTipoPesquisaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilarGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 *
 * @author sfurbino
 */
public class ComparadorEmailCrm extends ItemSimilarGenerico {

    public ComparadorEmailCrm(ComoEntidadeSimples pObjetoAnalizado, String parametro, FabTipoPesquisaGenerica pTipoPEsquisa) {
        super(pObjetoAnalizado, parametro, pTipoPEsquisa);
    }

    public EmailCrm getEmail() {
        return (EmailCrm) objetoAnalizado;
    }

    @Override
    public String getTextoReferencia() {
        if (termoPesquisa == null) {
            return null;
        }
        switch (tipoPesquisaGenerica) {

            case CODIGO:
                break;
            case NOME:
                return getEmail().getAssunto();

            case DESCRICAO:
                break;
            case TELEFONE:
                break;
            case EMAIL:
                return getEmail().getEmailsDestinatarios();

            case SITE:
                break;
            case DOCUMENTO:
                break;
            case PERSONALIZADA:
                break;
            default:
                throw new AssertionError();
        }
        return getEmail().getAssunto();

    }

}
