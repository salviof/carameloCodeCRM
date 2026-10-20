package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.execucaoia;

import br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia.ValorLogicoExecucaoIAEntidadeVinculada;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoia.ValorLogicoExecucaoIA;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.execucaoia.ValoresLogicosExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIA(calculo = ValoresLogicosExecucaoIA.ENTIDADEVINCULADA)
public class ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada
		extends
			ValorLogicoExecucaoIAEntidadeVinculada {

	public ValorLogicoExtErpcaramExecucaoIAEntidadeVinculada(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}