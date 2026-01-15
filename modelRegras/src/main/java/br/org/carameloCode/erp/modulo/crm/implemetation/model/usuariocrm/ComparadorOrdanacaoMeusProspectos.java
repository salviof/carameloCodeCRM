/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.Comparator;

/**
 *
 * @author sfurbino
 * @since 02/01/2020
 * @version 1.0
 */
public class ComparadorOrdanacaoMeusProspectos implements Comparator<ComoEntidadeSimples> {

    public ComparadorOrdanacaoMeusProspectos() {

    }

    @Override
    public int compare(ComoEntidadeSimples o1, ComoEntidadeSimples o2) {
        try {
            if (((PessoaJuridica) o1).getRelacionamento().getPeso() < 0 && ((PessoaJuridica) o2).getRelacionamento().getPeso() > 0) {
                return 1;
            }
            if (((PessoaJuridica) o2).getRelacionamento().getPeso() < 0 && ((PessoaJuridica) o1).getRelacionamento().getPeso() > 0) {
                return -1;
            }

            if (((PessoaJuridica) o1).getDataHoraAlterouProspecto().getTime() > ((PessoaJuridica) o2).getDataHoraAlterouProspecto().getTime()) {
                return -1;
            }
            if (((PessoaJuridica) o1).getDataHoraAlterouProspecto().getTime() < ((PessoaJuridica) o2).getDataHoraAlterouProspecto().getTime()) {
                return 1;
            }
            return 0;
        } catch (Throwable t) {
            return 0;
        }
    }

}
