package br.org.carameloCode.erp.modulo.crm.api.model.arquivoanexadoemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmail.ArquivoAnexadoEmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ArquivoAnexadoEmailRecebido.class)
public enum CPArquivoAnexadoEmail {
	_EMAILCRM;

	public static final String emailcrm = "emailCrm";
}