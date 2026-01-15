/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.CATEGORIA_DOCUMENTO_EQUIPE_MB_GESTAO)
@InfoPagina(nomeCurto = "CATDEQ", tags = {"Categorias de documentos da equipe"})
@Named
@ViewScoped
public class PgCategoriaArquivosEquipe extends MB_paginaCadastroEntidades<CategoriaArquivoEquipe> {

}
