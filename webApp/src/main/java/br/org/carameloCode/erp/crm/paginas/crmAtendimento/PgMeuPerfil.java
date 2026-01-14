/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.CPUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.qualificadoresCDI.sessao.QlSessaoFacesContext;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;

/**
 *
 * @author SalvioF
 */
@ViewScoped
@Named
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEU_PERFIL_MB)
@InfoPagina(nomeCurto = "MP", tags = {"Meu Perfil"})
public class PgMeuPerfil extends MB_PaginaConversation {

    private UsuarioCRM usuario;

    @Inject
    @QlSessaoFacesContext
    private SessaoAtualSBWP sessao;

    @PostConstruct
    public void inicio() {
        atualizarUsuario();

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
        super.autoExecProximaAcaoAposController(pAcaoController, pResposta); //To change body of generated methods, choose Tools | Templates.
    }

    private void atualizarUsuario() {
        renovarEMPagina();
        usuario = UtilSBPersistencia.getRegistroByID(UsuarioCRM.class, sessao.getUsuario().getId(), getEMPagina());

    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        usuario = (UsuarioCRM) pBeanSimples;
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        if (usuario != null) {
            CaixaPostal caixapostal = (CaixaPostal) usuario.getCPinst(CPUsuarioCRM.caixapostalprincipal).getValor();
            if (UtilCRCStringValidador.isNuloOuEmbranco(caixapostal.getUsuarioRecepcao())) {
                caixapostal.setUsuarioRecepcao(usuario.getEmail());
            }
        }

        return usuario;
    }

    public UsuarioCRM getUsuario() {
        return usuario;
    }

    public void salvarAlteracoes() {
        String teste = SBCore.getServicoArquivosDeEntidade().getEndrLocalImagens();
        System.out.println(teste);
        if (ModuloCRMAtendimento.meuPerfilSalvar(usuario).isSucesso()) {
            atualizarUsuario();
            atualizarIdAreaExibicaoAcaoSelecionada();
        }

    }

    public void enviarEmailTest() {
        salvarAlteracoes();
        ModuloCRMAtendimento.meuPerfilTestarEnvioEmail(usuario);
    }

    public void salvarConfigurcacaoServidor() {

        ModuloCRMAdmin.emailConfiguracoesSalvar(usuario).dispararMensagens();
    }

    public void testarServidor() {
        ModuloCRMAdmin.emailConfiguracoesTestar(usuario).dispararMensagens();
    }

}
