/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.servico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import javax.inject.Inject;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 * @author salvio
 */
@Named
@RequestScoped
public class ServicoChamado implements Serializable {

    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessaoAtual;

    public void notificar(ChamadoCliente pChamado) {
        if (sessaoAtual.isIdentificado()) {
            UsuarioCRM usuario = (UsuarioCRM) sessaoAtual.getUsuario();
            if (usuario.isUmUsuarioDoCliente()) {
                ModuloCRMCliente.chamadoNotificar(pChamado).dispararMensagens();
            } else {
                ModuloCRMAtendimentoChamado.chamadoNotificarCliente(pChamado).dispararMensagens();
            }
        }

    }

    public void abrirEFechar(ChamadoCliente pChamado) {
        if (sessaoAtual.isIdentificado()) {
            UsuarioCRM usuario = (UsuarioCRM) sessaoAtual.getUsuario();
            if (usuario.isUmUsuarioDoCliente()) {
                if (!pChamado.isFoiFinalizado()) {
                    ItfRespostaAcaoDoSistema resp = ModuloCRMCliente.chamadoFinalizar(pChamado).dispararMensagens();
                    if (resp.isSucesso()) {
                        UtilSBWP_JSFTools.vaParaPagina(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMCliente.DASHBOARD_MB_GESTAO));
                    }

                } else {
                    ItfRespostaAcaoDoSistema resp = ModuloCRMCliente.chamadoAbrirChamado(pChamado).dispararMensagens();
                    if (resp.isSucesso()) {
                        UtilSBWP_JSFTools.vaParaPagina(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR, pChamado));
                    }
                }

            } else {
                if (!pChamado.isFoiFinalizado()) {
                    ModuloCRMAtendimentoChamado.chamadoFinalizar(pChamado).dispararMensagens();
                    UtilSBWP_JSFTools.vaParaPagina(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO));
                } else {
                    ModuloCRMAtendimentoChamado.chamadoAssumirResponsavel(pChamado).dispararMensagens();
                    UtilSBWP_JSFTools.vaParaPagina(SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO, pChamado));
                }
            }
        }

    }

    public void anexar(ChamadoCliente pChamado) {

    }
}
