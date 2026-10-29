package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiaatividade;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA.UtilCRMIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIAPlaceHolders;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValorLogicoExecucaoIAAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValoresLogicosExecucaoIAAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia.ValorLogicoExtErpcaramExecucaoIASystemPromptMD;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoExecucaoIAAtividade(calculo = ValoresLogicosExecucaoIAAtividade.SYSTEMPROMPTMD)
public class ValorLogicoExtErpcaramExecucaoIAAtividadeSystemPromptMD
        extends
        ValorLogicoExtErpcaramExecucaoIASystemPromptMD {

    @Override
    public InstrucaoIAPlaceHolders gerarPlaceHolder() {
        return null;
    }

    @Override
    public List<InstrucaoIA> getInstrucoesExecucao(EntityManager em) {
        List<InstrucaoIA> instrucoes = super.getInstrucoesExecucao(em);
        AtividadeCRM atividade = UtilSBPersistencia.loadEntidade(((ExecucaoIAAtividade) getExecucaoIA()).getAtividade(), em);
        if (atividade == null) {
            return instrucoes;
        }
        instrucoes.addAll(UtilCRMIA.getListaInstrucaoPessoaDadosBasicos(atividade.getProspectoEmpresa()));
        instrucoes.addAll(UtilCRMIA.getListaInstrucaoIAAtividadeCRM(atividade));

        return instrucoes;
    }

    public ValorLogicoExtErpcaramExecucaoIAAtividadeSystemPromptMD(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
