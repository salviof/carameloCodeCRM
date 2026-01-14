package br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SubPastaEquipe.class)
public enum CPSubPastaEquipe {
	_CATEGORIAEQUIPE, _SUBPASTAS, _DIRETORIOPROXIMO;

	public static final String categoriaequipe = "categoriaEquipe";
	public static final String subpastas = "subPastas";
	public static final String diretorioproximo = "diretorioProximo";
}