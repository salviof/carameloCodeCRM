package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tipoagendamentoatdmpresencial;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAgendamentoAtdmPresencial.class)
public enum CPTipoAgendamentoAtdmPresencial {
	_LOCALPADRAOREUNIAO, _TEXTOLOCALIZACAOREUNIAOINSIDE;

	public static final String localpadraoreuniao = "localPadraoReuniao";
	public static final String textolocalizacaoreuniaoinside = "textoLocalizacaoReuniaoInSide";
}