/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author sfurbino
 */
public class ListenerEntidadePessoa extends ListenerEntidadePadrao implements Serializable {

    @PostPersist
    public void acaoAposPersistir(Pessoa emp) {
        emp.setDataHoraUltimaInteracao(new Date());
        super.acaoAposPersistir(emp); //To change body of generated methods, choose Tools | Templates.

    }

    public ListenerEntidadePessoa() {
        super();
    }

    @PreUpdate
    public void acaoAntesDeAtualizar(Pessoa pEntidade) {
        normalizaEndereco(pEntidade);
        configuraContatoPrincipal(pEntidade);
        super.acaoAntesDeAtualizar(pEntidade);
        pEntidade.setDataHoraUltimaInteracao(new Date());

    }

    @PrePersist
    public void acaoAntesPersistir(Pessoa emp) {
        normalizaEndereco(emp);
        configuraContatoPrincipal(emp);
        emp.setDataHoraUltimaInteracao(new Date());
        super.acaoAntesDePersistir(emp);

    }

    @PostUpdate
    public void acaoAposAtualizar(Pessoa emp) {
        emp.setDataHoraUltimaInteracao(new Date());
        super.acaoAposAtualizar(emp); //To change body of generated methods, choose Tools | Templates.
        try {

        } catch (Throwable t) {

        }
    }

    private void configuraContatoPrincipal(Pessoa pEmp) {
        if (pEmp.getContatoPrincipal() != null) {
            if (UtilCRCStringValidador.isNuloOuEmbranco(pEmp.getContatoPrincipal().getNome())) {

                if (!pEmp.getContatosProspecto().isEmpty()) {
                    pEmp.getContatosProspecto().remove(pEmp.getContatoPrincipal());
                }
                pEmp.setContatoPrincipal(null);
            }
        }
    }

    private void normalizaEndereco(Pessoa pEmp) {
        if (pEmp.getLocalizacao() != null) {
            if (pEmp.getLocalizacao().getBairro() != null) {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pEmp.getLocalizacao().getBairro().getNome())) {
                    pEmp.setLocalizacao(null);
                    return;
                }
            }
            if (pEmp.getLocalizacao().getBairro() != null && pEmp.getLocalizacao().getBairro().getCidade() != null) {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pEmp.getLocalizacao().getBairro().getCidade().getNome())) {
                    pEmp.setLocalizacao(null);
                    return;
                }
            }
            if (pEmp.getLocalizacao().getBairro() != null && pEmp.getLocalizacao().getBairro().getCidade().getUnidadeFederativa() != null) {
                if (UtilCRCStringValidador.isNuloOuEmbranco(pEmp.getLocalizacao().getBairro().getCidade().getUnidadeFederativa().getNome())) {
                    pEmp.setLocalizacao(null);

                }
            }
        }
    }
}
