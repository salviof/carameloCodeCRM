package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitArqAtualizacaoEqp.class)
public enum CPSolicitacaoAtualizacaoArquivoEquipe {
	_ARQUIVO;

	public static final String arquivo = "arquivo";
}