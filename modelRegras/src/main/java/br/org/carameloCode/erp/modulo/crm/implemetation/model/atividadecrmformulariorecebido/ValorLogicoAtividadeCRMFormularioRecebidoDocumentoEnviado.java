package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrmformulariorecebido;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmformulariorecebido.ValorLogicoAtividadeCRMFormularioRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrmformulariorecebido.ValoresLogicosAtividadeCRMFormularioRecebido;

@ValorLogicoAtividadeCRMFormularioRecebido(calculo = ValoresLogicosAtividadeCRMFormularioRecebido.DOCUMENTOENVIADO)
public class ValorLogicoAtividadeCRMFormularioRecebidoDocumentoEnviado
		extends
			ValorLogicoCalculoGenerico {

	ValorLogicoAtividadeCRMFormularioRecebidoDocumentoEnviado(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}
}