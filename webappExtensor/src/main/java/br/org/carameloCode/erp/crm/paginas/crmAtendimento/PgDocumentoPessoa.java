/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.ItfPaginaComModalProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.CPArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.api.model.arquivocliente.CPArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubpastaCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.DocsClienteDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.DocsEquipeDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.faces.event.AjaxBehaviorEvent;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "DocPrp", tags = {"Documentos de Pessoas"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_MB_GESTAO)
public class PgDocumentoPessoa
        extends MB_paginaCadastroEntidades<ArquivoAnexado> implements ItfPaginaComModalProspecto {

    @InfoParametroURL(nome = "prPessoa", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Pessoa.class)
    private ParametroURL prPessoa;

    @InfoParametroURL(nome = "prCatEquipe", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = CategoriaArquivoCliente.class, obrigatorio = false)
    private ParametroURL prCategoria;

    @InfoParametroURL(nome = "prCatCliente", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = CategoriaArquivoEquipe.class, obrigatorio = false)
    private ParametroURL prCategoriaEquipe;

    @InfoParametroURL(nome = "prSubPastaCliente", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = SubpastaCliente.class, obrigatorio = false)
    private ParametroURL prSubPastaCliente;

    @InfoParametroURL(nome = "prSubPastaEquipe", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = SubPastaEquipe.class, obrigatorio = false)
    private ParametroURL prsubPastaEquipe;

    private Pessoa pessoa;

    private CategoriaArquivoCliente categoriaArquivoCliente;
    private CategoriaArquivoEquipe categoriaArquivoEquipe;

    private SubPastaEquipe subPastaEquipe;

    private SubpastaCliente subPastaCliente;

    private List<DocsClienteDaCategoria> categoriasCliente;
    private List<DocsEquipeDaCategoria> categoriasEquipe;

    private List<SubPastaEquipe> subPastasEquipe;
    private List<SubpastaCliente> subPastasCliente;

    @PostConstruct
    public void inicio() {
        if (getParametroInstanciado(prPessoa).isValorDoParametroFoiConfigurado()) {
            pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoa).getValor(), getEMPagina());
        }

        if (getParametroInstanciado(prCategoriaEquipe).isValorDoParametroFoiConfigurado()) {
            categoriaArquivoEquipe = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prCategoriaEquipe).getValor(), getEMPagina());
            subPastasEquipe = categoriaArquivoEquipe.getSubpastasPrivadas();
        }
        if (getParametroInstanciado(prCategoria).isValorDoParametroFoiConfigurado()) {
            categoriaArquivoCliente = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prCategoria).getValor(), getEMPagina());
            subPastasCliente = new ArrayList();
            categoriaArquivoCliente.getSubpastasPrivadas().forEach(subPastasCliente::add);

        }

        if (getParametroInstanciado(prSubPastaCliente).isValorDoParametroFoiConfigurado()) {
            subPastaCliente = UtilSBPersistencia.loadEntidade((SubpastaCliente) getParametroInstanciado(prSubPastaCliente).getValor(), getEMPagina());
            subPastasCliente = new ArrayList();
            subPastaCliente.getSubPastas().forEach(subPastasCliente::add);
        }
        if (getParametroInstanciado(prsubPastaEquipe).isValorDoParametroFoiConfigurado()) {
            subPastaEquipe = UtilSBPersistencia.loadEntidade((SubPastaEquipe) getParametroInstanciado(prsubPastaEquipe).getValor(), getEMPagina());
            subPastasEquipe = subPastaEquipe.getSubPastas();
        }

    }

    public void definirSubpastasEquipe() {

        if (UtilCRCListas.isNuloOuVazio(categoriasEquipe)) {
            if (categoriaArquivoEquipe != null) {
                categoriasEquipe = new ArrayList<>();
                if (categoriaArquivoEquipe != null) {
                    categoriaArquivoEquipe.getSubPastas().forEach(item -> {
                        DocsEquipeDaCategoria docCategoria = new DocsEquipeDaCategoria();
                        docCategoria.preparacaoObjeto(item);
                        ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(ArquivoAnexado.class, getEMPagina());
                        novaConsulta.addCondicaoManyToOneIgualA(CPArquivoAnexado.prospecto, getPessoa());
                        novaConsulta.addCondicaoManyToOneIgualA(CPArquivoAnexado.categoriaarqequipe, item);
                        List<ArquivoAnexado> arquivos = novaConsulta.resultadoRegistros();
                        docCategoria.setArquivoAnexado(arquivos);
                        docCategoria.setQuantidade(arquivos.size());

                        categoriasEquipe.add(docCategoria);

                    });
                }
            }
            if (categoriaArquivoCliente != null) {
                definirSubpastasCliente();
            }

        }
    }

    public void definirSubpastasCliente() {
        if (UtilCRCListas.isNuloOuVazio(categoriasEquipe)) {
            categoriasCliente = new ArrayList<>();
            categoriaArquivoCliente.getSubPastas().forEach(item -> {
                DocsClienteDaCategoria docCategoria = new DocsClienteDaCategoria();
                docCategoria.preparacaoObjeto(item);

                ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(ArquivoCliente.class, getEMPagina());
                novaConsulta.addCondicaoManyToOneIgualA(CPArquivoAnexado.prospecto, getPessoa());
                novaConsulta.addCondicaoManyToOneIgualA(CPArquivoCliente.categoriaarqcli, item);
                List<ArquivoCliente> arquivos = novaConsulta.resultadoRegistros();
                docCategoria.setArquivoAnexado(arquivos);
                docCategoria.setQuantidade(arquivos.size());
                categoriasCliente.add(docCategoria);
            });

        }
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        if (getAcaoSelecionada().getComoAcaoDeEntidade().getClasseRelacionada().getSimpleName().equals(ArquivoAnexado.class.getSimpleName())) {

            Class classePEsquisa = ArquivoAnexado.class;
            if (categoriaArquivoCliente != null) {
                classePEsquisa = ArquivoCliente.class;
            }

            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(classePEsquisa, getEMPagina());
            consulta.addCondicaoManyToOneIgualA("prospecto", pessoa);
            if (subPastaEquipe != null) {
                SubPastaPrivada subpastaPrivada = new SubPastaPrivada();
                subpastaPrivada.setId(subPastaEquipe.getId());
                consulta.addCondicaoManyToOneIgualA(CPArquivoAnexado.subpasta, subpastaPrivada);
            } else {
                if (categoriaArquivoCliente != null) {
                    consulta.addCondicaoManyToOneIgualA("categoriaArqCli", categoriaArquivoCliente);
                } else if (categoriaArquivoEquipe != null) {
                    consulta.addCondicaoManyToOneIgualA("categoriaArqEquipe", categoriaArquivoEquipe);
                }

                consulta.addCondicaoManyToOneNulo(CPArquivoAnexado.subpasta);
            }

            setEntidadesListadas(consulta.resultadoRegistros());
        }
        if (getAcaoSelecionada().getComoAcaoDeEntidade().getClasseRelacionada().getSimpleName().equals(ArquivoCliente.class.getSimpleName())) {
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ArquivoCliente.class, getEMPagina());
            consulta.addCondicaoManyToOneIgualA("prospecto", pessoa);
            if (categoriaArquivoCliente != null) {
                consulta.addCondicaoManyToOneIgualA("categoriaArqCli", categoriaArquivoCliente);
            }
            //     definirSubpastasCliente();
            setEntidadesListadas(consulta.resultadoRegistros());
        }

    }

    @Override
    public void onModalProspectoClose(SelectEvent event) {
        renovarEMPagina();
        Pessoa pessoa = (Pessoa) event.getObject();

        //  paginaUtil.atualizaTelaPorID("cardPessoa" + pessoa.getId());
        //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onModalProspectoOpen(AjaxBehaviorEvent event) {
        renovarEMPagina();
        if (getEntidadeSelecionada() != null) {
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), getEMPagina()));
        }
    }

    public CategoriaArquivoCliente getCategoriaArquivoCliente() {
        return categoriaArquivoCliente;
    }

    public CategoriaArquivoEquipe getCategoriaArquivoEquipe() {
        return categoriaArquivoEquipe;
    }

    public List<DocsClienteDaCategoria> getCategoriasCliente() {
        return categoriasCliente;
    }

    public List<DocsEquipeDaCategoria> getCategoriasEquipe() {
        definirSubpastasEquipe();
        return categoriasEquipe;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public SubPastaEquipe getSubPastaEquipe() {
        return subPastaEquipe;
    }

    public SubpastaCliente getSubPastaCliente() {
        return subPastaCliente;
    }

    public List<SubpastaCliente> getSubPastasCliente() {
        return subPastasCliente;
    }

    public List<SubPastaEquipe> getSubPastasEquipe() {
        return subPastasEquipe;
    }

}
