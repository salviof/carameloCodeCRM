package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;

@Named
@ApplicationScoped
public class AcoesAdminCrm_ConfiguracoesGeraisMb implements Serializable {

	public ItfAcaoFormulario getConfiguracoesGeraisMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_MB");
	}

	public ItfAcaoFormularioEntidade getConfiguracoesGeraisFrmConfigurarEmails() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_CONFIGURAR_EMAILS");
	}

	public ComoAcaoControllerEntidade getConfiguracoesGeraisCtrSalvarConfiguracao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_SALVAR_CONFIGURACAO");
	}

	public ComoAcaoControllerEntidade getConfiguracoesGeraisCtrTestarConfiguracao() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_TESTAR_CONFIGURACAO");
	}

	public ItfAcaoFormularioEntidade getConfiguracoesGeraisFrmListarAssinaturas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_LISTAR_ASSINATURAS");
	}

	public ItfAcaoFormularioEntidade getConfiguracoesGeraisFrmEditarAssinatura() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_EDITAR_ASSINATURA");
	}

	public ComoAcaoControllerEntidade getConfiguracoesGeraisCtrSalvarAssinatura() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_SALVAR_ASSINATURA");
	}

	public ItfAcaoFormularioEntidade getConfiguracoesGeraisFrmReceberEmails() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_FRM_RECEBER_EMAILS");
	}

	public ComoAcaoControllerEntidade getConfiguracoesGeraisCtrReceberEmails() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_RECEBER_EMAILS");
	}

	public ComoAcaoControllerEntidade getConfiguracoesGeraisCtrReceberEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.CONFIGURACOES_GERAIS_CTR_RECEBER_EMAIL");
	}
}