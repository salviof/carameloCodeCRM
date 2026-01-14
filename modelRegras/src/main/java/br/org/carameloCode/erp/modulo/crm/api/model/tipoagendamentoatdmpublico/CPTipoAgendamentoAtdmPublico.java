package br.org.carameloCode.erp.modulo.crm.api.model.tipoagendamentoatdmpublico;

import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoAgendamentoAtdmPublico.class)
public enum CPTipoAgendamentoAtdmPublico {
	_ID, _NOME, _DESCRICAO, _ICONE, _IMAGEMPEQUENA, _IMAGEMMEDIA, _IMAGEMGRANDE, _MINUTOSPROGRAMADOSATENDIMENTO, _MINUTOSANTERIORARESERVA, _ANTECEDENCIANOVARESERVAMINUTOS, _FATOROCUPACAO, _CONTEXTORESERVA, _TIPOATENDIMENTOPUBLICO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String icone = "icone";
	public static final String imagempequena = "imagemPequena";
	public static final String imagemmedia = "imagemMedia";
	public static final String imagemgrande = "imagemGrande";
	public static final String minutosprogramadosatendimento = "minutosProgramadosAtendimento";
	public static final String minutosanteriorareserva = "minutosAnteriorAReserva";
	public static final String antecedencianovareservaminutos = "antecedenciaNovaReservaMinutos";
	public static final String fatorocupacao = "fatorOcupacao";
	public static final String contextoreserva = "contextoReserva";
	public static final String tipoatendimentopublico = "tipoAtendimentoPublico";
}