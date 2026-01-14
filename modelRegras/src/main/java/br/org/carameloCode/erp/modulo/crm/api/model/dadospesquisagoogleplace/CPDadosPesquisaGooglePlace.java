package br.org.carameloCode.erp.modulo.crm.api.model.dadospesquisagoogleplace;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.transitorio.DadosPesquisaGooglePlace;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = DadosPesquisaGooglePlace.class)
public enum CPDadosPesquisaGooglePlace {
	_ID, _TERMO, _LOCAL, _PAGETOKEN, _ORIGEMSELECIONADA, _DADOSENCONTRADOS;

	public static final String id = "id";
	public static final String termo = "termo";
	public static final String local = "local";
	public static final String pagetoken = "pageToken";
	public static final String origemselecionada = "origemSelecionada";
	public static final String dadosencontrados = "dadosEncontrados";
}