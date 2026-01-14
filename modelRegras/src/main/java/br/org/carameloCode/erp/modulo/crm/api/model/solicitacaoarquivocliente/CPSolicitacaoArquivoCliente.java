package br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SolicitacaoArquivoCliente.class)
public enum CPSolicitacaoArquivoCliente {
	_CATEGORIAARQCLIENTE, _CONTATOPESSOA;

	public static final String categoriaarqcliente = "categoriaArqCliente";
	public static final String contatopessoa = "contatoPessoa";
}