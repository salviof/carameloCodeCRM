package br.org.carameloCode.erp.modulo.crm.api.model.subpastaprivada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaPrivada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SubPastaPrivada.class)
public enum CPSubPastaPrivada {
	_ID, _NOME, _TIPOSUBPASTA, _PASTAPAI, _PASTAPUBLICA, _PESSOA, _MIGALHASDEPAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String tiposubpasta = "tipoSubPasta";
	public static final String pastapai = "pastaPai";
	public static final String pastapublica = "pastaPublica";
	public static final String pessoa = "pessoa";
	public static final String migalhasdepao = "migalhasDePao";
}