/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 * @author salvio
 */
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_MB_GESTAO)
@InfoPagina(nomeCurto = "CATDC", tags = {"Categorias de documentos do cliente"})
@Named
@ViewScoped
public class PgCategoriaArquivoCliente extends MB_paginaCadastroEntidades<CategoriaArquivoCliente> {

    @InfoParametroURL(tipoEntidade = CategoriaArquivoCliente.class, nome = "Categoria selecionada", tipoParametro = TIPO_PARTE_URL.ENTIDADE)
    private ParametroURL prParametroSelecionado;

    private CategoriaArquivoCliente categoriaPai;

    @Override
    public CategoriaArquivoCliente getEntidadeSelecionada() {
        CategoriaArquivoCliente cat = super.getEntidadeSelecionada();
        if (cat != null) {
            if (cat.getId() == null) {
                if (getParametroInstanciado(prParametroSelecionado).isValorDoParametroFoiConfigurado()) {
                    cat.setPastaPai((CategoriaArquivoCliente) getParametroInstanciado(prParametroSelecionado).getValor());
                }
            }

        }
        return cat;
    }

    public CategoriaArquivoCliente getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(CategoriaArquivoCliente categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public void novaSubPasta() {
        if (getCategoriaPai() == null) {
            SBCore.enviarAvisoAoUsuario("Selecione uma pasta pai");
        } else {

            String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_CLIENTE_FRM_NOVO_SUB_DIRETORIO, getEntidadeSelecionada());
            UtilSBWP_JSFTools.vaParaPagina(url);

        }
    }
}
