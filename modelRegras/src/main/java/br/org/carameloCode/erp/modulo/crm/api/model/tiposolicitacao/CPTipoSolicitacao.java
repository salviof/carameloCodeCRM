package br.org.carameloCode.erp.modulo.crm.api.model.tiposolicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.TipoSolicitacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoSolicitacao.class)
public enum CPTipoSolicitacao {
	_ID, _NOME, _DESCRICAO, _HORASESPERAENTREGA, _ICONE;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String horasesperaentrega = "horasEsperaEntrega";
	public static final String icone = "icone";
}