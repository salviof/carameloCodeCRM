/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.crm.paginas.chamado.MBGestaoChamados;
import br.org.carameloCode.erp.crm.paginas.servico.ServicoChatView;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.StatusChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import static br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente.CHAMADO_FRM_LISTAR;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;

/**
 *
 * @author sfurbino
 */
@ViewScoped
@Named
@InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CHAMADO_MB_GESTAO)
@InfoPagina(nomeCurto = "CHAMADO_CLIENTE", tags = {"Meus chamados em aberto"})
public class PgSuporteCliente extends MBGestaoChamados {

    @InfoParametroURL(nome = "Chamado Selecionado", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ChamadoCliente.class, representaEntidadePrincipalMB = true, obrigatorio = false)
    ParametroURL chamadoSelecionado;
    private ComoChatSalaBean canalChat;

    @Inject
    private ServicoChatView servicoRC;

    @Override
    public void setEntidadeSelecionada(ChamadoCliente entidadeSelecionada) {
        super.setEntidadeSelecionada(entidadeSelecionada); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void autoexecEntidadeNova() {

        ConsultaDinamicaDeEntidade qrychamadoRascunho = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEMPagina());
        qrychamadoRascunho.addCondicaoManyToOneIgualA(CPChamadoCliente.usuariocliente, SBCore.getUsuarioLogado());
        qrychamadoRascunho.addCondicaoManyToOneIgualA(FabStatusChamado.RASCUNHO.getRegistro());
        List<ChamadoCliente> rasunhos = qrychamadoRascunho.resultadoRegistros();
        if (rasunhos.isEmpty()) {
            setEntidadeSelecionada(new ChamadoCliente());

            try {
                getEntidadeSelecionada().prepararNovoObjeto(SBCore.getUsuarioLogado());
            } catch (ErroPreparandoObjeto ex) {
                setEntidadeSelecionada(null);
            }
        } else {
            ChamadoCliente chamadoRascunho = (ChamadoCliente) qrychamadoRascunho.resultadoRegistros().get(0);
            setEntidadeSelecionada(chamadoRascunho);
        }

    }

    public void atualizarNotificacaoViaEMAIL() {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        ChamadoCliente chamadoAtualizado = UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), em);
        chamadoAtualizado.setNotificarViaEmail(!chamadoAtualizado.isNotificarViaEmail());
        if (UtilSBPersistencia.mergeRegistro(chamadoAtualizado, em) != null) {
            SBCore.enviarAvisoAoUsuario("Notificação alterada com sucesso");
        }
    }

    public void atualizarNotificacaoViaSMS() {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        ChamadoCliente chamadoAtualizado = UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), em);
        chamadoAtualizado.setNotificarViaSMS(!chamadoAtualizado.isNotificarViaSMS());
        if (UtilSBPersistencia.mergeRegistro(chamadoAtualizado, em) != null) {
            SBCore.enviarAvisoAoUsuario("Notificação alterada com sucesso");
        }
    }
    private boolean listagemRealizada = false;

    @Override
    protected synchronized void listarDados(boolean mostrarInativos) {
        try {
            if (!listagemRealizada) {
                FabAcaoCRMCliente acao = getEnumAcaoAtual();
                List<StatusChamado> statusDosChamados = new ArrayList<>();

                statusDosChamados.add(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                statusDosChamados.add(FabStatusChamado.EM_ATENDIMENTO.getRegistro());
                statusDosChamados.add(FabStatusChamado.ATRAZADO.getRegistro());

                switch (acao) {
                    case CHAMADO_FRM_LISTAR:
                        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEMPagina());
                        consulta.addCondicaoManyToOneContemNoIntervalo("status", statusDosChamados);
                        consulta.addCondicaoManyToOneIgualA(CPChamadoCliente.usuariocliente, SBCore.getUsuarioLogado());

                        List<ChamadoCliente> chamados = consulta.resultadoRegistros();
                        setEntidadesListadas(chamados);
                        UtilCRCListasObjeto.ordernarPorCampoReverso(getEntidadesListadas(), CPChamadoCliente.datahoracriacao);
                        break;

                }

            }
        } finally {
            listagemRealizada = true;
        }
    }

    public void salvarDescritivoTemporario() {

        ItfRespostaAcaoDoSistema resposta = ModuloCRMCliente.chamadoSalvarMerge(getEntidadeSelecionada());
        if (resposta.isSucesso()) {
            setEntidadeSelecionada((ChamadoCliente) resposta.getRetorno());
        } else {
            resposta.dispararMensagens();
        }
    }

    public ComoChatSalaBean getCanalChat() {
        if (getEntidadeSelecionada() == null) {
            return null;
        }
        if (canalChat == null) {
            if (!getEntidadeSelecionada().getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())) {
                if (canalChat == null) {
                    canalChat = servicoRC.getSalaChamado(getEntidadeSelecionada());

                }
            }
        }
        return canalChat;
    }

    @Override
    public ComoChatSalaBean getCanalDoChamado() {
        return getCanalChat();
    }

    @Override
    public void abrirChamado() {
        if (getEntidadeSelecionada() == null) {
            SBCore.enviarAvisoAoUsuario("Escolha um chamado para visualizar");
        }
        FabStatusChamado status = getEntidadeSelecionada().getStatus().getFabStatus();
        switch (status) {
            case RASCUNHO:
                executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.CHAMADO_FRM_NOVO);

                break;
            case AGUARDANDO_ATENDIMENTO:
            case EM_ATENDIMENTO:
            case ATRAZADO:
                //executaAcaoSelecionadaPorEnum(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR);
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR.getRegistro().getComoFormulario(), getEntidadeSelecionada());
                UtilSBWP_JSFTools.vaParaPagina(url);
                break;
            case FINALIZADO:
                break;
            default:
                throw new AssertionError(status.name());

        }

    }

    public String getUrlChamado(ChamadoCliente pChamado) {
        setEntidadeSelecionada(pChamado);
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR.getRegistro().getComoFormulario(), getEntidadeSelecionada());
        return url;
    }

    @Override
    public ChamadoCliente getChamado() {
        return getEntidadeSelecionada();
    }

}
