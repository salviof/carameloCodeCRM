package br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospectoprivado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValorLogicoOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValoresLogicosOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospecto.ValorLogicoOrigemProspectoQtdLeadsInativos;

@ValorLogicoOrigemProspectoPrivado(calculo = ValoresLogicosOrigemProspectoPrivado.QTDLEADSINATIVOS)
public class ValorLogicoOrigemProspectoPrivadoQtdLeadsInativos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoOrigemProspectoPrivadoQtdLeadsInativos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogico = new ValorLogicoOrigemProspectoQtdLeadsInativos(pCampo);
    }

    private final ValorLogicoOrigemProspectoQtdLeadsInativos valorLogico;

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogico.getValor(pEntidade);
    }

}
