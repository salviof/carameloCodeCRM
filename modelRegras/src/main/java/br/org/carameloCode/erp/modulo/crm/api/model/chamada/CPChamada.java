package br.org.carameloCode.erp.modulo.crm.api.model.chamada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.Chamada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Chamada.class)
public enum CPChamada {
	_ID, _NOME, _CODIGOCHAMADA, _DATACHAMADA;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String codigochamada = "codigoChamada";
	public static final String datachamada = "dataChamada";
}