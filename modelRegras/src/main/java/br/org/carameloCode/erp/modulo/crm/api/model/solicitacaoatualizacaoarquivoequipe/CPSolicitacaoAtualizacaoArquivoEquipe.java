package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoAtualizacaoArquivoEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoAtualizacaoArquivoEquipe.class)
public enum CPSolicitacaoAtualizacaoArquivoEquipe {
	_ARQUIVO;

	public static final String arquivo = "arquivo";
}