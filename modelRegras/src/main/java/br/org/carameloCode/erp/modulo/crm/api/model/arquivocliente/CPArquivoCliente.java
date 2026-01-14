package br.org.carameloCode.erp.modulo.crm.api.model.arquivocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.ArquivoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ArquivoCliente.class)
public enum CPArquivoCliente {
	_CATEGORIAARQCLI;

	public static final String categoriaarqcli = "categoriaArqCli";
}