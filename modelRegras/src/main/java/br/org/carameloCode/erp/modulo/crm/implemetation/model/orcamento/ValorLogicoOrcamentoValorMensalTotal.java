package br.org.carameloCode.erp.modulo.crm.implemetation.model.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValorLogicoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValoresLogicosOrcamento;

@ValorLogicoOrcamento(calculo = ValoresLogicosOrcamento.VALORMENSALTOTAL)
public class ValorLogicoOrcamentoValorMensalTotal
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoOrcamentoValorMensalTotal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getOrcamento().getServicoOferecido().isEmpty()) {
            getOrcamento().setValorMensalTotal(0);
        } else {
            double valorTotal = getOrcamento().getServicoOferecido().stream().mapToDouble(srv -> srv.getValorMensal()).sum();
            getOrcamento().setValorMensalTotal(valorTotal);
        }

        return getOrcamento().getValorMensalTotal();
    }

    public Orcamento getOrcamento() {
        return (Orcamento) getCampoInst().getObjetoDoAtributo();
    }
}
