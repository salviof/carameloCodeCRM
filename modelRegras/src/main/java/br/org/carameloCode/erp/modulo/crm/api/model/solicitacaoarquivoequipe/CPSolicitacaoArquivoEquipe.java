package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoArquivoEquipe.class)
public enum CPSolicitacaoArquivoEquipe {
	_CATEGORIAARQEQUIPE;

	public static final String categoriaarqequipe = "categoriaArqEquipe";
}