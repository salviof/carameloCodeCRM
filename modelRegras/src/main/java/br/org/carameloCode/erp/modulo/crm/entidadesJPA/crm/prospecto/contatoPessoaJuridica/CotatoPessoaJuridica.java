/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoaJuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Contato PJ", plural = "Contatos PJ")
public class CotatoPessoaJuridica extends ContatoProspecto {

    public PessoaJuridica getEmpresa() {
        return getProspecto().getComoPessoaJuridica();
    }

}
