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
public class AcoesAdminCrm_TipoAtividadeMbGerenciar implements Serializable {

	public ItfAcaoFormulario getTipoAtividadeMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeFrmDadosColetar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_DADOS_COLETAR");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeFrmPermissao() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_PERMISSAO");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeFrmFormatarEmailVinculado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_FORMATAR_EMAIL_VINCULADO");
	}

	public ComoAcaoControllerEntidade getTipoAtividadeCtrVincularEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_VINCULAR_EMAIL");
	}

	public ComoAcaoControllerEntidade getTipoAtividadeCtrDesvincularEmail() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_DESVINCULAR_EMAIL");
	}

	public ComoAcaoControllerEntidade getTipoAtividadeCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_SALVAR_MERGE");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeFrmRemover() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_REMOVER");
	}

	public ComoAcaoControllerEntidade getTipoAtividadeCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_REMOVER");
	}
}