package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmformulariorecebido;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmformulariorecebido.ValorLogicoAtividadeCRMFormularioRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmformulariorecebido.ValoresLogicosAtividadeCRMFormularioRecebido;

@ValorLogicoAtividadeCRMFormularioRecebido(calculo = ValoresLogicosAtividadeCRMFormularioRecebido.CHATBOT)
public class ValorLogicoAtividadeCRMFormularioRecebidoChatBot
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoAtividadeCRMFormularioRecebidoChatBot(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}