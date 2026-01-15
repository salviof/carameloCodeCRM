package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipochatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.TipoChatBot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochatbot.ValorLogicoTipoChatBot;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochatbot.ValoresLogicosTipoChatBot;

@ValorLogicoTipoChatBot(calculo = ValoresLogicosTipoChatBot.LINK)
public class ValorLogicoTipoChatBotLink extends ValorLogicoCalculoGenerico {

    public ValorLogicoTipoChatBotLink(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getTipoChatBot();
    }

    public TipoChatBot getTipoChatBot() {
        return (TipoChatBot) getCampoInst().getObjetoDoAtributo();
    }

}
