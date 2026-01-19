package br.org.carameloCode.erp.modulo.crm.api.model.telefone;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.telefone.Telefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Telefone.class)
public enum CPTelefone {
	_ID, _NOME, _TELEFONE, _CODIGOAPIWHATSAPP, _TIPOCHAMADARECEBIDA, _TIPOCHAMADAREALIZADA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String telefone = "telefone";
	public static final String codigoapiwhatsapp = "codigoApiWhatsapp";
	public static final String tipochamadarecebida = "tipoChamadaRecebida";
	public static final String tipochamadarealizada = "tipoChamadaRealizada";
}