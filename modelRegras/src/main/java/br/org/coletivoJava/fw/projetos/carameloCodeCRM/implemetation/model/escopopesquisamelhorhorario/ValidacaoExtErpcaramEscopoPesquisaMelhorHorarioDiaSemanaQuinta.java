package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.escopopesquisamelhorhorario;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario.ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaQuinta;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario.ValidadorEscopoPesquisaMelhorHorario;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario.ValidadoresEscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorEscopoPesquisaMelhorHorario(validador = ValidadoresEscopoPesquisaMelhorHorario.DIASEMANAQUINTA)
public class ValidacaoExtErpcaramEscopoPesquisaMelhorHorarioDiaSemanaQuinta
		extends
			ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaQuinta {

	public ValidacaoExtErpcaramEscopoPesquisaMelhorHorarioDiaSemanaQuinta(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		return super.validar(o);
	}

	public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
		return getObjetoDoAtributo();
	}
}