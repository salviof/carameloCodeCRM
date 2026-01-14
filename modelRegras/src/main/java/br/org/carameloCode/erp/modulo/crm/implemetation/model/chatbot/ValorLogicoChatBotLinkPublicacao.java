package br.org.carameloCode.erp.modulo.crm.implemetation.model.chatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.ChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringUrl;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chatbot.ValorLogicoChatBot;
import br.org.carameloCode.erp.modulo.crm.api.model.chatbot.ValoresLogicosChatBot;

@ValorLogicoChatBot(calculo = ValoresLogicosChatBot.LINKPUBLICACAO)
public class ValorLogicoChatBotLinkPublicacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoChatBotLinkPublicacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (UtilCRCStringValidador.isNuloOuEmbranco(getChatBot().getLinkPublicacao())) {

            StringBuilder link = new StringBuilder();
            link.append(getChatBot().getTipo().getLink());
            link.append("?NOME=");
            link.append(UtilCRCStringUrl.gerarUrlCodificada(getChatBot().getAtividade().getProspectoEmpresa().getContatoPrincipal().getNome()));
            link.append("&EMPRESA=");
            link.append(UtilCRCStringUrl.gerarUrlCodificada(getChatBot().getAtividade().getProspectoEmpresa().getNome()));
            link.append("&SUCESSO=");
            link.append(UtilCRCStringUrl.gerarUrlCodificada(getChatBot().getAtividade().getProspectoEmpresa().getUsuarioAtendimento().getNome()));
            link.append("&REPRESENTANTE=");
            link.append(UtilCRCStringUrl.gerarUrlCodificada(getChatBot().getAtividade().getProspectoEmpresa().getUsuarioResponsavel().getNome()));

            for (TipoDadoCRM tipoDAdo : getChatBot().getTipo().getDadosExtras()) {
                DadoCRM dado = FabDadoCRM.getDadoNovoSeNaoExistir(getChatBot().getAtividade(), tipoDAdo);
                link.append("&").append(dado.getTipoDadoCRM().getNome()).append("=");
                link.append(UtilCRCStringUrl.gerarUrlCodificada(dado.getCampoInstanciado().getValorTextoFormatado()));
            }
            getChatBot().setLinkPublicacao(link.toString());

        }

        return getChatBot().getLinkPublicacao();
    }

    public ChatBot getChatBot() {
        return (ChatBot) getCampoInst().getObjetoDoAtributo();
    }
}
