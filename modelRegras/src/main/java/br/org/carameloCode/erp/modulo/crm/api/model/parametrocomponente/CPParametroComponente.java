package br.org.carameloCode.erp.modulo.crm.api.model.parametrocomponente;

import com.super_bits.telas.compomente.ParametroComponente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ParametroComponente.class)
public enum CPParametroComponente {
	_ID, _VALORPADRAO, _NOMEPARAMETRO;

	public static final String id = "id";
	public static final String valorpadrao = "valorPadrao";
	public static final String nomeparametro = "nomeParametro";
}