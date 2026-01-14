/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.api.model.subpastacliente.CPSubpastaCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubpastaCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@InfoPagina(nomeCurto = "pastasDeDocumentos", tags = {"Pastas do diretório"})
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.SUBPASTA_MB_GESTAO)
public class PgPastaDeDocumento extends MB_paginaCadastroEntidades<SubPastaPrivada> {

    @InfoParametroURL(nome = "CatEquipe", tipoEntidade = CategoriaArquivoEquipe.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false,
            representaEntidadePrincipalMB = false)
    private ParametroURL prCategoriaEquipe;

    @InfoParametroURL(nome = "CatCliente", tipoEntidade = CategoriaArquivoCliente.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false,
            representaEntidadePrincipalMB = false)
    private ParametroURL prCategoriaCliente;

    @InfoParametroURL(nome = "subPastaPaiEquipe", tipoEntidade = SubPastaEquipe.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false,
            representaEntidadePrincipalMB = false)
    private ParametroURL prPastaPaiEquipe;

    @InfoParametroURL(nome = "subPastaPai", tipoEntidade = SubpastaCliente.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false,
            representaEntidadePrincipalMB = false)
    private ParametroURL prPastaPaiCliente;

    @InfoParametroURL(nome = "pessoa", tipoEntidade = Pessoa.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false,
            representaEntidadePrincipalMB = false)
    private ParametroURL prPessoa;

    private SubPastaPrivada subPastaPrivadaPai;

    private CategoriaArquivoCliente categoriaCliente;

    private CategoriaArquivoEquipe categoriaEquipe;

    private Pessoa pessoa;

    @PostConstruct
    public void inicio() {

        if (getParametroInstanciado(prPastaPaiEquipe).isValorDoParametroFoiConfigurado() || getParametroInstanciado(prPastaPaiCliente).isValorDoParametroFoiConfigurado()) {
            if (getParametroInstanciado(prPastaPaiEquipe).isValorDoParametroFoiConfigurado()) {
                subPastaPrivadaPai = UtilSBPersistencia.loadEntidade((SubPastaPrivada) getParametroInstanciado(prPastaPaiEquipe).getValor(), getEMPagina());
            }
            if (getParametroInstanciado(prPastaPaiCliente).isValorDoParametroFoiConfigurado()) {
                subPastaPrivadaPai = UtilSBPersistencia.loadEntidade((SubPastaPrivada) getParametroInstanciado(prPastaPaiCliente).getValor(), getEMPagina());
            }
            if (subPastaPrivadaPai instanceof SubpastaCliente) {
                categoriaCliente = UtilSBPersistencia.loadEntidade((CategoriaArquivoCliente) subPastaPrivadaPai.getCPinst(CPSubpastaCliente.diretorioproximo).getValor(), getEMPagina());

            } else {
                categoriaEquipe = UtilSBPersistencia.loadEntidade((CategoriaArquivoEquipe) subPastaPrivadaPai.getCPinst(CPSubpastaCliente.diretorioproximo).getValor(), getEMPagina());
            }

        } else {
            if (getParametroInstanciado(prCategoriaEquipe).isValorDoParametroFoiConfigurado()) {
                categoriaEquipe = (CategoriaArquivoEquipe) getParametroInstanciado(prCategoriaEquipe).getValor();
            }

            if (getParametroInstanciado(prCategoriaCliente).isValorDoParametroFoiConfigurado()) {
                categoriaCliente = (CategoriaArquivoCliente) getParametroInstanciado(prCategoriaEquipe).getValor();
            }
        }
    }

    public SubPastaPrivada getSubPastaPrivadaPai() {
        return subPastaPrivadaPai;
    }

    public CategoriaArquivoCliente getCategoriaCliente() {
        return categoriaCliente;
    }

    public CategoriaArquivoEquipe getCategoriaEquipe() {
        return categoriaEquipe;
    }

    @Override
    protected void autoexecEntidadeNova() {
        //super.autoexecEntidadeNova();
        if (getParametroInstanciado(prPastaPaiEquipe).isValorDoParametroFoiConfigurado() || getParametroInstanciado(prPastaPaiCliente).isValorDoParametroFoiConfigurado()) {

            if (getParametroInstanciado(prPastaPaiCliente).isValorDoParametroFoiConfigurado()) {
                subPastaPrivadaPai = (SubPastaPrivada) getParametroInstanciado(prPastaPaiCliente).getValor();
                setEntidadeSelecionada(new SubpastaCliente());
            }
            if (getParametroInstanciado(prPastaPaiEquipe).isValorDoParametroFoiConfigurado()) {
                subPastaPrivadaPai = (SubPastaPrivada) getParametroInstanciado(prPastaPaiEquipe).getValor();
                setEntidadeSelecionada(new SubPastaEquipe());
            }
            getEntidadeSelecionada().setPastaPai(subPastaPrivadaPai);
        } else {

            if (getParametroInstanciado(prCategoriaEquipe).isValorDoParametroFoiConfigurado()) {
                setEntidadeSelecionada(new SubPastaEquipe());
                categoriaEquipe = (CategoriaArquivoEquipe) getParametroInstanciado(prCategoriaEquipe).getValor();
                getEntidadeSelecionada().getComoSubPastaEquipe().setCategoriaEquipe(categoriaEquipe);
            }

            if (getParametroInstanciado(prCategoriaCliente).isValorDoParametroFoiConfigurado()) {
                setEntidadeSelecionada(new SubpastaCliente());
                categoriaCliente = (CategoriaArquivoCliente) getParametroInstanciado(prCategoriaCliente).getValor();
                getEntidadeSelecionada().getComoSubPastaCliente().setCategoriaCliente(categoriaCliente);
            }

        }
        pessoa = (Pessoa) getParametroInstanciado(prPessoa).getValor();
        if (getEntidadeSelecionada() != null) {
            getEntidadeSelecionada().setPessoa(pessoa);
        }

    }

}
