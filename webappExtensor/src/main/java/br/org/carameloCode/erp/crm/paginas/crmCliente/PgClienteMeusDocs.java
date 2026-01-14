/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.crm.paginas.crmAtendimento.ServicosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCBytes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author salvio
 */
@ViewScoped
@Named
@InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.DOCUMENTOS_MB_GESTAO)
@InfoPagina(nomeCurto = "MACLI", tags = {"Meus arquivos"})
public class PgClienteMeusDocs extends MB_paginaCadastroEntidades<ArquivoCliente> {

    @InfoParametroURL(nome = "prCategoria", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = CategoriaArquivoCliente.class, obrigatorio = false)
    private ParametroURL prCategoria;

    @InfoParametroURL(nome = "prArquivo", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = ArquivoCliente.class, representaEntidadePrincipalMB = true, obrigatorio = false)
    private ParametroURL prArquivo;

    private CategoriaArquivoCliente categoriaSelecionada;
    private UsuarioCrmCliente usuarioLogado;
    private List<CategoriaArquivoCliente> categoriasExistentes;

    @PostConstruct
    public void inicio() {
        if (getParametroInstanciado(prCategoria).isValorDoParametroFoiConfigurado()) {
            categoriaSelecionada = (CategoriaArquivoCliente) getParametroInstanciado(prCategoria).getValor();
        }
        if (getParametroInstanciado(prArquivo).isValorDoParametroFoiConfigurado()) {
            setEntidadeSelecionada((ArquivoCliente) getParametroInstanciado(prArquivo).getValor());
            categoriaSelecionada = getEntidadeSelecionada().getCategoriaArqCli();
        }
        categoriasExistentes = UtilSBPersistencia.getListaTodos(CategoriaArquivoCliente.class, getEMPagina());
        usuarioLogado = (UsuarioCrmCliente) SBCore.getUsuarioLogado();
    }

    public CategoriaArquivoCliente getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(CategoriaArquivoCliente categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public List<CategoriaArquivoCliente> getCategoriasExistentes() {
        return categoriasExistentes;
    }

    public void abrirCategoria(CategoriaArquivoCliente pCategoria) {
        String url = MapaDeFormularios.getUrlFormulario(FabAcaoCRMCliente.DOCUMENTOS_FRM_LISTAR_ARQUIVOS_PASTA.getRegistro(), pCategoria);
        UtilSBWP_JSFTools.vaParaPagina(url);
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        if (categoriaSelecionada != null) {
            ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(ArquivoCliente.class, getEMPagina());
            novaconsulta.addCondicaoManyToOneIgualA(categoriaSelecionada);
            novaconsulta.addCondicaoManyToManyContendoObjeto("prospecto", usuarioLogado.getContatoClienteVinculado().getProspecto());
            setEntidadesListadas(novaconsulta.resultadoRegistros());
        } else {
            setEntidadesListadas(new ArrayList<>());
        }
    }

    public void enviarArquivosPessoa(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            ArquivoCliente arquivo = new ArquivoCliente();

            try {

                Pessoa prospecto = UtilSBPersistencia.loadEntidade(usuarioLogado.getRepresentanteLegal(), getEMPagina());
                arquivo.setProspecto(prospecto);
                arquivo.setCategoriaArqCli(categoriaSelecionada);
                arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().uploadArquivo(file.getFileName(), UtilCRCBytes.gerarBytePorInputstream(file.getInputStream()));
                prospecto.getArquivos().add(arquivo);
                ModuloCRMCliente.arquivoUpload(arquivo);
                renovarEMPagina();
                listarDados(true);
            } catch (IOException ex) {
                Logger.getLogger(ServicosCRM.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
