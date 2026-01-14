/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.model.documentoapresentacao.CPDocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.apresentacao.DocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServico;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

/**
 *
 * @author desenvolvedor
 */
@InfoPagina(nomeCurto = "GSD", tags = "Gerenciar Servicos Disponiveis")
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.SERVICO_DIPONIVEL_MB_GESTAO)
@Named
@ViewScoped
public class PgServicoDisponivel extends MB_paginaCadastroEntidades<TipoServico> {

    @RequestScoped
    private PgUtil pgUtil;

    public PgServicoDisponivel() {
        super(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_MB_GESTAO.getRegistro().getComoGestaoEntidade());
    }

    @PostConstruct
    public void inicio() {
        listarDados();
        removerAcaoDeEntidade(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_REMOVER.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_CONVERTER_PARA_SAZONAL);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_CONVERTER_PARA_RECORRENTE);
    }

    @Override
    public void executarAcao(TipoServico pEntidadeSelecionada) {

        super.executarAcao(pEntidadeSelecionada);

        FabAcaoCrmAdmin fabricaAcao = (FabAcaoCrmAdmin) getEnumAcaoAtual();

        switch (fabricaAcao) {

            case SERVICO_DIPONIVEL_CTR_SALVAR_MERGE:

                ItfResposta resp = ModuloCRMAdmin.tipoServicoSalvarMerge(pEntidadeSelecionada);

                if (resp.isSucesso()) {

                    xhtmlAcaoAtual = acaoListarRegistros.getXhtml();
                    pgUtil.atualizaTelaPorID("formulario");
                }

                break;

        }
        if (acaoSelecionada.isUmaAcaoFormulario()) {
            getPaginaUtil().atualizaTelaPorID("@form");
        }

    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        setEntidadesListadas(UtilSBPersistencia.getListaTodos(TipoServico.class, getEMPagina()));
    }

    public void fecharExluirServico(SelectEvent event) {

        FabTipoRespostaComunicacao resp = mapaRespostasComunicacaoTransienteDeAcaoByAcoes.get(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_REMOVER.getRegistro().getNomeUnico());
        if (resp.isRespostaPositiva()) {
            super.metodoRespostaModal(event.getObject());

            UtilSBWP_JSFTools.vaParaPagina(MapaDeFormularios.getUrlFormulario(FabAcaoCrmAdmin.SERVICO_DIPONIVEL_FRM_LISTAR.getRegistro()));
        } else {
            executaAcaoSelecionadaPorEnum(getAcaoUltimoFormularioExecutado().getEnumAcaoDoSistema());
        }
        mapaRespostasComunicacaoTransienteDeAcaoByAcoes.clear();
    }

    public List<Orcamento> getOrcametosDoServico() {
        if (getEntidadeSelecionada() == null) {
            return new ArrayList<>();
        }
        List<Orcamento> orcamentos = UtilSBPersistencia.getListaRegistrosByHQL("SELECT orc from " + Orcamento.class.getSimpleName() + " orc  \n"
                + " JOIN orc.servicoOferecido srvof \n"
                + " JOIN srvof.tipoServico ts \n"
                + "where srvof.tipoServico.id=" + getEntidadeSelecionada().getId(), -1, getEMPagina());
        return orcamentos;

    }

    public List<DocumentoApresentacao> getApresentacoesDoServico() {
        if (getEntidadeSelecionada() == null) {
            return new ArrayList<>();
        }
        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(DocumentoApresentacao.class, getEMPagina());
        consulta.addCondicaoManyToManyContendoObjeto(CPDocumentoApresentacao.tiposservicovinculado, getEntidadeSelecionada());

        return consulta.resultadoRegistros();

    }

}
