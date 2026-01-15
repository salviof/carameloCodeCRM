/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;

import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author SalvioF
 */
@ViewScoped()
@Named
public class ServicoFluxograma implements Serializable {

    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessao;

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public BfluxoRelacionamento gerarDiagramaPorTipoRelacionamento(TipoRelacionamento ptipoRelacionamento) {
        try {
            if (sessao.getTelaUsuario().isUmMobile()) {
                return new BfluxoRelacionamento(ptipoRelacionamento, BfluxoRelacionamento.LAYOUT_FLUXO.VERTICAL);
            } else {
                return new BfluxoRelacionamento(ptipoRelacionamento, BfluxoRelacionamento.LAYOUT_FLUXO.HORIZONTAL);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo fluxo de relacionamento", t);
            return null;
        }

    }
}
