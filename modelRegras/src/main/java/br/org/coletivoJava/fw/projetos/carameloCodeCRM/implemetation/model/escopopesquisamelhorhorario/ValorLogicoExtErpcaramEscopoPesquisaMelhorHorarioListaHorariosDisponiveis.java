package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.escopopesquisamelhorhorario;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorarioListaHorariosDisponiveis;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.LISTAHORARIOSDISPONIVEIS)
public class ValorLogicoExtErpcaramEscopoPesquisaMelhorHorarioListaHorariosDisponiveis
		extends
			ValorLogicoEscopoPesquisaMelhorHorarioListaHorariosDisponiveis {

	public ValorLogicoExtErpcaramEscopoPesquisaMelhorHorarioListaHorariosDisponiveis(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}