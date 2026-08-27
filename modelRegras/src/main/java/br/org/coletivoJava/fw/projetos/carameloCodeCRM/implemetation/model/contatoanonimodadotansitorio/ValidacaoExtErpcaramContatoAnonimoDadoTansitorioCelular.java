package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.contatoanonimodadotansitorio;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contatoanonimodadotansitorio.ValidacaoContatoAnonimoDadoTansitorioCelular;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValidadorContatoAnonimoDadoTansitorio;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValidadoresContatoAnonimoDadoTansitorio;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorContatoAnonimoDadoTansitorio(validador = ValidadoresContatoAnonimoDadoTansitorio.CELULAR)
public class ValidacaoExtErpcaramContatoAnonimoDadoTansitorioCelular
		extends
			ValidacaoContatoAnonimoDadoTansitorioCelular {

	public ValidacaoExtErpcaramContatoAnonimoDadoTansitorioCelular(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		return super.validar(o);
	}

	public ContatoAnonimoDadoTansitorio getContatoAnonimoDadoTansitorio() {
		return getObjetoDoAtributo();
	}
}