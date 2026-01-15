package br.org.carameloCode.erp.modulo.crm.implemetation.model.historicorelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.historicorelacionamento.ValorLogicoHistoricoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.historicorelacionamento.ValoresLogicosHistoricoRelacionamento;

@ValorLogicoHistoricoRelacionamento(calculo = ValoresLogicosHistoricoRelacionamento.DESCRICAO)
public class ValorLogicoHistoricoRelacionamentoDescricao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoHistoricoRelacionamentoDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getHistorico().getId() == null) {
            return "Novo Histórico";
        } else {

            return "Mudança de relacionamento para " + getHistorico().getProspecto().getNome();

        }
    }

    public HistoricoRelacionamento getHistorico() {
        return (HistoricoRelacionamento) getCampoInst().getObjetoDoAtributo();
    }
}
