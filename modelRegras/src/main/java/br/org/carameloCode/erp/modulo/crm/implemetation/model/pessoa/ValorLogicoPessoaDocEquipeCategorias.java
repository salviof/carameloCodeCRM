package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.DocsEquipeDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivoequipe.CPCategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.DOCEQUIPECATEGORIAS)
public class ValorLogicoPessoaDocEquipeCategorias
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaDocEquipeCategorias(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    boolean valoreDefinidos = false;

    @Override
    public synchronized Object getValor(Object... pEntidade) {

        if (!valoreDefinidos) {
            if (getPessoa().getDocEquipeCategorias() == null || getPessoa().getDocEquipeCategorias().isEmpty()) {
                getPessoa().setDocEquipeCategorias(new ArrayList());
                EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                try {
                    List<CategoriaArquivoEquipe> categorias = new ConsultaDinamicaDeEntidade(CategoriaArquivoEquipe.class, em).addCondicaoManyToOneNulo(CPCategoriaArquivoEquipe.pastapai).resultadoRegistros();

                    for (CategoriaArquivoEquipe cat : categorias) {
                        ConsultaDinamicaDeEntidade consulta = UtilSBPersistencia.gerarConsultaDeEntidade(ArquivoAnexado.class, em);
                        consulta.addCondicaoManyToOneIgualA("prospecto", getPessoa());
                        consulta.addCondicaoManyToOneIgualA("categoriaArqEquipe", cat);

                        List<ArquivoAnexado> arquivos = consulta.resultadoRegistros();
                        DocsEquipeDaCategoria novoDocCat = new DocsEquipeDaCategoria();
                        novoDocCat.setArquivoAnexado(arquivos);
                        novoDocCat.setQuantidade(arquivos.size());
                        novoDocCat.setId((long) String.valueOf(getPessoa().getId()).concat(cat.getNome()).hashCode());
                        novoDocCat.setNome(cat.getNome());
                        novoDocCat.setCategoria(cat);
                        novoDocCat.setIcone(cat.getIcone());
                        getPessoa().getDocEquipeCategorias().add(novoDocCat);
                    }

                } finally {
                    UtilSBPersistencia.fecharEM(em);
                    valoreDefinidos = true;
                }
            }
        }
        return getPessoa().getDocEquipeCategorias();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
