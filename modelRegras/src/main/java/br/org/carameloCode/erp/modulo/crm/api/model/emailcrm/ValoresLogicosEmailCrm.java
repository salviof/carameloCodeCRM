package br.org.carameloCode.erp.modulo.crm.api.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EmailCrm.class)
public enum ValoresLogicosEmailCrm {
	TEXTO, UMEMAILRECEBIDO, UMEMAILDEATIVIDADE, UMEMAILMODORASCUNHO, UMEMAILPRIVADO, UMEMAILCOMPARTILHADO, UMEMAILDEPROSPECTO, UMACONVERSAEXTERNA, UMACONVERSAINTERNA, UMEMAILRESPOSTA, UMEMAILPASSIVO, UMEMAILATIVO, ARQUIVOS, CONTEUDOHTMLPROCESSADO, ICONETIPOEMAIL, ICONEALERTA, CORTIPOEMAIL, ACOESDISPONIVEIS
}