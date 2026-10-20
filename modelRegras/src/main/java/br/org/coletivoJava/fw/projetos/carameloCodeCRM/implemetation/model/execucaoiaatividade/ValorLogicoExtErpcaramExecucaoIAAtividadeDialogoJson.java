package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoiaatividade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValorLogicoExecucaoIAAtividade;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoiaatividade.ValoresLogicosExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIAAtividade(calculo = ValoresLogicosExecucaoIAAtividade.DIALOGOJSON)
public class ValorLogicoExtErpcaramExecucaoIAAtividadeDialogoJson
		extends
			ValorLogicoCalculoGenerico {

	public ValorLogicoExtErpcaramExecucaoIAAtividadeDialogoJson(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}