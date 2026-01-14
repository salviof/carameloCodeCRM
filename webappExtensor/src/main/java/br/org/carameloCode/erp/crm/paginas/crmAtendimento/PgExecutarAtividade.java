/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.SelectEvent;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso.CodigoConviteAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail.ModuloCRMAtendimentoEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.LayoutTelaAreaConhecida;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.etapas.B_Etapas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.ConstAppFormEComponente.contantesView.LayoutsPaginas;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
@ViewScoped
@Named
@InfoPagina(nomeCurto = "CA", tags = {"Execucao Atividade"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB)
public class PgExecutarAtividade extends MB_PaginaDeAtividade implements ItfPaginaComModalEmail {

    @InfoParametroURL(nome = "Prospecto", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Pessoa.class,
            obrigatorio = true)
    protected ParametroURL prProspecto;
    @InfoParametroURL(nome = "Tipo Atividade",
            tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            tipoEntidade = TipoAtividadeCRM.class,
            obrigatorio = false)
    private ParametroURL prTipoAtividade;
    @InfoParametroURL(nome = "AtividadeGerada", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            obrigatorio = false, tipoEntidade = AtividadeCRM.class
    )
    private ParametroURL prAtividade;
    @InfoParametroURL(nome = "CodigoConvite", obrigatorio = false, tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = CodigoConviteAtividade.class)
    private ParametroURL prCodigoAcessoAtividade;

    @Inject
    private LayoutsPaginas layouts;
    @Inject
    private InfoWebApp infoWebApp;

    private String xhmlTemplateJSFExcucaoAtividade;

    private B_Etapas etapasRestantes;

    public PgExecutarAtividade() {
        super();
    }

    public List<ComoAcaoDoSistema> getAcoesDeProspecto() {
        return acoesDeProspecto;
    }

    public void setProspecto(Pessoa prospecto) {
        this.prospecto = prospecto;
    }

    @Override
    public Pessoa getProspecto() {
        if (super.getProspecto() == null) {
            return null;
        }
        try {
            super.getProspecto().getArquivos().size();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }

        return UtilSBPersistencia.loadEntidade(super.getProspecto(), getEMPagina()); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTipoAtividadeSelecionada(TipoAtividadeCRM tipoAtividadeSelecionada) {
        this.tipoAtividadeSelecionada = tipoAtividadeSelecionada;
    }

    public void concluirAtividade() {
        ItfResposta resposta;
        resposta = ModuloCRMAtendimento.atividadeConcluir(getAtividade());
        renovarEMPagina();
        if (resposta.getRetorno() != null) {
            atividade = (AtividadeCRM) resposta.getRetorno();
        }
        if (resposta.isSucesso()) {
            atividade = (AtividadeCRM) resposta.getRetorno();

            redirecionarAposSalvar();
        }

    }

    public void gerarDocumentos() {

        ControleGeracaoDocumento.iniciarProcessamento(atividade);
    }

    @Override
    protected void renovarEMPagina() {
        etapasRestantes = null;
        etapasFormularios = null;
        dadosDaAtividade = null;
        super.renovarEMPagina();
    }

    public void enviarConvite() {
        try {
            //    renovarEMPagina();
            ItfResposta resp = null;
            if (atividade.isTemCodigoRemoto()) {
                CodigoConviteAtividade convite = getAtividade().getCodigosConvite().get(0);
                String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_MB_CONVITE.getRegistro(), convite);
                convite.setConteudo(convite.getConteudo().replace("[URL_CHAMADA]", url));
                resp = ModuloCRMAtendimento.conviteEnviar(convite).dispararMensagens();

            } else {
                CodigoConviteAtividade codigo = new CodigoConviteAtividade();
                codigo.setAtividade(getAtividade());

                resp = ModuloCRMAtendimento.conviteEnviar(codigo);

            }
            if (resp.isSucesso()) {

                atualizarFormularioExecucao();
                String urlChamada = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.FORMULARIO_CONVIDADO_MB_CONVITE.getRegistro(), getAtividade().getConvitePrincipal());
                if (getAtividade().getConvitePrincipal().getConteudo() == null) {
                    getAtividade().getConvitePrincipal().setAssunto("Convite para colaborar");
                    getAtividade().getConvitePrincipal().setConteudo(atividade.getEmailVinculado().getTexto()
                    );
                    UtilSBPersistencia.mergeRegistro(getAtividade().getConvitePrincipal(), getEMPagina());
                }

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando código de convite", t);
        }

    }

    public void salvareConcluirDadosDinamicos() {
        try {
            vaiEditarDados = false;
            ModuloCRMAtendimento.prospectoSalvar(prospecto);
            atividade.setDadosRevisados(true);
            ItfResposta resp = ModuloCRMAtendimento.atividadeCRMComplementarSalvarDadosDinamicosEConcluir(atividade).dispararMensagens();

            renovarEMPagina();
            xhtmlAcaoAtual = atividade.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.formularioexecucao).getValor().toString();

            if (!xhtmlAcaoAtual.equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS.getRegistro().getComoFormulario().getXhtml())) {
                getPaginaUtil().atualizaTelaPorID(LayoutTelaAreaConhecida.AREA_CONTEUDO);
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro cadastrando dados dinamicos", t);
        }
    }

    public void salvarAnotacoes() {
        try {
            EntityManager novagestaoDeEntidade = UtilSBPersistencia.getNovoEMIniciandoTransacao();
            Query atualizacao = novagestaoDeEntidade.createNativeQuery(" update " + AtividadeCRM.class.getSimpleName() + " "
                    + "set anotacoes = :anotacao where id=:pIdAtividade");
            atualizacao.setParameter("anotacao", atividade.getAnotacoes());
            atualizacao.setParameter("pIdAtividade", atividade.getId());
            atualizacao.executeUpdate();
            UtilSBPersistencia.finalizarTransacao(novagestaoDeEntidade);

        } catch (Throwable t) {
            SBCore.enviarAvisoAoUsuario("Ouve um erro salvando a anotação");
        }
    }

    public void salvarDadosDinamicos() {
        try {
            ModuloCRMAtendimento.prospectoSalvar(prospecto);
            atividade.setDadosRevisados(true);
            atividade = UtilSBPersistencia.mergeRegistro(atividade, getEMPagina());
            ItfResposta resp = ModuloCRMEmail.atividadeCRMComplementarSalvarDadosDinamicos(atividade).dispararMensagens();
            //   vaiEditarDados = false;
            renovarEMPagina();

            xhtmlAcaoAtual = atividade.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.formularioexecucao).getValor().toString();
            if (!xhtmlAcaoAtual.equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS.getRegistro().getComoFormulario().getXhtml())) {
                getPaginaUtil().atualizaTelaPorID(LayoutTelaAreaConhecida.AREA_CONTEUDO);
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro cadastrando dados dinamicos", t);
        }
    }

    private void redirecionarAposSalvar() {

        String url = null;
        atividade = UtilSBPersistencia.loadEntidade(atividade, getEMPagina());
        switch (tipoExibicaoPagina) {

            case ATIVIDADE_CONVIDADO:
                break;
            default:
                if (atividade != null) {
                    url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro(), atividade.getProspectoEmpresa());
                } else {
                    url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_ESCOLHER_ATIVIDADE.getRegistro());
                }

        }
        if (url != null) {
            UtilSBWP_JSFTools.executarJavaScript("window.location.href = '" + url + "';");
        }

    }

    public void agendar() {

        if (atividade.getDataHoraPrevisaoExecucao() == null) {
            SBCore.enviarMensagemUsuario("Especifique a data do Agendamento", FabMensagens.ALERTA);
            return;
        }
        if (atividade.getDataHoraPrevisaoExecucao().getTime() < new Date().getTime() + 50000) {
            SBCore.enviarMensagemUsuario("O agendamento precisa ser uma data futura.", FabMensagens.ALERTA);
            return;
        }

        ItfResposta resposta = ModuloCRMAtendimento.agendarAtividade(atividade);
        if (resposta.isSucesso()) {
            vaiAgendar = false;
            atividade = (AtividadeCRM) resposta.getRetorno();
            redirecionarAposSalvar();
        }

    }

    public void salvarMaisTarde() {

        ItfResposta resposta;
        resposta = ModuloCRMAtendimento.salvarAtividadeComoInacabado(getAtividade());
        renovarEMPagina();
        if (resposta.isSucesso()) {
            atividade = (AtividadeCRM) resposta.getRetorno();
            redirecionarAposSalvar();
        }
    }

    public boolean isVaiAgendar() {
        //   vaiAgendar = atividade.getDataHoraPrevisaoExecucao() != null;
        return vaiAgendar;
    }

    public void alterarDadosAtividade() {
        vaiEditarDados = true;
        atualizarIdAreaExibicaoAcaoSelecionada();
    }

    public void alterarHorarioAgendamento() {
        vaiAgendar = true;
        atualizarIdAreaExibicaoAcaoSelecionada();
    }

    public void cancelarAgendamento() {
        vaiAgendar = false;
        atividade.setDataHoraInicioAtividade(null);
        atualizarIdAreaExibicaoAcaoSelecionada();
    }

    public void cancelarAtividade() {
        renovarEMPagina();
        ItfRespostaAcaoDoSistema resp = ModuloCRMAtendimento.atividadecancelaAtividade(atividade);
        resp.dispararMensagens();

        if (resp.isSucesso()) {
            atividade = (AtividadeCRM) resp.getRetorno();
        }

        renovarEMPagina();
        xhtmlAcaoAtual = atividade.getCampoInstanciadoByNomeOuAnotacao(CPAtividadeCRM.formularioexecucao).getValor().toString();

        atualizarIdAreaExibicaoAcaoSelecionada();

    }

    public synchronized B_Etapas getEtapas() {
        if (etapasFormularios == null) {
            etapasFormularios = getAtividade().getEtapasRestantes();

            if (!etapasFormularios.isEmpty()) {
                ItfAcaoFormulario acaoAtual = MapaAcoesSistema.getAcaoDoSistemaByFormulario(xhtmlAcaoAtual);
                List<ItfAcaoFormulario> lista = getAtividade().getEtapasRestantes();
                ComoAcaoDoSistema[] etapas = new ComoAcaoDoSistema[lista.size()];
                lista.toArray(etapas);
                etapasRestantes = new B_Etapas(acaoAtual, etapas);
            }

        }
        return etapasRestantes;
    }

    public boolean isTemEtapasAtividadeAtual() {
        return (getEtapas() == null);

    }

    public boolean isUltimoBloqueio() {
        return getAtividade().getListaBloqueios().size() < 2;
    }

    public List<ItfAcaoFormulario> getEtapasFormularios() {
        return etapasFormularios;
    }

    public void verificaProcessoDocumento() {

        ControleGeracaoDocumento.Geradordocumento processandoDocumento = ControleGeracaoDocumento.processoEmAndamento();
        if (processandoDocumento.isProcessoEmAndamento()) {
            System.out.println("Não terminou");
            UtilSBWP_JSFTools.executarJavaScript("PF('blokGerador').show();");

        } else {
            System.out.println("Terminou");
            if (!processandoDocumento.getResposta().isSucesso()) {
                processandoDocumento.getResposta().dispararMensagens();
            }
            UtilSBWP_JSFTools.executarJavaScript("PF('verificaGeracao').stop()");
            UtilSBWP_JSFTools.executarJavaScript("PF('blokGerador').hide();");
            atualizarFormularioExecucao();

        }
    }

    public String getHorarioTexto() {
        return horarioTexto;
    }

    public void setHorarioTexto(String horarioTexto) {
        this.horarioTexto = horarioTexto;
        atualizarAgendamento();
    }

    public void setDiaAgendamento(Date diaAgendamento) {
        this.diaAgendamento = diaAgendamento;
        atualizarAgendamento();
    }

    private void atualizarAgendamento() {

        if (diaAgendamento != null && horarioTexto != null) {
            String[] tempo = horarioTexto.split(":");
            if (tempo.length == 3) {
                String hora = tempo[0];
                String minuto = tempo[1];
                String segundo = tempo[2];
                if (Integer.valueOf(hora) != 0) {
                    String pData = UtilCRCDataHora.converteDateEmSTringDD_MM_YYYY(diaAgendamento);
                    pData = pData + " " + hora + ":" + minuto + ":" + segundo;
                    Date data = UtilCRCDataHora.converteStringEmDataEHora(pData);
                    atividade.setDataHoraPrevisaoExecucao(data);
                }
            }
        }

    }

    public Date getDiaAgendamento() {
        return diaAgendamento;
    }

    public boolean isEmModoColetarDAdos() {
        String arquivoform = getXhtmlAcaoAtual();
        if (arquivoform == null) {
            return false;
        }
        if (FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS.getRegistro().getComoFormulario().getXhtml().equals(arquivoform)) {
            return true;
        }
        return false;
    }

    @Override
    public String getXhtmlAcaoAtual() {
        String xhtml = null;
        if (vaiEditarDados) {
            xhtml = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_DADOS_OBRIGATORIOS.getRegistro().getComoFormulario().getXhtml();
        } else if (vaiAgendar) {
            xhtml = FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_AGENDAR.getRegistro().getComoFormulario().getXhtml();
        } else {
            xhtml = super.getXhtmlAcaoAtual(); //To change body of generated methods, choose Tools | Templates.
        }
        if (xhtml != null
                && xhtml.equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_COM_EMAIL.getRegistro().getComoFormulario().getXhtml())) {
            if (atividade.getEmailVinculado() == null) {
                atividade = (AtividadeCRM) ModuloCRMEmail.atividadeAplicarModeloEmail(atividade).getRetorno();
                renovarEMPagina();

                atividade = UtilSBPersistencia.loadEntidade(atividade, getEMPagina());
            }
        }
        return xhtml;
    }

    private List<DadoCRM> dadosDaAtividade;

    public List<DadoCRM> getDadosDaAtividade() {
        if (dadosDaAtividade == null || dadosDaAtividade.isEmpty()) {
            dadosDaAtividade = new ArrayList<>();
            if (!getAtividade().getTipoAtividade().isGeraNovoRelacionamento() && getAtividade().getTipoAtividade().getTiposDadosColetarNaAtividade().isEmpty()) {
                return dadosDaAtividade;
            }

            getAtividade().getDadosNaoColetados().stream().filter(da -> !dadosDaAtividade.contains(da)).forEach(dadosDaAtividade::add);
            prospecto.getDadosColetadosPorTipoAtividade(getAtividade().getTipoAtividade()).stream().filter(da -> !dadosDaAtividade.contains(da)).forEach(dadosDaAtividade::add);

        }
        dadosDaAtividade.stream().forEach(dado -> dado.setAtividadeCRM(atividade));
        return dadosDaAtividade;
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParametroProspecto() {
        return getParametroInstanciado(prProspecto);
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParametroAtividade() {
        return getParametroInstanciado(prAtividade);
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParaetroCodigoAcesso() {
        return getParametroInstanciado(prCodigoAcessoAtividade);
    }

    @Override
    public ItfParametroRequisicaoInstanciado getParaetroTipoAtividade() {
        return getParametroInstanciado(prTipoAtividade);
    }

    @Override
    public void onModalNovoContatoEmailClose(SelectEvent event) {
        onModalProspectoClose(event);
        renovarEMPagina();
        atividade = UtilSBPersistencia.loadEntidade(atividade, getEMPagina());
        getPaginaUtil().atualizaTelaPorID("idAreaSBConteudo");
    }

    @Override
    public void onModalNovoContatoEmailOpen(AjaxBehaviorEvent event) {

    }

    @Override
    public void onModalAnexoEmailClose(SelectEvent event) {
        onModalProspectoClose(event);
        renovarEMPagina();
    }

    public void onModalAnexoEmailClose() {
        renovarEMPagina();
    }

    @Override
    public void renovarEntityManager() {
        renovarEMPagina();
    }

    public synchronized void emailsalvarRascunho() {
        EntityManager emSalvarRascunho = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {
            UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), emSalvarRascunho);
            int tamanhoMinimo = 30;

            if (atividade.getEmailVinculado().getTexto() != null && atividade.getEmailVinculado().getTexto().length() > tamanhoMinimo) {
                ItfResposta resp = ModuloCRMAtendimentoEmail.salvarrascunho((EnvioEmail) atividade.getEmailVinculado());
                if (resp.isSucesso()) {

                    renovarEMPagina();
                    atividade.setEmailVinculado(UtilSBPersistencia.loadEntidade((EnvioEmail) resp.getRetorno(), getEMPagina()));
                }

            }

            try {
                //  ItfResposta resp = ModuloCRMAtendimento.atividadeEmailSalvarRascunhoEmail(atividade);
                //  if (resp.isSucesso()) {
                //atualizarFormularioExecucao();
                // }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro sobrescrevendo atividade Anterior", t);
            }
        } finally {
            UtilSBPersistencia.fecharEM(emSalvarRascunho);
        }
    }

    public String getXhmlTemplateJSFExcucaoAtividade() {
        if (getAtividade() == null) {
            xhmlTemplateJSFExcucaoAtividade = layouts.getModeloGeral().getXhtmlVinculado();
        } else {
            boolean temPlugin = atividade.getTipoAtividade().getAcaoDePLuginVunculado() != null;
            if (temPlugin) {
                if (atividade.isTemCodigoRemoto()) {
                    xhmlTemplateJSFExcucaoAtividade = layouts.getModeloGeral().getXhtmlVinculado();
                    return xhmlTemplateJSFExcucaoAtividade;
                }
                if (vaiEditarDados) {
                    xhmlTemplateJSFExcucaoAtividade = layouts.getModeloGeral().getXhtmlVinculado();
                    return xhmlTemplateJSFExcucaoAtividade;
                }

                if (vaiAgendar) {
                    xhmlTemplateJSFExcucaoAtividade = layouts.getModeloGeral().getXhtmlVinculado();
                    return xhmlTemplateJSFExcucaoAtividade;
                }
                if (xhtmlAcaoAtual.equals(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_FRM_ATIVIDADE_PLUGIN_DE_ATIVIDADE.getRegistro().getComoFormulario().getXhtml())) {

                    xhmlTemplateJSFExcucaoAtividade = "modeloAtividadePlugin.xhtml";
                } else {
                    xhmlTemplateJSFExcucaoAtividade = layouts.getModeloGeral().getXhtmlVinculado();
                }
                return xhmlTemplateJSFExcucaoAtividade;

            } else {
                xhmlTemplateJSFExcucaoAtividade = layouts.getModeloGeral().getXhtmlVinculado();
            }
        }

        return xhmlTemplateJSFExcucaoAtividade;
    }

}
