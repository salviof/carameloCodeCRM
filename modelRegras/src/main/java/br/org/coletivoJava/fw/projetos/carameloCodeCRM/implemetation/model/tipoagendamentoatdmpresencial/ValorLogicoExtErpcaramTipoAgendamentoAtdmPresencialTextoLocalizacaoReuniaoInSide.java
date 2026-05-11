package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tipoagendamentoatdmpresencial;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.tipoagendamentoatdmpresencial.ValorLogicoTipoAgendamentoAtdmPresencialTextoLocalizacaoReuniaoInSide;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tipoagendamentoatdmpresencial.ValorLogicoTipoAgendamentoAtdmPresencial;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tipoagendamentoatdmpresencial.ValoresLogicosTipoAgendamentoAtdmPresencial;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoAgendamentoAtdmPresencial(calculo = ValoresLogicosTipoAgendamentoAtdmPresencial.TEXTOLOCALIZACAOREUNIAOINSIDE)
public class ValorLogicoExtErpcaramTipoAgendamentoAtdmPresencialTextoLocalizacaoReuniaoInSide
		extends
			ValorLogicoTipoAgendamentoAtdmPresencialTextoLocalizacaoReuniaoInSide {

	public ValorLogicoExtErpcaramTipoAgendamentoAtdmPresencialTextoLocalizacaoReuniaoInSide(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}