package br.org.carameloCode.erp.modulo.crm.api.model.subpastaequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SubPastaEquipe.class)
public enum ValoresLogicosSubPastaEquipe {
	DIRETORIOPROXIMO, PASTAPUBLICA, MIGALHASDEPAO
}