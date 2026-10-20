package br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tipoinstrucaopersona;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.TipoInstrucaoPersona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = TipoInstrucaoPersona.class)
public enum CPTipoInstrucaoPersona {
	_ID, _NOME, _DESCRICAO, _ICONE;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String descricao = "descricao";
	public static final String icone = "icone";
}