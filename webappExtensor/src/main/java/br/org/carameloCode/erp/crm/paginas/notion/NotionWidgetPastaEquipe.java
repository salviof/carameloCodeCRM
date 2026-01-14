/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.notion;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class NotionWidgetPastaEquipe implements Serializable {

    private Pessoa pessoa;

    @PostConstruct
    public void inicio() {
        //HttpServletRequest request = UtilSBWPServletTools.getRequestAtual();
        String documento = (String) UtilSBWPServletTools.getRequestParametro("documento");
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        if (documento.contains("?")) {
            documento = documento.substring(0, documento.indexOf("?"));
        }
        try {
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(Pessoa.class, em);
            consulta.addcondicaoCampoIgualA("documento", documento);
            pessoa = (Pessoa) consulta.getPrimeiroRegistro();
        } catch (Throwable t) {

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

}
