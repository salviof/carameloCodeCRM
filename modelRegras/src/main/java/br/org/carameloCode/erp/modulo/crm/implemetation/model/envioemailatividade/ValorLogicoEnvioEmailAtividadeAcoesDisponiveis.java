package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailAcoesDisponiveis;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.ACOESDISPONIVEIS)
public class ValorLogicoEnvioEmailAtividadeAcoesDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    private final ValorLogicoEnvioEmailAcoesDisponiveis logicapadrao;

    public ValorLogicoEnvioEmailAtividadeAcoesDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
        logicapadrao = new ValorLogicoEnvioEmailAcoesDisponiveis(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return logicapadrao.getValor(pEntidade);
    }

}
