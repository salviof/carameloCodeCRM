/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.ChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author salvio
 */
@ViewScoped
@Named
@InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.FORM_CHAT_INTERATIVO_MB)
@InfoPagina(nomeCurto = "FormularioInterativo", tags = {"FormularioInterativo"})
public class PgChatbotCliente extends MB_paginaCadastroEntidades {

    @InfoParametroURL(nome = "Atividade", tipoParametro = TIPO_PARTE_URL.ENTIDADE,
            representaEntidadePrincipalMB = false, tipoEntidade = AtividadeCRM.class,
            obrigatorio = true
    )
    private ParametroURL prAtividade;

    private UsuarioCrmCliente usuarioLogado;
    private AtividadeCRM atividade;

    @PostConstruct
    public void inicio() {

        usuarioLogado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        if (!getParametroInstanciado(prAtividade).isValorDoParametroFoiConfigurado()) {
            executaAcaoSelecionadaPorEnum(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM);
        }
        if (getParametroInstanciado(prAtividade).isValorDoParametroFoiConfigurado()) {
            atividade = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prAtividade).getValor(), getEMPagina());
        }
        if (!atividade.getProspectoEmpresa().equals(usuarioLogado.getRepresentanteLegal())) {
            executaAcaoSelecionadaPorEnum(FabAcaoPaginasDoSistema.PAGINA_NATIVA_ACESSO_NEGADO_FRM_SUB_FORM);
        }

    }

    public ChatBot getChatBot() {
        return atividade.getChatBot();
    }

    public String getLinkChat() {
        return atividade.getChatBot().getLink();
    }

}
