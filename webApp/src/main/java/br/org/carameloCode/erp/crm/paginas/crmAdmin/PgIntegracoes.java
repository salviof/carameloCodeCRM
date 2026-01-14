/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.coletivoJava.integracoes.amazonSMS.FabIntegracaoSMS;
import br.org.coletivoJava.integracoes.matrixChat.FabApiRestIntMatrixChatUsuarios;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.StatusIntegracao;
import com.super_bits.Super_Bits.googleCalendarIntegracao.api.FabIntGoogleCalendar;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticContatoRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfB_PaginaComEntityManager;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 *
 * @author sfurbino
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Integracoes", tags = {"Integracoes"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.INTEGRACOES_MB_GESTAO)
public class PgIntegracoes extends MB_PaginaConversation implements ItfPaginaGerenciarEntidade<StatusIntegracao> {

    private StatusIntegracao statusSelecionado;
    private List<StatusIntegracao> statusDisponiveis = new ArrayList<>();

    @PostConstruct
    public void inicio() {

        listarDados();

    }

    @Override
    public List<StatusIntegracao> getEntidadesListadas() {
        return statusDisponiveis;
    }

    @Override
    public void setEntidadesListadas(List<StatusIntegracao> entidadesListadas) {
        statusDisponiveis = entidadesListadas;
    }

    @Override
    public void executarAcao(StatusIntegracao pCompradorSelecionado) {

    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return statusSelecionado;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        statusSelecionado = (StatusIntegracao) pBeanSimples;
    }

    public StatusIntegracao getStatusSelecionado() {
        return statusSelecionado;
    }

    public List<StatusIntegracao> getStatusDisponiveis() {
        return statusDisponiveis;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoListarRegistros() {
        return FabAcaoCrmAdmin.INTEGRACOES_FRM_LISTAR_INTEGRACOES.getRegistro().getComoFormulario().getComoFormularioEntidade();
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoNovoRegistro() {
        return null;
    }

    @Override
    public ComoAcaoDoSistema getAcaoSalvarAlteracoes() {
        return FabAcaoCrmAdmin.INTEGRACAO_ERP_CTR_SALVAR_MERGE.getRegistro().getComoFormulario().getComoController();
    }

    @Override
    public List<ComoAcaoDoSistema> getAcoesRegistros() {
        return (List) getAcaoVinculada().getAcoesVinculadasAObjetoExistente();
    }

    @Override
    public void setEntidadeSelecionada(StatusIntegracao entidadeSelecionada) {
        this.statusSelecionado = entidadeSelecionada;
    }

    @Override
    public StatusIntegracao getEntidadeSelecionada() {
        return getStatusSelecionado();
    }

    @Override
    public boolean isNovoRegistro() {
        return false;
    }

    @Override
    public boolean isPodeEditar() {
        return false;
    }

    @Override
    public boolean isTemVisualizar() {
        return true;
    }

    @Override
    public boolean isTemNovo() {
        return false;
    }

    @Override
    public boolean isTemEditar() {
        return false;
    }

    @Override
    public boolean isTemAlterarStatus() {
        return false;
    }

    @Override
    public void listarDados() {
        statusDisponiveis.add(new StatusIntegracao(FabIntegracaoSMS.ENVIAR_MENSAGEM));
        statusDisponiveis.add(new StatusIntegracao(FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO));
        statusDisponiveis.add(new StatusIntegracao(FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES));
        statusDisponiveis.add(new StatusIntegracao(FabApiRestIntMatrixChatUsuarios.USUARIO_PROFILE));
        statusDisponiveis.add(new StatusIntegracao(FabIntGoogleCalendar.AGENDAR_ATIVIDADES));
    }

    @Override
    public boolean isTemPesquisa() {
        return false;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoVisualizar() {
        return FabAcaoCrmAdmin.INTEGRACAO_ERP_FRM_VISUALIZAR.getRegistro().getComoFormularioEntidade();
    }

    @Override
    public ComoAcaoControllerEntidade getAcaoAlterarStatus() {
        return null;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEditar() {
        return FabAcaoCrmAdmin.INTEGRACAO_ERP_FRM_EDITAR.getRegistro().getComoFormularioEntidade();
    }

    @Override
    public boolean isSomenteLeitura() {
        return true;
    }

    @Override
    public void atualizarEntidadeSelecionada() {

    }

    @Override
    public ItfB_PaginaComEntityManager getComoPaginaComEntityManager() {
        return super.getComoPaginaComEntityManager(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean isPaginaDeGestao() {
        return super.isPaginaDeGestao(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getJsonPagina() {
        return super.getJsonPagina(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
