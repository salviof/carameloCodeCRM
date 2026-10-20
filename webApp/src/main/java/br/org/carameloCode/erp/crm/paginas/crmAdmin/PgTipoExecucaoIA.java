package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.ia.acoes.FabAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.acoes.InfoAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.TipoExecucaoIA;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "gdteia", tags = {"gestao de tipos de execucao ia"})
@InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.TIPO_EXECUCAO_IA_MB_GESTAO)
public class PgTipoExecucaoIA extends MB_paginaCadastroEntidades<TipoExecucaoIA> {

}
