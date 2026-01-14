package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailIconeAlerta;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.ICONEALERTA)
public class ValorLogicoEnvioEmailAtividadeIconeAlerta
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoEnvioEmailIconeAlerta logicapadrao;

    public ValorLogicoEnvioEmailAtividadeIconeAlerta(ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicapadrao = new ValorLogicoEnvioEmailIconeAlerta(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return logicapadrao.getValor(pEntidade);
    }

}
