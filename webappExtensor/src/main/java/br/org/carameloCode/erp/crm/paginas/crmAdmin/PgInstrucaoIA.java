package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.ia.acoes.FabAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.acoes.InfoAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
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
@InfoPagina(nomeCurto = "gdinia", tags = {"gestao de instrucoes ia"})
@InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.INSTRUCAO_IA_MB_GESTAO)
public class PgInstrucaoIA extends MB_paginaCadastroEntidades<InstrucaoIA> {

}
