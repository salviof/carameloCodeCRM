/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilarContato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 *
 * @author sfurbino
 */
public class ItemSimilarContatoCrm extends ItemSimilarContato {

    public ItemSimilarContatoCrm(ComoEntidadeSimples pObjetoAnalizado, String parametro) {
        super(pObjetoAnalizado, parametro);
        if (nota > 0) {
            if (pObjetoAnalizado instanceof ContatoColaborador) {
                if (nota < 0.8) {
                    nota = nota - 0.5;
                }
            }
        }
    }

}
