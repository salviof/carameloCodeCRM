package br.org.carameloCode.erp.modulo.crm.implemetation.model.chatbot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.ChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chatbot.ValorLogicoChatBot;
import br.org.carameloCode.erp.modulo.crm.api.model.chatbot.ValoresLogicosChatBot;

@ValorLogicoChatBot(calculo = ValoresLogicosChatBot.CODIGOACESSO)
public class ValorLogicoChatBotCodigoAcesso extends ValorLogicoCalculoGenerico {

    public ValorLogicoChatBotCodigoAcesso(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valordefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valordefinido) {
            ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.FORM_CHAT_INTERATIVO_FRM_INTERACAO, getChatBot().getAtividade(), ((ContatoProspecto) getChatBot().getAtividade().getProspectoEmpresa().getCPinst("contatoPrincipal").getValor()).getEmail());
            getChatBot().setCodigoAcesso(token.getCodigo());
            valordefinido = true;
        }

        return getChatBot().getCodigoAcesso();
    }

    public ChatBot getChatBot() {
        return (ChatBot) getCampoInst().getObjetoDoAtributo();
    }

}
