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
public class AcoesAtendimentoCrm_MensagemMktMbGestao implements Serializable {

	public ItfAcaoFormulario getMensagemMktMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMensagemMktFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getMensagemMktFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getMensagemMktFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getMensagemMktFrmEnvioSucesso() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_FRM_ENVIO_SUCESSO");
	}

	public ComoAcaoControllerEntidade getMensagemMktCtrEnviar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_CTR_ENVIAR");
	}

	public ComoAcaoControllerEntidade getMensagemMktCtrEnviarTodosContatos() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAtendimentoAgenda.MENSAGEM_MKT_CTR_ENVIAR_TODOS_CONTATOS");
	}
}