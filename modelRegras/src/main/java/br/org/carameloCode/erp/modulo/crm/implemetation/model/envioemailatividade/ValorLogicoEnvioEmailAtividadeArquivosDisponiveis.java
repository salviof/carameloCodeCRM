package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail.ValorLogicoEnvioEmailArquivosDisponiveis;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.ARQUIVOSDISPONIVEIS)
public class ValorLogicoEnvioEmailAtividadeArquivosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAtividadeArquivosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
        calculoPadrao = new ValorLogicoEnvioEmailArquivosDisponiveis(getCampoInst());
    }
    private final ValorLogicoEnvioEmailArquivosDisponiveis calculoPadrao;

    @Override
    public Object getValor(Object... pEntidade) {
        return calculoPadrao.getValor(pEntidade);
    }

}
