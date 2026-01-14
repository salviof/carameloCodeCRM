package br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = EnvioEmailAtividade.class)
public enum ValidadoresEnvioEmailAtividade {
	CONTATOS, EMAILSOLICITANTE, ASSUNTO
}