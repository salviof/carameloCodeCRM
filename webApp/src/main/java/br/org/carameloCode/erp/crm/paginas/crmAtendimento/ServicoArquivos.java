/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexado.CPArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivocliente.CPCategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivoequipe.CPCategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ComoPasta;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.DocsClienteDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.DocsEquipeDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
@RequestScoped
@Named
public class ServicoArquivos {

    private List<DocsClienteDaCategoria> categoriasCliente;
    private List<DocsEquipeDaCategoria> categoriasEnquipe;

    private EntityManager em;

    @PostConstruct
    public void inicio() {
        em = UtilSBPersistencia.getEntyManagerPadraoNovo();
    }

    @PreDestroy
    public void fim() {
        UtilSBPersistencia.fecharEM(em);
    }

    public synchronized List<DocsClienteDaCategoria> getDadosCategoriaCliente(CategoriaArquivoCliente pCategora, Pessoa p) {
        if (categoriasCliente == null) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();

            categoriasCliente = new ArrayList<>();
            List<CategoriaArquivoCliente> categorias = null;
            if (pCategora == null) {
                categorias = new ConsultaDinamicaDeEntidade(CategoriaArquivoCliente.class, em).addCondicaoManyToOneNulo(CPCategoriaArquivoCliente.pastapai).resultadoRegistros();
            } else {
                categorias = new ConsultaDinamicaDeEntidade(CategoriaArquivoCliente.class, em).addCondicaoManyToOneIgualA(CPCategoriaArquivoCliente.pastapai, pCategora).resultadoRegistros();
            }

            for (CategoriaArquivoCliente cat : categorias) {
                ConsultaDinamicaDeEntidade consulta = UtilSBPersistencia.gerarConsultaDeEntidade(ArquivoCliente.class, em);
                consulta.addCondicaoManyToOneIgualA("prospecto", p);
                consulta.addCondicaoManyToOneIgualA("categoriaArqCli", cat);

                List<ArquivoCliente> arquivos = consulta.resultadoRegistros();
                DocsClienteDaCategoria novoDocCat = new DocsClienteDaCategoria();
                novoDocCat.setArquivoAnexado(arquivos);
                novoDocCat.setQuantidade(arquivos.size());
                novoDocCat.setId((long) String.valueOf(p.getId()).concat(cat.getNome()).hashCode());
                novoDocCat.setNome(cat.getNome());
                novoDocCat.setCategoria(cat);
                novoDocCat.setIcone(cat.getIcone());
                categoriasCliente.add(novoDocCat);
            }

        }
        return categoriasCliente;
    }

    private ComoPasta pastaAtual = null;

    public synchronized ComoPasta getDadosCategoria(ArquivoAnexado pArquivo) {
        if (pastaAtual != null) {
            return pastaAtual;
        }
        if (pArquivo instanceof ArquivoCliente) {
            ArquivoCliente arqCli = (ArquivoCliente) pArquivo;
            ConsultaDinamicaDeEntidade consulta = UtilSBPersistencia.gerarConsultaDeEntidade(ArquivoCliente.class, em);
            consulta.addCondicaoManyToOneIgualA("prospecto", pArquivo.getProspecto());
            consulta.addCondicaoManyToOneIgualA("categoriaArqCli", pArquivo.getCategoriaArqEquipe());

            List<ArquivoCliente> arquivos = consulta.resultadoRegistros();
            DocsClienteDaCategoria novoDocCat = new DocsClienteDaCategoria();
            novoDocCat.setArquivoAnexado(arquivos);
            novoDocCat.setQuantidade(arquivos.size());
            novoDocCat.setId((long) String.valueOf(arqCli.getCategoriaArqCli().getId()).concat(arqCli.getCategoriaArqCli().getNome()).hashCode());
            novoDocCat.setNome(pArquivo.getCategoriaArqEquipe().getNome());
            novoDocCat.setCategoria(arqCli.getCategoriaArqCli());
            novoDocCat.setIcone(arqCli.getCategoriaArqCli().getIcone());
            return novoDocCat;
        } else {
            ConsultaDinamicaDeEntidade consulta = UtilSBPersistencia.gerarConsultaDeEntidade(ArquivoAnexado.class, em);
            consulta.addCondicaoManyToOneIgualA("prospecto", pArquivo.getProspecto());
            consulta.addCondicaoManyToOneIgualA(CPArquivoAnexado.categoriaarqequipe, pArquivo.getCategoriaArqEquipe());

            List<ArquivoAnexado> arquivos = consulta.resultadoRegistros();
            DocsEquipeDaCategoria novoDocCat = new DocsEquipeDaCategoria();
            novoDocCat.setArquivoAnexado(arquivos);
            novoDocCat.setQuantidade(arquivos.size());
            novoDocCat.setId((long) String.valueOf(pArquivo.getCategoriaArqEquipe().getId()).concat(pArquivo.getCategoriaArqEquipe().getNome()).hashCode());
            novoDocCat.setNome(pArquivo.getCategoriaArqEquipe().getNome());
            novoDocCat.setCategoria(pArquivo.getCategoriaArqEquipe());
            novoDocCat.setIcone(pArquivo.getCategoriaArqEquipe().getIcone());
            return novoDocCat;
        }
    }

    public synchronized List<DocsEquipeDaCategoria> getDadosCategoriaEquipe(CategoriaArquivoEquipe pCategora, Pessoa p) {
        if (categoriasEnquipe == null) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();

            categoriasCliente = new ArrayList<>();
            List<CategoriaArquivoCliente> categorias = null;
            if (pCategora == null) {
                categorias = new ConsultaDinamicaDeEntidade(CategoriaArquivoCliente.class, em).addCondicaoManyToOneNulo(CPCategoriaArquivoEquipe.pastapai).resultadoRegistros();
            } else {
                categorias = new ConsultaDinamicaDeEntidade(CategoriaArquivoCliente.class, em).addCondicaoManyToOneIgualA(CPCategoriaArquivoEquipe.pastapai, pCategora).resultadoRegistros();
            }

            for (CategoriaArquivoCliente cat : categorias) {
                ConsultaDinamicaDeEntidade consulta = UtilSBPersistencia.gerarConsultaDeEntidade(ArquivoCliente.class, em);
                consulta.addCondicaoManyToOneIgualA("prospecto", p);
                consulta.addCondicaoManyToOneIgualA("categoriaArqCli", cat);

                List<ArquivoCliente> arquivos = consulta.resultadoRegistros();
                DocsClienteDaCategoria novoDocCat = new DocsClienteDaCategoria();
                novoDocCat.setArquivoAnexado(arquivos);
                novoDocCat.setQuantidade(arquivos.size());
                novoDocCat.setId((long) String.valueOf(p.getId()).concat(cat.getNome()).hashCode());
                novoDocCat.setNome(cat.getNome());
                novoDocCat.setCategoria(cat);
                novoDocCat.setIcone(cat.getIcone());
                categoriasCliente.add(novoDocCat);
            }

        }
        return categoriasEnquipe;
    }

}
