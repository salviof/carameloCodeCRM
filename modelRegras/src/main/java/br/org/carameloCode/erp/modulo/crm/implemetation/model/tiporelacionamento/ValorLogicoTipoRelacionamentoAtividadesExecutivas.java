package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.CPTipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValorLogicoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValoresLogicosTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.ArrayList;
import java.util.List;

@ValorLogicoTipoRelacionamento(calculo = ValoresLogicosTipoRelacionamento.ATIVIDADESEXECUTIVAS)
public class ValorLogicoTipoRelacionamentoAtividadesExecutivas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoRelacionamentoAtividadesExecutivas(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    boolean valorDefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!valorDefinido) {
            try {
                List<TipoAtividadeCRM> tipos = new ArrayList<>();
                List<TipoAtividadeCRM> todosTipos = (List<TipoAtividadeCRM>) getTipoRelacionamento().getCPinst(CPTipoRelacionamento.todasatividadesdisponiveis).getValor();

                //#{.relacionamentoGerado ne null
                //and cc.attrs.tipoAtividade.relacionamentoGerado ne cc.attrs.empresa.relacionamento)}
                todosTipos.stream().filter(at -> at.getRelacionamentoGerado() == null)
                        .forEach(tipos::add);
                getTipoRelacionamento().setAtividadesExecutivas(tipos);
            } finally {
                valorDefinido = true;
            }
        }

        return getTipoRelacionamento().getAtividadesExecutivas();

    }

    public TipoRelacionamento getTipoRelacionamento() {
        return (TipoRelacionamento) getCampoInst().getObjetoRaizDoAtributo();
    }
}
