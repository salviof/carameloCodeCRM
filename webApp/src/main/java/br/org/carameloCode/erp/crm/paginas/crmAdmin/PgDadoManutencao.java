/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_MB)
@InfoPagina(nomeCurto = "MAPRO", tags = {"Manutenção dados Prospecto"})
@Named
@ViewScoped
public class PgDadoManutencao extends MB_paginaCadastroEntidades<PessoaJuridica> {

    private DadoCRM dadoCRMAtual;
    private int indice = 0;

    private boolean modoEdicao = false;

    @PostConstruct
    public void inicio() {
        getAcoesRegistros().clear();
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_VISUALIZAR_DADO);
    }

    public int getIndiceProximo() {
        if (indice >= getEntidadeSelecionada().getDadosColetados().size() - 1) {
            indice = 0;

        } else {
            indice++;
        }
        return indice;
    }

    public int getIndiceAnterior() {
        if (indice <= 0) {
            indice = 0;

        } else {
            indice--;
        }
        return indice;
    }

    @Override
    protected void autoExecAlterarFormulario(ItfAcaoFormulario pAcao, boolean pAlterouEntidade) {
        super.autoExecAlterarFormulario(pAcao, pAlterouEntidade);
        if (getEntidadeSelecionada() != null && !getEntidadeSelecionada().getDadosColetados().isEmpty()) {
            if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_VISUALIZAR_DADO)) {
                dadoCRMAtual = getEntidadeSelecionada().getDadosColetados().get(0);
            }
            if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_PROXIMO_DADO)) {
                dadoCRMAtual = getEntidadeSelecionada().getDadosColetados().get(getIndiceProximo());
            }
            if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_DADO_ANTERIOR)) {
                dadoCRMAtual = getEntidadeSelecionada().getDadosColetados().get(getIndiceAnterior());
            }
        } else {
            SBCore.getCentralDeMensagens().enviarMsgAvisoAoUsuario("Nenhum dado para ser Exibido");
        }
    }

    public void proximoDado() {

    }

    public DadoCRM getDadoCRMAtual() {
        return dadoCRMAtual;
    }

    public boolean isModoEdicao() {
        return modoEdicao;
    }

    public void setModoEdicao(boolean modoEdicao) {
        this.modoEdicao = modoEdicao;
    }

}
