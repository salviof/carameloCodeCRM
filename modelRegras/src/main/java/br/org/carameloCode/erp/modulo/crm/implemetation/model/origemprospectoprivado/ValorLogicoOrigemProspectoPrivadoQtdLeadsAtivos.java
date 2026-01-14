package br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospectoprivado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValorLogicoOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado.ValoresLogicosOrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.origemprospecto.ValorLogicoOrigemProspectoQtdLeadsAtivos;

@ValorLogicoOrigemProspectoPrivado(calculo = ValoresLogicosOrigemProspectoPrivado.QTDLEADSATIVOS)
public class ValorLogicoOrigemProspectoPrivadoQtdLeadsAtivos
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoOrigemProspectoQtdLeadsAtivos valorLogico;

    public ValorLogicoOrigemProspectoPrivadoQtdLeadsAtivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
        valorLogico = new ValorLogicoOrigemProspectoQtdLeadsAtivos(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return valorLogico.getValor(pEntidade);
    }

}
