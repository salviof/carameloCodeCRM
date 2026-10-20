package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.instrucaoia;

import br.org.carameloCode.erp.modulo.ia.implemetation.model.instrucaoia.ValorLogicoInstrucaoIAConteudo;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.instrucaoia.ValorLogicoInstrucaoIA;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.instrucaoia.ValoresLogicosInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoInstrucaoIA(calculo = ValoresLogicosInstrucaoIA.CONTEUDO)
public class ValorLogicoExtErpcaramInstrucaoIAConteudo
		extends
			ValorLogicoInstrucaoIAConteudo {

	public ValorLogicoExtErpcaramInstrucaoIAConteudo(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}