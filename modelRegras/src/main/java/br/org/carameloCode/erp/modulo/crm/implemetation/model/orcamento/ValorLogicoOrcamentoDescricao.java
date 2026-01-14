package br.org.carameloCode.erp.modulo.crm.implemetation.model.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValorLogicoOrcamento;
import br.org.carameloCode.erp.modulo.crm.api.model.orcamento.ValoresLogicosOrcamento;

@ValorLogicoOrcamento(calculo = ValoresLogicosOrcamento.DESCRICAO)
public class ValorLogicoOrcamentoDescricao extends ValorLogicoCalculoGenerico {

    public ValorLogicoOrcamentoDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getOrcamento().getDataHoraCriacao() != null) {
            String data = UtilCRCDataHora.getDataHoraString(getOrcamento().getDataHoraCriacao(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO);
            String valor = "Orçamento criado em " + data + " com " + getOrcamento().getServicoOferecido().size() + " itens";
            getOrcamento().setDescricao(valor);
        }
        return getOrcamento().getDescricao();
    }

    public Orcamento getOrcamento() {
        return (Orcamento) getCampoInst().getObjetoDoAtributo();
    }

}
