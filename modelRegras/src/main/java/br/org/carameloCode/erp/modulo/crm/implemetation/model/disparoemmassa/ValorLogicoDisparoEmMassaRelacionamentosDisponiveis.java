package br.org.carameloCode.erp.modulo.crm.implemetation.model.disparoemmassa;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa.ValorLogicoDisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa.ValoresLogicosDisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.DisparoEmMassa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoDisparoEmMassa(calculo = ValoresLogicosDisparoEmMassa.RELACIONAMENTOSDISPONIVEIS)
public class ValorLogicoDisparoEmMassaRelacionamentosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDisparoEmMassaRelacionamentosDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getDisparoEmMassa().getMetaRelacionamento() != null) {
            getDisparoEmMassa().setRelacionamentosDisponiveis(getDisparoEmMassa().getRelacionamentosDisponiveis());
        }

        return getDisparoEmMassa().getRelacionamentosDisponiveis();

    }

    public DisparoEmMassa getDisparoEmMassa() {
        return (DisparoEmMassa) getCampoInst().getObjetoRaizDoAtributo();
    }
}
