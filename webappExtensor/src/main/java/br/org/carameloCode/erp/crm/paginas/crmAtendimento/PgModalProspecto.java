/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.UtilSBCrmEmailModel;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.abstrato.PgModalSubAcao;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.primefaces.event.TabChangeEvent;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 *
 *
 *
 * @author desenvolvedor
 */
@ViewScoped
@Named
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MODAL_CRM_MB_PROSPECTOS)
@InfoPagina(nomeCurto = "modalProspecto", tags = {"modalProsp"})
public class PgModalProspecto extends PgModalSubAcao implements Serializable {

    private final static ComoAcaoDoSistema ACAO_COMPARTILHAR = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS.getRegistro();
    private final static ComoAcaoDoSistema ACAO_SALVAR_ALTERACOES = FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_ARQUIVOS.getRegistro();
    private final static ComoAcaoDoSistema ACAO_SALVAR_DADOS_DINAMICOS = FabAcaoCRMAtendimento.PROSPECTO_CTR_ATIVIDADE_SALVAR_DADOS_DINAMICOS.getRegistro();
    private String textoTeste;
    private final List<DadoCRM> dadosDinamicos;

    private ContatoProspecto contatoSelecionado;

    private Pessoa prospectoSelecionado;
    private boolean aberto;

    private TabView tabulacao;

    private EnvioEmail envioEmail;

    public PgModalProspecto() {
        this.dadosDinamicos = new ArrayList<>();
    }

