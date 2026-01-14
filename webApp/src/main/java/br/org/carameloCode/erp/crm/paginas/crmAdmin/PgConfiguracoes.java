/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.crm.legado.EditorDeTema;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author SalvioF
 */
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_MB)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "CFGB", tags = {"Configurações Básicas"})
public class PgConfiguracoes extends MB_PaginaConversation {

    private EditorDeTema editor;
    private List<UsuarioCRM> usuarios;
    private UsuarioCRM usuarioSelecionado;

    public void listarUSuarios() {
        if (usuarios == null || usuarios.isEmpty()) {
            usuarios = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, getEMPagina()).addcondicaoCampoIgualA("tipoUsuario", "UsuarioCRM").resultadoRegistros();

        }
    }

    @Override
    public void executarAcaoSelecionada() {

        FabAcaoCrmAdmin acao = getEnumAcaoAtual();
        adicionarAcaoNoHistorico(acaoSelecionada);
        switch (acao) {
            case CONFIGURACOES_GERAIS_CTR_SALVAR_CONFIGURACAO:
                ModuloCRMAdmin.emailConfiguracoesSalvar(usuarioSelecionado).dispararMensagens();
                setAcaoSelecionada(getAcaoUltimoFormularioExecutado());
                break;
            case CONFIGURACOES_GERAIS_CTR_TESTAR_CONFIGURACAO:
                ModuloCRMAdmin.emailConfiguracoesTestar(usuarioSelecionado).dispararMensagens();
                setAcaoSelecionada(getAcaoUltimoFormularioExecutado());
                break;
            case CONFIGURACOES_GERAIS_FRM_CONFIGURAR_EMAILS:
                listarUSuarios();
                break;
            case CONFIGURACOES_GERAIS_CTR_RECEBER_EMAIL:
                if (!ModuloCRMEmail.emailTestar(UtilSBPersistencia.loadEntidade((UsuarioCRM) SBCore.getUsuarioLogado(), getEMPagina())).isSucesso()) {

                } else {
                    ModuloCRMAdmin.configuracoes_receber_email(UtilSBPersistencia.loadEntidade((UsuarioCRM) SBCore.getUsuarioLogado(), getEMPagina())).dispararMensagens();
                }
                setAcaoSelecionada(getAcaoUltimoFormularioExecutado());
                break;

            default:
                super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public void editarAssinaturaForm() {

        executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_EDITAR_ASSINATURA);

    }

    public void salvarConfiguracaoMailUsuario(UsuarioCRM pUsuario) {
        usuarioSelecionado = pUsuario;
        executarAcaoSelecionada();
    }

    public void testarConfiguracao(UsuarioCRM pUsuario) {
        usuarioSelecionado = pUsuario;
        executarAcaoSelecionada();
    }

    @PostConstruct
    public void inicio() {
        editor = new EditorDeTema("adamantium");
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

    public EditorDeTema getEditor() {
        return editor;
    }

    public void setEditor(EditorDeTema editor) {
        this.editor = editor;
    }

    public List<UsuarioCRM> getUsuarios() {
        listarUSuarios();
        return usuarios;
    }

    public UsuarioCRM getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(UsuarioCRM usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public void salvarAssinatura() {
        if (ModuloCRMAtendimento.meuPerfilSalvar(usuarioSelecionado).dispararMensagens().isSucesso()) {
            renovarEMPagina();
            usuarioSelecionado = UtilSBPersistencia.loadEntidade(usuarioSelecionado, getEMPagina());

        }
    }

    public void testarEnvioAssinatura() {
        ModuloCRMAdmin.emailConfiguracoesTestar(usuarioSelecionado).dispararMensagens();
    }

}
