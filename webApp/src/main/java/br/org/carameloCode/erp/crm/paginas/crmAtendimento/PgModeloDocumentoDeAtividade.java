/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.util.UtilGeradorDocumentoCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.superBits.utilitario.editorArquivos.office.MapaSubstituicaoWord;

/**
 *
 * @author desenvolvedor
 */
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_MB_GERENCIAR)
@InfoPagina(nomeCurto = "DOCMOD", tags = "Modelos de Documentos")
@Named
@ViewScoped
public class PgModeloDocumentoDeAtividade extends MB_paginaCadastroEntidades<ModeloDocumentoTipoAtividade> {

    @InfoParametroURL(nome = "Parametro tipo modelo documento", tipoEntidade = ModeloDocumentoTipoAtividade.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false, representaEntidadePrincipalMB = true)
    private ParametroURL parametroModeloDocumento;
    private List<String> palavrasChave;

    public PgModeloDocumentoDeAtividade() {
        super(UtillSBWPReflexoesWebpaginas.getAcaoDeGestaoPorClasseDoMetodoChamado());

    }

    @PostConstruct
    public void inicio() {
        if (getEntidadesListadas().isEmpty()) {
            executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_LISTAR);
        }
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_GERAR_TESTE);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_REMOVER);

    }

    public List<String> getPalavrasChave() {
        if (palavrasChave == null || palavrasChave.isEmpty()) {
            if (getEntidadeSelecionada().getLeadParaTestes() != null) {
                PessoaJuridica prospecto = (PessoaJuridica) getEntidadeSelecionada().getLeadParaTestes();
                ModuloCRMAdmin.atualizarDadosProspectoTeste(getEntidadeSelecionada());
                prospecto = UtilSBPersistencia.loadEntidade(prospecto, getEMPagina());

                MapaSubstituicaoWord mapa = UtilGeradorDocumentoCRM.getMapaSubstituicao(prospecto, "");
                palavrasChave = mapa.getpalavrasChave();
            } else {
                palavrasChave = new ArrayList<>();
            }
        }
        return palavrasChave;
    }

    @Override
    public void executarAcao(ModeloDocumentoTipoAtividade pEntidadeSelecionada) {

        super.executarAcao(pEntidadeSelecionada); //To change body of generated methods, choose Tools | Templates.

        if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_MERGE)) {
            if (ModuloCRMAdmin.salvarModeloDocumentoTipoAtiviadade(pEntidadeSelecionada).isSucesso()) {
                executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_FRM_LISTAR);
            } else {
                setAcaoSelecionada(getAcaoAnteriorExecutada());
            }
        }
        if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.MODELOS_DOCUMENTOS_TIPO_ATIVIDADE_CTR_GERAR_TESTE)) {
            ItfResposta resp = ModuloCRMAdmin.gerarDocumentoProspectoTeste(pEntidadeSelecionada);
            if (resp.isSucesso()) {
                String url = (String) resp.getRetorno();
                UtilSBWP_JSFTools.vaParaPagina(url);
            }
        }

    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModeloDocumentoTipoAtividade getEntidadeSelecionada() {
        return super.getEntidadeSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

}