    @Override
    public void executarAcaoSelecionada() {

        if (isAcaoSelecionadaIgualA(FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_CTR_ENVIAR_EMAIL)) {

            ItfResposta resp = ModuloCRMAtendimento.envioDocumentoEnviar(envioEmail).dispararMensagens();

            setAcaoSelecionada(FabAcaoCRMAtendimento.MODAL_CRM_FRM_EMAIL_RAPIDO.getRegistro());
            if (resp.isSucesso()) {
                try {

                    resp.dispararMensagens();
                } catch (Throwable t) {

                }

                fecharModal();
            }

        } else {
            super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void setAcaoSelecionada(ComoAcaoDoSistema pAcaoSelecionada) {
        if (pAcaoSelecionada.isUmaAcaoFormulario() || pAcaoSelecionada.equals(FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_CTR_ENVIAR_EMAIL.getRegistro())) {
            super.setAcaoSelecionada(pAcaoSelecionada);
        }
    }

    public void atualizarProspectoEmail() {
        renovarEMPagina();
        setProspectoSelecionado(UtilSBPersistencia.loadEntidade(prospectoSelecionado, getEMPagina()));
    }

    public void atualizarProspecto() {
        renovarEMPagina();
        dadosDinamicos.clear();
        setProspectoSelecionado(UtilSBPersistencia.loadEntidade(prospectoSelecionado, getEMPagina()));
    }

    public void fecharModal() {

        aberto = false;

        try {
            PrimeFaces.current().dialog().closeDynamic(prospectoSelecionado);
        } catch (Throwable t) {
            // ignora erro quando não configurado
        }
    }

    @Override
    public void fecharPagina() {
        super.fecharPagina(); //To change body of generated methods, choose Tools | Templates.
        fecharModal();
    }

    public Pessoa getProspectoSelecionado() {
        return prospectoSelecionado;
    }

    protected void setProspectoSelecionado(Pessoa pProspectoSelecionado) {
        envioEmail = null;
        aberto = true;
        this.prospectoSelecionado = pProspectoSelecionado;
    }

    public ComoAcaoDoSistema getAcaoCompartilhar() {
        return ACAO_COMPARTILHAR;
    }

    public ComoAcaoDoSistema getAcaoSalvarAlteracoes() {
        return ACAO_SALVAR_ALTERACOES;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public void fechar() {
        fecharModal();
    }

    public void salvarAlteracoes() {
        ItfResposta resp = ModuloCRMAtendimento.prospectoSalvar(prospectoSelecionado);
        if (resp.isSucesso()) {
            prospectoSelecionado = (Pessoa) resp.getRetorno();
            fecharModal();

        } else {
            resp.dispararMensagens();
        }
    }

    public void salvarAlteracoesContato() {
        if (contatoSelecionado == null) {
            SBCore.enviarAvisoAoUsuario("Selecione um contato");
        } else {
            ItfResposta resp = ModuloCRMAtendimento.contatoSalvarMerge(contatoSelecionado);
            if (resp.isSucesso()) {

                fecharModal();

            } else {
                resp.dispararMensagens();
            }
        }
    }

    public void abandonarProspecto() {
        ItfResposta resp = ModuloCRMAtendimento.prospectoAbandonar(prospectoSelecionado);
        if (resp.isSucesso()) {
            fecharModal();
        } else {
            resp.dispararMensagens();
        }
    }

    public void salvarAnexos() {

        ItfResposta resp = ModuloCRMAtendimento.prospectoSalvarAnexos(prospectoSelecionado, (List<ArquivoAnexado>) prospectoSelecionado.getCPinst("arquivos").getValor());
        if (resp.isSucesso()) {
            fecharModal();

        } else {
            resp.dispararMensagens();
        }
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return getProspectoSelecionado();
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        setProspectoSelecionado((Pessoa) pBeanSimples);
    }

    public List<DadoCRM> gerarDadosCRM(Pessoa prosp, TipoRelacionamento pTipoRelacionamento) {
        try {
            dadosDinamicos.clear();

            for (TipoDadoCRM tipoDado : pTipoRelacionamento.getDadosNescessarios()) {
                DadoCRM dadoCRM = new DadoCRM(prosp);
                dadoCRM.setTipoDadoCRM(tipoDado);
                dadoCRM.setTipoRelacionamentoVinculado(pTipoRelacionamento);
                if (!prosp.getDadosColetados().contains(dadoCRM)) {
                    dadosDinamicos.add(dadoCRM);
                }
            }

            if (dadosDinamicos.isEmpty()) {
                SBCore.enviarAvisoAoUsuario("Todos os dados para iniciar um relacionamento " + pTipoRelacionamento.getNome() + " já foram coletados");
            }
            return dadosDinamicos;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processando dados CRM", t);
            return new ArrayList<>();
        }
    }

    public List<DadoCRM> getDadosDinamicos() {
        return dadosDinamicos;
    }

    public void salvarDAdosDinamicos(List<DadoCRM> pDadosDinamicos) {
        ItfResposta resp = ModuloCRMAtendimento.prospectoDadosDinamicosSalvar(pDadosDinamicos);
        if (resp.isSucesso()) {
            fechar();
        }
    }

    public void salvarDAdosColetados() {

        ItfResposta resp = ModuloCRMAtendimento.prospectoDadosDinamicosSalvar(getProspectoSelecionado().getDadosColetados());
        if (resp.isSucesso()) {
            fechar();
        }
    }

    public void salvarDAdosDinamicos() {
        salvarDAdosDinamicos(dadosDinamicos);

    }

    public ComoAcaoDoSistema getAcaoSalvarDadosDinamicos() {
        return ACAO_SALVAR_DADOS_DINAMICOS;
    }

    public void onTabChange(TabChangeEvent event) {
        String titulo = event.getTab().getTitle();
        for (TipoRelacionamento tipo : prospectoSelecionado.getRelacionamentosComDadosColetados()) {
            if (titulo.contains(tipo.getNome())) {

            }
        }
        //   SBCore.enviarMensagemUsuario(titulo, FabMensagens.AVISO);
        //   textoTeste = titulo;
        //
        tabulacao.setTabindex("0");

        atualizarIdAreaExibicaoAcaoSelecionada();
    }

    public String getTextoTeste() {
        return textoTeste;
    }

    public TabView getTabulacao() {
        return tabulacao;
    }

    public void setTabulacao(TabView tabulacao) {
        this.tabulacao = tabulacao;
    }

    public EnvioEmail getEnvioEmail() {
        if (envioEmail == null) {
            envioEmail = new EnvioEmail();
            envioEmail.setProspecto(prospectoSelecionado);
            UsuarioCRM usuario = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class, SBCore.getUsuarioLogado().getId(), getEMPagina());

            UtilSBCrmEmailModel.aplicarModelov2(envioEmail, usuario);

        }
        return envioEmail;
    }

    public ContatoProspecto getContatoSelecionado() {
        return contatoSelecionado;
    }

    public void setContatoSelecionado(ContatoProspecto contatoSelecionado) {
        this.contatoSelecionado = contatoSelecionado;
    }

    public void excluirContato() {
        if (contatoSelecionado == null) {
            SBCore.enviarAvisoAoUsuario("Selecione um contato");
            return;
        }
        ItfRespostaAcaoDoSistema resp = ModuloCRMAtendimento.contatoRemover(contatoSelecionado).dispararMensagens();
        if (resp.isSucesso()) {
            renovarEMPagina();
            setBeanSelecionado(UtilSBPersistencia.loadEntidade(getBeanSelecionado(), getEMPagina()));
            setContatoSelecionado(null);
        }
    }

    public void novoContatoFormulario() {
        setContatoSelecionado(new ContatoProspecto());
        try {
            getContatoSelecionado().prepararNovoObjeto(getBeanSelecionado());
        } catch (ErroPreparandoObjeto ex) {
            SBCore.enviarAvisoAoUsuario("Selecione um Lead para adicionar um contato");
        }
        getProspectoSelecionado().getContatosProspecto().add(getContatoSelecionado());
        executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MODAL_CRM_FRM_CONTATO_NOVO);
    }

    public void listarContatos() {
        executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MODAL_CRM_FRM_CONTATOS);
    }
}
