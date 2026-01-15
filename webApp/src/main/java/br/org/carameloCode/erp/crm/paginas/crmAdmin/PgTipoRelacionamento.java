/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.CPTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@ViewScoped
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_MB_GERENCIAR)
@InfoPagina(nomeCurto = "TR", tags = {"Tipo Relacionamento"})
@Named
public class PgTipoRelacionamento extends MB_paginaCadastroEntidades<TipoRelacionamento> {

    @InfoParametroURL(nome = "Relacionamento", tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false, tipoEntidade = TipoRelacionamento.class, representaEntidadePrincipalMB = true)
    private ParametroURL relacionamentoSelecionado;

    @PostConstruct
    public void inicio() {

        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_ATIVIDADES_DISPONIVEIS.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_RESPONSAVEIS.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_DADOS_NESCESSARIOS.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_REMOVER.getRegistro());
        removerAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_REMOVER.getRegistro());
        removerAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_MOVERLEADS.getRegistro());
        removerAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_CONVERTER.getRegistro());
    }

    public List<Pessoa> getPessoasComEsteRelacionamento() {
        return (List) getEntidadeSelecionada().getCPinst(CPTipoRelacionamento.pessoascomesterelacionamento).getValor();
    }

    @Override
    public void metodoRespostaModal(Object... pParametros) {
        super.metodoRespostaModal(pParametros); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void autoExecProximaAcaoAposController(ComoAcaoController pAcaoController, ItfRespostaAcaoDoSistema pResposta) {
        if (!pResposta.isSucesso()) {
            pResposta.dispararMensagens();
        }
        super.autoExecProximaAcaoAposController(pAcaoController, pResposta); //To change body of generated methods, choose Tools | Templates.
    }

}
