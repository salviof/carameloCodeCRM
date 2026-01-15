/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SBBeanModel.etapas.B_Etapas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.modal.PgModalSBJSF;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
@InfoPagina(nomeCurto = "ENVDOC", tags = {"Envio de Documento"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_MB)
@Named
@ViewScoped
public class PgEnvioDocumento extends MB_PaginaConversation {

    private EnvioEmail envioDeDocumento;
    @Inject
    private PgModalSBJSF modal;

    private AtividadeCRM atividadeVinculada;
    private B_Etapas etapaEnvioDoc;
    private PessoaJuridica prospectoSelecionado;
    private List<PessoaJuridica> prospectos;

    @PostConstruct
    private void inicio() {
        try {
            //   etapaEnvioDoc = new B_Etapas(FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_GERAR_DOCUMENTO.getRegistro(),
            //           FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_ENVIAR_DOCUMENTO.getRegistro()
            //   );
            //    System.out.println(modal.getPaginaVinculada().getBeanSelecionado().toString());
            //atividadeVinculada = paginaAtividade.getNovaAtividade();
            if (atividadeVinculada != null) {
                //        envioDeDocumento = new EnvioEmail();
                //  envioDeDocumento.prepararNovoObjeto(paginaAtividade.getNovaAtividade());
                //  prospectoSelecionado = paginaAtividade.getNovaAtividade().getProspectoEmpresa();

//                acaoSelecionada =
                //          xhtmlAcaoAtual = atividadeVinculada.getFormularioExecucao();
                idAreaExbicaoAcaoSelecionada = "envioDoc";
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo atividades", t);
        }

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada();

    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return envioDeDocumento;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        envioDeDocumento = (EnvioEmail) pBeanSimples;
    }

    public EnvioEmail getEnvioDeDocumento() {
        return envioDeDocumento;
    }

    public void setEnvioDeDocumento(EnvioEmail envioDeDocumento) {
        this.envioDeDocumento = envioDeDocumento;
    }

    public void gerarDocumento() {
        UsuarioCRM usuario = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class, SBCore.getUsuarioLogado().getId(), getEMPagina());
        ItfResposta resp = ModuloCRMEmail.gerarDocumentos(atividadeVinculada, usuario);

        if (resp.isSucesso()) {
            executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_ETAPA_ENVIAR_DOCUMENTO);

        }

    }

    public B_Etapas getEtapaEnvioDoc() {
        return etapaEnvioDoc;
    }

    public AtividadeCRM getAtividadeVinculada() {
        return atividadeVinculada;
    }

    public void enviarArquivos() {
        ItfResposta resp = ModuloCRMAtendimento.envioDocumentoEnviar(envioDeDocumento).dispararMensagens();
    }

    public List<DocumentoAtividadeCRM> getDocumentosDaAtividade() {
        try {
            if (prospectoSelecionado == null) {
                return new ArrayList<>();
            }

            renovarEMPagina();
            atividadeVinculada = UtilSBPersistencia.loadEntidade(atividadeVinculada, getEMPagina());
            return atividadeVinculada.getDocumentosGerados();

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro listando documentos não enviados", t);
            return new ArrayList<>();
        }
    }

}
