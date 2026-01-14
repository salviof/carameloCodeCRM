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
public class AcoesAtendimentoCrm_AutenticacaoSocialMb implements Serializable {

	public ItfAcaoFormulario getAutenticacaoSocialMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_MB");
	}

	public ItfAcaoFormularioEntidade getAutenticacaoSocialFrmListarOpcoes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_LISTAR_OPCOES");
	}

	public ItfAcaoFormularioEntidade getAutenticacaoSocialFrmAutenticadoSucesso() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_AUTENTICADO_SUCESSO");
	}

	public ItfAcaoFormularioEntidade getAutenticacaoSocialFrmAutenticarExterno() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_FRM_AUTENTICAR_EXTERNO");
	}

	public ComoAcaoControllerEntidade getAutenticacaoSocialCtrAtualizarCadastro() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_CTR_ATUALIZAR_CADASTRO");
	}

	public ComoAcaoControllerEntidade getAutenticacaoSocialCtrCriarCadastro() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_CTR_CRIAR_CADASTRO");
	}

	public ComoAcaoControllerEntidade getAutenticacaoSocialCtrLogar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoSocialAutenticador.AUTENTICACAO_SOCIAL_CTR_logar");
	}
}