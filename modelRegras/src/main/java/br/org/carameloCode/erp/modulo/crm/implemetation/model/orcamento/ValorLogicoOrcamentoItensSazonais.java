package br.org.carameloCode.erp.modulo.crm.implemetation.model.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.ServicoOferecido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValorLogicoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValoresLogicosOrcamento;

@ValorLogicoOrcamento(calculo = ValoresLogicosOrcamento.ITENSSAZONAIS)
public class ValorLogicoOrcamentoItensSazonais
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoOrcamentoItensSazonais(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        List<ServicoOferecido> servicosSazonais = new ArrayList<>();
        getOrcamento().getServicoOferecido().stream().filter(it -> it.getValorSetup() > 0).forEach(servicosSazonais::add);
        getOrcamento().setItensSazonais(servicosSazonais);

        return getOrcamento().getItensSazonais();
    }

    public Orcamento getOrcamento() {
        return (Orcamento) getCampoInst().getObjetoDoAtributo();
    }

}
