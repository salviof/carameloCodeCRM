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
public class AcoesAtendimentoCrm_LoginMbGerenciar implements Serializable {

	public ItfAcaoFormulario getLoginMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.LOGIN_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getLoginFrmRealizarLogin() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_LOGIN");
	}

	public ItfAcaoFormularioEntidade getLoginFrmRealizarLoginDoCliente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_LOGIN_DO_CLIENTE");
	}

	public ItfAcaoFormularioEntidade getLoginFrmRealizarPrimeiroAcessoPeloEmail() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_REALIZAR_PRIMEIRO_ACESSO_PELO_EMAIL");
	}

	public ItfAcaoFormularioEntidade getLoginFrmClienteCadastrarSenhaPrimeiroAcesso() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.LOGIN_FRM_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO");
	}

	public ComoAcaoControllerEntidade getLoginCtrClienteCadastrarSenhaPrimeiroAcesso() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAcessoAnonimoIntranet.LOGIN_CTR_CLIENTE_CADASTRAR_SENHA_PRIMEIRO_ACESSO");
	}
}