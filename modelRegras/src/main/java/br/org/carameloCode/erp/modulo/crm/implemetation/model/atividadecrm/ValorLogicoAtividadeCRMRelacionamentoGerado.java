package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.RELACIONAMENTOGERADO)
public class ValorLogicoAtividadeCRMRelacionamentoGerado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMRelacionamentoGerado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getAtividade().getTipoAtividade() == null) {
            getAtividade().setRelacionamentoGerado(null);
        }
        if (getAtividade().getTipoAtividade().isResultaEmRelacionamentoAnterior()) {
            if (getAtividade().getProspectoEmpresa() == null) {
                getAtividade().setRelacionamentoGerado(getAtividade().getTipoAtividade().getRelacionamentoGerado());
            } else {
                TipoRelacionamento tipo = (TipoRelacionamento) getAtividade().getProspectoEmpresa().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.relacionamentoanterior).getValor();
                getAtividade().setRelacionamentoGerado(tipo);
            }
        } else {
            getAtividade().setRelacionamentoGerado(getAtividade().getTipoAtividade().getRelacionamentoGerado());
        }

        return getAtividade().getRelacionamentoGerado();
    }

    public AtividadeCRM getAtividade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
