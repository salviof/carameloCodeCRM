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
public class AcoesAtendimentoCrm_EnvioDocumentoMb implements Serializable {

	public ItfAcaoFormulario getEnvioDocumentoMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_MB");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmEtapaGerar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_ETAPA_GERAR");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmEtapaFormatarDocumento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_ETAPA_FORMATAR_DOCUMENTO");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmEtapaEnviarDocumento() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_ETAPA_ENVIAR_DOCUMENTO");
	}

	public ComoAcaoControllerEntidade getEnvioDocumentoCtrEnviarEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_CTR_ENVIAR_EMAIL");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmProspectoSemPermissao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_PROSPECTO_SEM_PERMISSAO");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmProspectoNaoEncontrado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_PROSPECTO_NAO_ENCONTRADO");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmSelecionarTipoAtividade() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_SELECIONAR_TIPO_ATIVIDADE");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmAtividadeFoiFinalizada() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_ATIVIDADE_FOI_FINALIZADA");
	}

	public ItfAcaoFormularioEntidade getEnvioDocumentoFrmEnviarPadrao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.ENVIO_DOCUMENTO_FRM_ENVIAR_PADRAO");
	}
}