package br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SistemaERPConfiavel.class)
public enum CPSistemaERPConfiavel {
	_ID, _NOME, _DOMINIO, _TIPOCHAVE, _EMAILUSUARIOADMIN, _URLRECEPCAOCODIGO, _CHAVEPUBLICA, _URLPUBLICAENDPOINT, _FOIESTABELICIDACONEXAO, _HASHCHAVEPUBLICA, _COMOJSON;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String dominio = "dominio";
	public static final String tipochave = "tipoChave";
	public static final String emailusuarioadmin = "emailusuarioAdmin";
	public static final String urlrecepcaocodigo = "urlRecepcaoCodigo";
	public static final String chavepublica = "chavePublica";
	public static final String urlpublicaendpoint = "urlPublicaEndPoint";
	public static final String foiestabelicidaconexao = "foiEstabelicidaConexao";
	public static final String hashchavepublica = "hashChavePublica";
	public static final String comojson = "comoJson";
}