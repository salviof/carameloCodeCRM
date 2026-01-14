/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.crm.paginas.chamado.MBGestaoChamados;
import br.org.carameloCode.erp.crm.paginas.servico.ServicoChatView;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimentoChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulos.SBAcessosModel.model.tokens.tokenLoginDinamico.TokenAcessoDinamico;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jersey.repackaged.com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoChatSalaBean;

/**
 *
 * @author sfurbino
 */
@InfoPagina(nomeCurto = "mchamadosatd", tags = "Meus Chamados Atendimento")
@ViewScoped
@Named
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEUS_CHAMADOS_MB_GESTAO)
public class PgMeusChamadosAtd extends MBGestaoChamados {

    @InfoParametroURL(nome = "Usuarário da agenda", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prUsuario;

    @InfoParametroURL(nome = "usuarioCliente", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCrmCliente.class, obrigatorio = false)
    private ParametroURL prUsuarioCLiente;

    @InfoParametroURL(nome = "chamado", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ChamadoCliente.class, obrigatorio = false, representaEntidadePrincipalMB = true)
    private ParametroURL prchamado;

    private Pessoa pessoaSelecionada;

    private UsuarioCRM usuario;

    private UsuarioCrmCliente usuarioCliente;

    public UsuarioCRM getUsuario() {
        return usuario;
    }

    @Override
    public ChamadoCliente getEntidadeSelecionada() {
        return super.getEntidadeSelecionada(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void autoexecEntidadeNova() {
        defineParametros();
        super.autoexecEntidadeNova();
        if (usuarioCliente != null) {
            getEntidadeSelecionada().setUsuarioCliente(usuarioCliente);
        }
        ItfRespostaAcaoDoSistema resp = ModuloCRMAtendimentoChamado.chamadoSalvarMerge(getEntidadeSelecionada());

        if (resp.isSucesso()) {
            renovarEMPagina();
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) resp.getRetorno(), getEMPagina()));
        }
    }

    @Inject
    private ServicoChatView servicoRC;

    private TokenAcessoDinamico ultimoToken;

    @PostConstruct
    public void inicio() {
        FabAcaoCRMAtendimento acao = getEnumAcaoAtual();

        switch (acao) {
            case MEUS_CHAMADOS_FRM_CHAMADOS_AGUARDANDO_ATENDIMENTO:
            case MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO:
                listarDados();
                break;
        }
        UsuarioCRM usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        if (getParametroInstanciado(prUsuario).isValorDoParametroFoiConfigurado()) {
            if (usuarioLogado.getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
                usuario = (UsuarioCRM) getParametroInstanciado(prUsuario).getValor();
            }
        } else {

        }
        defineParametros();
    }
    private boolean prConfigurado = false;

    private void defineParametros() {
        if (prConfigurado) {
            return;
        }
        try {
            if (pessoaSelecionada == null) {
                if (getParametroInstanciado(prUsuarioCLiente).isValorDoParametroFoiConfigurado()) {
                    usuarioCliente = (UsuarioCrmCliente) getParametroInstanciado(prUsuarioCLiente).getValor();
                    pessoaSelecionada = usuarioCliente.getRepresentanteLegal();
                }
            }

            usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
            if (getParametroInstanciado(prchamado).isValorDoParametroFoiConfigurado()) {
                usuarioCliente = ((ChamadoCliente) getParametroInstanciado(prchamado).getValor()).getUsuarioCliente();
                pessoaSelecionada = usuarioCliente.getRepresentanteLegal();
            }
        } finally {

            prConfigurado = true;
        }
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        defineParametros();
        FabAcaoCRMAtendimento acao = getEnumAcaoAtual();

        switch (acao) {
            case MEUS_CHAMADOS_FRM_TODOS_STATUS:
                ConsultaDinamicaDeEntidade consultaTodos = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEMPagina());

                if (pessoaSelecionada != null) {
                    consultaTodos.addCondicaoManyToManyContendoObjeto(CPChamadoCliente.pessoa, pessoaSelecionada);
                } else {
                    consultaTodos.addCondicaoManyToManyContendoObjeto(CPChamadoCliente.atendenteresponsavel, SBCore.getUsuarioLogado());
                }
                if (getParametroInstanciado(prUsuario).isValorDoParametroFoiConfigurado()) {
                    consultaTodos.addCondicaoManyToOneIgualA(CPChamadoCliente.atendenteresponsavel, (ComoEntidadeSimples) getParametroInstanciado(prUsuario).getValor());
                }
                List<ChamadoCliente> chamadosTodos = consultaTodos.resultadoRegistros();
                setEntidadesListadas(chamadosTodos);
                break;
            case MEUS_CHAMADOS_FRM_CHAMADOS_AGUARDANDO_ATENDIMENTO:
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEMPagina());
                consulta.addCondicaoManyToOneIgualA(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
                if (pessoaSelecionada != null) {
                    consulta.addCondicaoManyToManyContendoObjeto(CPChamadoCliente.pessoa, pessoaSelecionada);
                } else {
                    //  consulta.addCondicaoManyToManyContendoObjeto(CPChamadoCliente.atendenteresponsavel, SBCore.getUsuarioLogado());
                }
                if (getParametroInstanciado(prUsuario).isValorDoParametroFoiConfigurado()) {
                    consulta.addCondicaoManyToOneIgualA(CPChamadoCliente.atendenteresponsavel, (ComoEntidadeSimples) getParametroInstanciado(prUsuario).getValor());
                }
                List<ChamadoCliente> chamados = consulta.resultadoRegistros();
                setEntidadesListadas(chamados);
                break;
            case MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO:

                ConsultaDinamicaDeEntidade consultaEmAtendimento = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEMPagina());
                consultaEmAtendimento.addCondicaoManyToOneContemNoIntervalo(CPChamadoCliente.status, Lists.newArrayList(FabStatusChamado.EM_ATENDIMENTO.getRegistro(), FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro()));
                if (getParametroInstanciado(prUsuario).isValorDoParametroFoiConfigurado()) {
                    consultaEmAtendimento.addCondicaoManyToOneIgualA(CPChamadoCliente.atendenteresponsavel, (ComoEntidadeSimples) getParametroInstanciado(prUsuario).getValor());
                } else {

                    //consultaEmAtendimPgMeusChamadosAtdento.addCondicaoManyToManyContendoObjeto(CPChamadoCliente.atendenteresponsavel, SBCore.getUsuarioLogado());
                    if (pessoaSelecionada != null) {
                        consultaEmAtendimento.addCondicaoManyToManyContendoObjeto(CPChamadoCliente.pessoa, pessoaSelecionada);
                    } else {
                        consultaEmAtendimento.addCondicaoManyToManyContendoObjeto(CPChamadoCliente.atendenteresponsavel, SBCore.getUsuarioLogado());
                    }
                }

