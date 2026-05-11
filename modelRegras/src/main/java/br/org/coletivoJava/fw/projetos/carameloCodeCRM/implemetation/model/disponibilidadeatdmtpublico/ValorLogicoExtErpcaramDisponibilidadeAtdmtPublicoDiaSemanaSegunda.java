package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.disponibilidadeatdmtpublico;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSegunda;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublico;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.disponibilidadeatdmtpublico.ValoresLogicosDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoDisponibilidadeAtdmtPublico(calculo = ValoresLogicosDisponibilidadeAtdmtPublico.DIASEMANASEGUNDA)
public class ValorLogicoExtErpcaramDisponibilidadeAtdmtPublicoDiaSemanaSegunda
		extends
			ValorLogicoDisponibilidadeAtdmtPublicoDiaSemanaSegunda {

	public ValorLogicoExtErpcaramDisponibilidadeAtdmtPublicoDiaSemanaSegunda(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}