/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.util;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.fluxoAtividade.FluxoDeAtividades;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.junit.Test;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.EndPoint;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBWPComponentePrimeModelTest extends TesteWPCrmCarameloCode {

    public UtilSBWPComponentePrimeModelTest() {
    }

    /**
     * Test of getDiagramModel method, of class UtilSBWPComponentePrimeModel.
     */
    @Test
    public void testesDiagrama() {
        try {
            FluxoDeAtividades fluxo = new FluxoDeAtividades(UtilSBPersistencia.getListaTodos(TipoRelacionamento.class, getEMTeste()));
            DefaultDiagramModel modelagemDiagrama = UtilSBWPComponentePrimeModel.getDiagramModel(fluxo);

            for (Connection conexao : modelagemDiagrama.getConnections()) {
                System.out.println("____________________________________");
                EndPoint origem = conexao.getSource();
                EndPoint destino = conexao.getTarget();

                if (origem != null) {
                    System.out.println("Origem" + origem.getAnchor().name());

                } else {
                    System.out.println("Origem não definida");
                    conexao.toString();

                }

                if (destino != null) {
                    System.out.println("destino" + destino.getAnchor().name());
                } else {
                    System.out.println("Destino não definido");
                }

                System.out.println("____________________________________");
            }

            for (Element elemento : modelagemDiagrama.getElements()) {
                System.out.println(elemento.getData());
                System.out.println(elemento.getStyleClass());
                System.out.println(elemento.getX());
                System.out.println(elemento.getY());

            }
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

}
