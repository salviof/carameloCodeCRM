package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.escopopesqhorariopublicado;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicadoLinkDeAcesso;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.LINKDEACESSO)
public class ValorLogicoExtErpcaramEscopoPesqHorarioPublicadoLinkDeAcesso
		extends
			ValorLogicoEscopoPesqHorarioPublicadoLinkDeAcesso {

	public ValorLogicoExtErpcaramEscopoPesqHorarioPublicadoLinkDeAcesso(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}