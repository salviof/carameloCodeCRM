package br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.ParametroMensagemWtzap;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ParametroMensagemWtzap.class)
public enum CPParametroMensagemWtzap {
	_ID, _DESCRICAO, _CODIGOPARAMETRO, _CABECALHO, _TIPOPARAMETROWTZAP, _TIPOMENSAGEM, _TIPODADO, _DADORELATIVO;

	public static final String id = "id";
	public static final String descricao = "descricao";
	public static final String codigoparametro = "codigoParametro";
	public static final String cabecalho = "cabecalho";
	public static final String tipoparametrowtzap = "tipoParametroWtzap";
	public static final String tipomensagem = "tipoMensagem";
	public static final String tipodado = "tipoDado";
	public static final String dadorelativo = "dadoRelativo";
}