                List<ChamadoCliente> chamadosEmAtendimento = consultaEmAtendimento.resultadoRegistros();
                setEntidadesListadas(chamadosEmAtendimento);
                break;
        }
        UtilCRCListasObjeto.ordernarPorCampoReverso(getEntidadesListadas(), CPChamadoCliente.id);
    }

    public void abrirChamado() {
        String link = null;
        switch (getEntidadeSelecionada().getStatus().getFabStatus()) {
            case RASCUNHO:
                break;
            case AGUARDANDO_ATENDIMENTO:

                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_DEFINIR_ATENDIMENTO);
                link = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_DEFINIR_ATENDIMENTO, getEntidadeSelecionada());
                break;
            case EM_ATENDIMENTO:
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER);
                link = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER, getEntidadeSelecionada());
                break;
            case ATRAZADO:
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER);
                link = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER, getEntidadeSelecionada());
                break;
            case FINALIZADO:
                executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER);
                link = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER, getEntidadeSelecionada());
                break;
            default:
                throw new AssertionError(getEntidadeSelecionada().getStatus().getFabStatus().name());

        }
        if (link != null) {
            UtilSBWP_JSFTools.vaParaPagina(link);
        }
    }
    private ComoChatSalaBean canalRC;

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
        super.autoExecProximaAcaoAposController(pAcaoController, pResposta); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public ItfRespostaAcaoDoSistema autoExecAcaoController(ComoEntidadeSimples pEntidade) {
        return super.autoExecAcaoController(pEntidade); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setAcaoSelecionada(ComoAcaoDoSistema acaoSelecionada) {
        boolean atualizardados = false;
        if (this.acaoSelecionada != null) {
            if (!acaoSelecionada.equals(this.getAcaoSelecionada())) {
                FabAcaoCRMAtendimento acao = getEnumAcaoAtual();

                switch (acao) {

                    case MEUS_CHAMADOS_FRM_CHAMADOS_EM_ATENDIMENTO:
                    case MEUS_CHAMADOS_FRM_CHAMADOS_ATENDER:
                    case MEUS_CHAMADOS_FRM_TODOS_STATUS:
                        atualizardados = true;
                        break;

                    default:

                }
            }
        }

        super.setAcaoSelecionada(acaoSelecionada); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (atualizardados) {
            listarDados(true);
        }
    }

    @Override
    public ComoChatSalaBean getCanalDoChamado() {
        if (getEntidadeSelecionada() == null) {
            return null;
        }
        if (!getEntidadeSelecionada().getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())) {

            if (canalRC == null) {
                try {
                    canalRC = servicoRC.getSalaChamado(getEntidadeSelecionada());
                } catch (Throwable t) {

                }
            }
        }
        return canalRC;
    }

    @Override
    public ChamadoCliente getChamado() {
        return getEntidadeSelecionada();
    }

    private void atualizarUltimoToken() {
        ChamadoCliente chamadoToken = getEntidadeSelecionada();
        if (chamadoToken != null
                && chamadoToken.isChamadoEmEstadoAtendimento()) {
            ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR, chamadoToken, getEntidadeSelecionada().getUsuarioCliente().getEmail());
            ultimoToken = (TokenAcessoDinamico) token;
        }

    }

    @Override
    protected void renovarEMPagina() {
        super.renovarEMPagina(); //To change body of generated methods, choose Tools | Templates.
        if (usuario != null) {
            usuario = UtilSBPersistencia.loadEntidade(usuario, getEMPagina());
        }
        if (pessoaSelecionada != null) {
            pessoaSelecionada = UtilSBPersistencia.loadEntidade(pessoaSelecionada, getEMPagina());
        }
        if (usuarioCliente != null) {
            usuarioCliente = UtilSBPersistencia.loadEntidade(usuarioCliente, getEMPagina());
        }
    }

    public TokenAcessoDinamico getUltimoToken() {
        if (ultimoToken == null) {
            atualizarUltimoToken();
        }

        return ultimoToken;
    }

    public String getUrlAcessoDireto() {
        if (getUltimoToken() == null) {
            return null;
        }
        TokenAcessoDinamico token = getUltimoToken();
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB.getRegistro(), token);
        url = url.replace("https://crm.", "https://atendimento.");
        return url;
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return usuarioCliente;
    }

    public void salvarDescritivoTemporario() {

        ItfRespostaAcaoDoSistema resposta = ModuloCRMAtendimentoChamado.chamadoSalvarMerge(getEntidadeSelecionada());
        setEntidadeSelecionada((ChamadoCliente) resposta.getRetorno());
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

}
