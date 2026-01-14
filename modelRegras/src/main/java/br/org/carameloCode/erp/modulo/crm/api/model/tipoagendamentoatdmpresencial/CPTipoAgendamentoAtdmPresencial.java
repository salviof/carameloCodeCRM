package br.org.carameloCode.erp.modulo.crm.api.model.tipoagendamentoatdmpresencial;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPresencial;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAgendamentoAtdmPresencial.class)
public enum CPTipoAgendamentoAtdmPresencial {
	_LOCALPADRAOREUNIAO, _TEXTOLOCALIZACAOREUNIAOINSIDE;

	public static final String localpadraoreuniao = "localPadraoReuniao";
	public static final String textolocalizacaoreuniaoinside = "textoLocalizacaoReuniaoInSide";
}