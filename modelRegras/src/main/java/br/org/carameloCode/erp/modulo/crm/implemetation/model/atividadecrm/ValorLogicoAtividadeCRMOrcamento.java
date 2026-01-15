package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.ORCAMENTO)
public class ValorLogicoAtividadeCRMOrcamento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMOrcamento(ItfCampoInstanciado pCampo) {
        super(pCampo);

    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getAtividade().getOrcamento() == null) {
            getAtividade().setOrcamento((Orcamento) getAtividade().getProspectoEmpresa().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.ultimoorcamento).getValor());
        }
        return getAtividade().getOrcamento();
    }

    public AtividadeCRM getAtividade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
