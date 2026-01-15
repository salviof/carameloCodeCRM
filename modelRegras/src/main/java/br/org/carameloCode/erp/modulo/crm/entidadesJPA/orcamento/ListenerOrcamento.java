/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author sfurbino
 */
public class ListenerOrcamento extends ListenerEntidadePadrao {

    @PreUpdate
    public void acaoAntesDeAtualizar(Orcamento pEntidade) {
        super.acaoAntesDeAtualizar(pEntidade); //To change body of generated methods, choose Tools | Templates.
    }

    @PrePersist
    public void acaoAntesDePersistir(Orcamento pEntidade) {
        super.acaoAntesDePersistir(pEntidade);

    }

    @PostUpdate
    public void acaoAposAtualizar(Orcamento emp) {
        super.acaoAposAtualizar(emp); //To change body of generated methods, choose Tools | Templates.
    }

    @PostPersist
    public void acaoAposPersistir(Orcamento emp) {
        super.acaoAposPersistir(emp); //To change body of generated methods, choose Tools | Templates.
    }

}
