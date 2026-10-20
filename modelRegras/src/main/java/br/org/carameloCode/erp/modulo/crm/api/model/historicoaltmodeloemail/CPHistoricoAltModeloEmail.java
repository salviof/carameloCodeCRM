package br.org.carameloCode.erp.modulo.crm.api.model.historicoaltmodeloemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.HistoricoAltModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = HistoricoAltModeloEmail.class)
public enum CPHistoricoAltModeloEmail {
	_ID, _ASSUNTO, _CONTEUDO, _MODELO, _DATAHORA, _USUARIO, _EXECUCAOIA;

	public static final String id = "id";
	public static final String assunto = "assunto";
	public static final String conteudo = "conteudo";
	public static final String modelo = "modelo";
	public static final String datahora = "dataHora";
	public static final String usuario = "usuario";
	public static final String execucaoia = "execucaoIA";
}