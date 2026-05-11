package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.escopopesqhorariopublicado;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicadoAtivo;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.ATIVO)
public class ValorLogicoExtErpcaramEscopoPesqHorarioPublicadoAtivo
		extends
			ValorLogicoEscopoPesqHorarioPublicadoAtivo {

	public ValorLogicoExtErpcaramEscopoPesqHorarioPublicadoAtivo(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public Object getValor(Object... pEntidade) {
		return super.getValor(pEntidade);
	}
}