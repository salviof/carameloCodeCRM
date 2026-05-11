package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.escopopesquisamelhorhorario;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorarioAtendentesPossiveis;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.ATENDENTESPOSSIVEIS)
public class ValorLogicoExtErpcaramEscopoPesquisaMelhorHorarioAtendentesPossiveis
		extends
			ValorLogicoEscopoPesquisaMelhorHorarioAtendentesPossiveis {

	public ValorLogicoExtErpcaramEscopoPesquisaMelhorHorarioAtendentesPossiveis(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}