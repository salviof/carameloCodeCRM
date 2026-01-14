package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailFoiLidoPeloDestinatario;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.FOILIDOPELODESTINATARIO)
public class ValorLogicoEnvioEmailAtividadeFoiLidoPeloDestinatario
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoEnvioEmailFoiLidoPeloDestinatario logicaPadrao;

    public ValorLogicoEnvioEmailAtividadeFoiLidoPeloDestinatario(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicaPadrao = new ValorLogicoEnvioEmailFoiLidoPeloDestinatario(getCampoInst());
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return logicaPadrao.getValor(pEntidade);
    }

}
