package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.disponibilidadeatdmtpublico;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSabado;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublico;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValoresLogicosDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoDisponibilidadeAtdmtPublico(calculo = ValoresLogicosDisponibilidadeAtdmtPublico.DIASEMANASABADO)
public class ValorLogicoExtErpcaramDisponibilidadeAtdmtPublicoDiaSemanaSabado
		extends
			ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSabado {

	public ValorLogicoExtErpcaramDisponibilidadeAtdmtPublicoDiaSemanaSabado(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}