package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.contatoanonimodadotansitorio;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contatoanonimodadotansitorio.ValorLogicoContatoAnonimoDadoTansitorioSite;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValorLogicoContatoAnonimoDadoTansitorio;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValoresLogicosContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoContatoAnonimoDadoTansitorio(calculo = ValoresLogicosContatoAnonimoDadoTansitorio.SITE)
public class ValorLogicoExtErpcaramContatoAnonimoDadoTansitorioSite
		extends
			ValorLogicoContatoAnonimoDadoTansitorioSite {

	public ValorLogicoExtErpcaramContatoAnonimoDadoTansitorioSite(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}