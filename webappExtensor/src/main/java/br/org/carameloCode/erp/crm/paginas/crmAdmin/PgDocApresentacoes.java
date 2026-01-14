/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.apresentacao.DocumentoApresentacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServico;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author SalvioF
 */
@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_MB)
@InfoPagina(nomeCurto = "APR", tags = {"Documento Apresentação"})
@ViewScoped
public class PgDocApresentacoes extends MB_paginaCadastroEntidades<DocumentoApresentacao> {

    private List<TipoServico> servicos;

    @PostConstruct
    public void inicio() {
        servicos = UtilSBPersistencia.getListaTodos(TipoServico.class, getEMPagina());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_REMOVER);
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_ATIVAR_DESATIVAR);
        removerAcaoDeEntidade(FabAcaoCrmAdmin.DOCUMENTO_APRESENTACAO_CTR_UPLOAD_APRESENTACOES.getRegistro());
    }

    public List<TipoServico> getServicos() {
        return servicos;
    }

    @Override
    public void listarDados() {
        super.listarDados(true); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        super.listarDados(true); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executarAcao(DocumentoApresentacao pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); //To change body of generated methods, choose Tools | Templates.

    }

    public void receberDocumentos(FileUploadEvent pArquivo) {
        DocumentoApresentacao documento = new DocumentoApresentacao();
        String nomeArquivo = pArquivo.getFile().getFileName();

        documento.setNome(nomeArquivo);
        documento.setArquivo(nomeArquivo);
        documento.getCampoInstanciadoByNomeOuAnotacao("arquivo").getComoArquivoDeEntidade().uploadArquivo(nomeArquivo, pArquivo.getFile().getContent());

        setEntidadeSelecionada(documento);
        ModuloCRMAdmin.documentoApresentacaoSalvar(documento);

    }

    @Override
    public void metodoRespostaModal(Object... pParametros) {
        super.metodoRespostaModal(pParametros); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selAcaoDeCtrParaUltimoFrm() {
        super.selAcaoDeCtrParaUltimoFrm(); //To change body of generated methods, choose Tools | Templates.
    }

}
