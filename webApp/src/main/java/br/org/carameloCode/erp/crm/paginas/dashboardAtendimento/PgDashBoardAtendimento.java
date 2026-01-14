/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.dashboardAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "PAtend", tags = {"Painel do atendimento"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.MEU_DASHBOARD_MB_GESTAO)
public class PgDashBoardAtendimento extends MB_PaginaConversation {

    @InfoParametroURL(nome = "usuario selecionado", tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false, tipoEntidade = UsuarioCRM.class)
    private ParametroURL prUsuarioSelecionado;
    private UsuarioCRM usuarioSelecionado;

    private UsuarioCRM usuariologado;

    private PesquisaLead pesquisaLead;
    private OrigemProspecto origemSelecionada;
    private int codigoMetaGraficoSelecionada;

    @PostConstruct
    public void inicio() {
        usuariologado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        if (getAcaoSelecionada() == null) {
            executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_TOTAIS);
        }

    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return usuarioSelecionado;
    }

    public UsuarioCRM getUsuarioSelecionado() {
        if (!SBCore.getUsuarioLogado().getGrupo().equals(FabGruposIntranetCasaNova.CRM_ADMIN.getRegistro())) {
            usuarioSelecionado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
        } else {
            if (getParametroInstanciado(prUsuarioSelecionado).isValorDoParametroFoiConfigurado()) {
                usuarioSelecionado = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prUsuarioSelecionado).getValor(), getEMPagina());
            } else {
                usuarioSelecionado = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina());
            }
        }

        return usuarioSelecionado;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {
        usuarioSelecionado = (UsuarioCRM) pBeanSimples;
    }

    public UsuarioCRM getUsuariologado() {
        return usuariologado;
    }

    public PesquisaLead getPesquisaLead() {
        if (pesquisaLead == null) {
            pesquisaLead = new PesquisaLead();
            pesquisaLead.setUsuario(getUsuarioSelecionado());
        }
        return pesquisaLead;
    }

    public OrigemProspecto getOrigemSelecionada() {
        return origemSelecionada;
    }

    public void setOrigemSelecionada(OrigemProspecto origemSelecionada) {
        this.origemSelecionada = origemSelecionada;
    }

    public int getCodigoMetaGraficoSelecionada() {
        return codigoMetaGraficoSelecionada;
    }

    public void setCodigoMetaGraficoSelecionada(int codigoMetaGraficoSelecionada) {
        this.codigoMetaGraficoSelecionada = codigoMetaGraficoSelecionada;
    }

    public void salvarOrigemPrivadaSelecionada() {
        ModuloCRMAtendimento.origemSalvarMerge((OrigemProspectoPrivado) origemSelecionada).dispararMensagens();
    }
}
