package br.org.carameloCode.erp.modulo.crm.api.model.hashsdearquivodeentidade;

import br.org.coletivojava.fw.utils.servico.ServicoRepositorioDeArquivos.model.HashsDeArquivoDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = HashsDeArquivoDeEntidade.class)
public enum CPHashsDeArquivoDeEntidade {
	_ID, _HASHCALCULADO, _ENTIDADE, _ATRIBUTO, _IDENTIDADE;

	public static final String id = "id";
	public static final String hashcalculado = "hashCalculado";
	public static final String entidade = "entidade";
	public static final String atributo = "atributo";
	public static final String identidade = "idEntidade";
}