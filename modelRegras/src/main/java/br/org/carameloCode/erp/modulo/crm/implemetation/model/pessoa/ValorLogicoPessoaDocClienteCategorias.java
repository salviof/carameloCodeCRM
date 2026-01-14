package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.DocsClienteDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivocliente.CPCategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.DOCCLIENTECATEGORIAS)
public class ValorLogicoPessoaDocClienteCategorias
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaDocClienteCategorias(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    boolean valoreDefinidos = false;

    @Override
    public synchronized Object getValor(Object... pEntidade) {

        if (!valoreDefinidos) {
            if (getPessoa().getId() != null) {
                if (getPessoa().getDocClienteCategorias() == null || getPessoa().getDocClienteCategorias().isEmpty()) {
                    getPessoa().setDocClienteCategorias(new ArrayList());
                    EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                    try {
                        List<CategoriaArquivoCliente> categorias = new ConsultaDinamicaDeEntidade(CategoriaArquivoCliente.class, em).addCondicaoManyToOneNulo(CPCategoriaArquivoCliente.pastapai).resultadoRegistros();

                        for (CategoriaArquivoCliente cat : categorias) {
                            ConsultaDinamicaDeEntidade consulta = UtilSBPersistencia.gerarConsultaDeEntidade(ArquivoCliente.class, em);
                            consulta.addCondicaoManyToOneIgualA("prospecto", getPessoa());
                            consulta.addCondicaoManyToOneIgualA("categoriaArqCli", cat);

                            List<ArquivoCliente> arquivos = consulta.resultadoRegistros();
                            DocsClienteDaCategoria novoDocCat = new DocsClienteDaCategoria();
                            novoDocCat.setArquivoAnexado(arquivos);
                            novoDocCat.setQuantidade(arquivos.size());
                            novoDocCat.setId((long) Long.valueOf(getPessoa().getId()).toString().concat(cat.getNome()).hashCode());
                            novoDocCat.setNome(cat.getNome());
                            novoDocCat.setCategoria(cat);
                            novoDocCat.setIcone(cat.getIcone());
                            getPessoa().getDocClienteCategorias().add(novoDocCat);
                        }

                    } finally {
                        UtilSBPersistencia.fecharEM(em);
                        valoreDefinidos = true;
                    }

                }
            }
        }
        return getPessoa().getDocClienteCategorias();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
