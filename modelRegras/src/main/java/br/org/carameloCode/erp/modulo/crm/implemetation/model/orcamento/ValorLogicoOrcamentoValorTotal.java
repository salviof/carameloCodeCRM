package br.org.carameloCode.erp.modulo.crm.implemetation.model.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValorLogicoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValoresLogicosOrcamento;

@ValorLogicoOrcamento(calculo = ValoresLogicosOrcamento.VALORTOTAL)
public class ValorLogicoOrcamentoValorTotal extends ValorLogicoCalculoGenerico {

    public ValorLogicoOrcamentoValorTotal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getOrcamento().getServicoOferecido().isEmpty()) {
            getOrcamento().setValorTotal(0);
        } else {
            double valorTotal = getOrcamento().getServicoOferecido().stream().mapToDouble(srv -> srv.getValorSetup()).sum();
            getOrcamento().setValorTotal(valorTotal);
        }

        return getOrcamento().getValorTotal();
    }

    public Orcamento getOrcamento() {
        return (Orcamento) getCampoInst().getObjetoDoAtributo();
    }
}
