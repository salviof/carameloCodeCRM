package br.org.carameloCode.erp.modulo.crm.api.model.tiposervico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoServico.class)
public enum CPTipoServico {
	_ID, _NOME, _DESCRICAO, _TIPOCOBRANCA, _DESCRICAOAPRESENTACAO, _URLDETALHES, _TEMINTEGRACAOFATURA, _CODIGOITENGRACAOFATURA, _GERAPGTORECORRENTE, _GERAPGTOSAZONAL, _FOIDEFINIDOTIPOPGTO, _APRESENTACOES, _ARQUIVOAPRESENTACAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String tipocobranca = "tipoCobranca";
	public static final String descricaoapresentacao = "descricaoApresentacao";
	public static final String urldetalhes = "urlDetalhes";
	public static final String temintegracaofatura = "temIntegracaoFatura";
	public static final String codigoitengracaofatura = "codigoItengracaoFatura";
	public static final String gerapgtorecorrente = "geraPgtoRecorrente";
	public static final String gerapgtosazonal = "geraPgtoSazonal";
	public static final String foidefinidotipopgto = "foiDefinidoTipoPgto";
	public static final String apresentacoes = "apresentacoes";
	public static final String arquivoapresentacao = "arquivoApresentacao";
}