/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.CPUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.IntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente.CHAMADO_MB_GESTAO;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente.DASHBOARD_CTR_INSATISFEITO;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente.DASHBOARD_CTR_MUITO_SATISFEITO;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente.DASHBOARD_CTR_SATISFEITO;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

/**
 *
 * @author sfurbino
 */
@ViewScoped
@Named
@InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.DASHBOARD_MB_GESTAO)
@InfoPagina(nomeCurto = "DASHCLI", tags = {"DashBoard de atendimento do cliente"})
public class PgDashboardCliente extends MB_paginaCadastroEntidades<UsuarioCrmCliente> {

    //o Token precisa de pelomenos um parametro na pagina, por isso esse parametro.
    @InfoParametroURL(nome = "Contato", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ContatoProspecto.class, obrigatorio = false)
    private ParametroURL parametroContatoSelecionado;

    private UsuarioCrmCliente usuarioLogado;

    @PostConstruct
    public void inicio() {
        usuarioLogado = (UsuarioCrmCliente) SBCore.getUsuarioLogado();
    }

    @Override
    public void executarAcaoSelecionada() {

        FabAcaoCRMCliente acao = getEnumAcaoAtual();
        switch (acao) {
            case CHAMADO_MB_GESTAO:
                break;
            case DASHBOARD_CTR_MUITO_SATISFEITO:
                ItfResposta respMsati = ModuloCRMCliente.satisfacaoDeclararMuitoSatisfeito(usuarioLogado);
                if (respMsati.isSucesso()) {
                    respMsati.dispararMensagens();
                }
                break;
            case DASHBOARD_CTR_SATISFEITO:
                ItfResposta respSats = ModuloCRMCliente.satisfacaoDeclararSatisfeito(usuarioLogado);
                if (respSats.isSucesso()) {
                    respSats.dispararMensagens();
                }
                break;
            case DASHBOARD_CTR_INSATISFEITO:
                ItfResposta respInst = ModuloCRMCliente.satisfacaoDeclararInsatisfeito(usuarioLogado);
                if (respInst.isSucesso()) {
                    respInst.dispararMensagens();
                }
                break;
            default:
                super.executarAcaoSelecionada();

        }
        renovarEMPagina();
        usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
    }

    public String getScriptFacebook() {
        return "https://connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v11.0&appId=1503220026413122&autoLogAppEvents=1";
    }

    public boolean isUsuarioSatisfeito() {
        return usuarioLogado.getSatisfacao().equals(FabSatisfacaoCliente.SATISFEITO.getRegistro());
    }

    public boolean isUsuarioInsatisfeito() {
        return usuarioLogado.getSatisfacao().equals(FabSatisfacaoCliente.INSATISFEITO.getRegistro());
    }

    public boolean isUsuarioMuitoSatisfeito() {
        return usuarioLogado.getSatisfacao().equals(FabSatisfacaoCliente.MUITOSATISFEITO.getRegistro());
    }

    public UsuarioCrmCliente getUsuarioLogado() {
        return usuarioLogado;
    }

    public String getUrlConversa() {
        String urlReserva = MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.CONVERSA_FRM_CHAT.getRegistro());
        return urlReserva;
    }

    public String getUrlAgenda() {
        ReservaHorario reserva = (ReservaHorario) getUsuarioLogado().getCampoInstanciadoByNomeOuAnotacao(CPUsuarioCrmCliente.reservaativamaisproxima).getValor();
        if (reserva == null) {
            String urlNovareserva = MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.RESERVAS_FRM_HORARIOS_DISPONIVEIS.getRegistro());
            return urlNovareserva;
        } else {
            if (reserva.getTipoAgendamento().isUmAtendimentoRemoto()) {
                String urlReserva = MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_REMOTO.getRegistro(), reserva);
                return urlReserva;
            } else {
                String urlReserva = MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.RESERVAS_FRM_VISUALIZAR_PRESENCIAL.getRegistro(), reserva);
                return urlReserva;
            }
        }
    }

    private List<IntegracaoLink> integracoes;

    public List<IntegracaoLink> getIntegracoes() {
        if (integracoes == null || integracoes.isEmpty()) {
            integracoes = new ArrayList<>();
            List<IntegracaoLink> todasintegracoes = (List<IntegracaoLink>) usuarioLogado.getRepresentanteLegal().getCPinst("integracoesLink").getValor();
            todasintegracoes.stream().filter(it -> it.getTipoDado().isMostrarParaCliente()).forEach(integracoes::add);

        }
        return integracoes;

    }
}
