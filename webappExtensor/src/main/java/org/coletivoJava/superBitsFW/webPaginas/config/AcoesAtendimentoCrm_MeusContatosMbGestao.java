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
public class AcoesAtendimentoCrm_MeusContatosMbGestao implements Serializable {

	public ItfAcaoFormulario getMeusContatosMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMeusContatosFrmEnviarMktWtzap() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_ENVIAR_MKT_WTZAP");
	}

	public ItfAcaoFormularioEntidade getMeusContatosFrmConviteReserva() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_CONVITE_RESERVA");
	}

	public ItfAcaoFormularioEntidade getMeusContatosFrmConvitePrimeiroAcesso() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_CONVITE_PRIMEIRO_ACESSO");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrConvidarPrimeiroAcessoPorEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PRIMEIRO_ACESSO_POR_EMAIL");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrConvidarPrimeiroAcessoPorWhatsapp() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PRIMEIRO_ACESSO_POR_WHATSAPP");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrConvidarAgendaPorEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_AGENDA_POR_EMAIL");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrConvidarAgendaPorWhatsapp() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_AGENDA_POR_WHATSAPP");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrConvidarAcessoPorWhatsapp() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_ACESSO_POR_WHATSAPP");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrConvidarAcessoPorEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_ACESSO_POR_EMAIL");
	}

	public ItfAcaoFormularioEntidade getMeusContatosFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getMeusContatosFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getMeusContatosFrmSmsFormatar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_SMS_FORMATAR");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrSmsEnviar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_SMS_ENVIAR");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrLigarPeloCelular() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PELO_CELULAR");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrAbrirChatWhatsapp() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_ABRIR_CHAT_WHATSAPP");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrConvidarParaChat() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PARA_CHAT");
	}

	public ComoAcaoControllerEntidade getMeusContatosCtrLigarPabxOnline() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PABX_ONLINE");
	}
}