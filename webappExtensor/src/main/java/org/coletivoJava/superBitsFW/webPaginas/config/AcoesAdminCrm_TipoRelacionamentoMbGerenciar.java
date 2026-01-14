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
public class AcoesAdminCrm_TipoRelacionamentoMbGerenciar
		implements
			Serializable {

	public ItfAcaoFormulario getTipoRelacionamentoMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmConverter() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_CONVERTER");
	}

	public ComoAcaoControllerEntidade getTipoRelacionamentoCtrConverter() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_CONVERTER");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmResponsaveis() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_RESPONSAVEIS");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmDadosNescessarios() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_DADOS_NESCESSARIOS");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmAtividadesDisponiveis() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_ATIVIDADES_DISPONIVEIS");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_EDITAR");
	}

	public ComoAcaoControllerEntidade getTipoRelacionamentoCtrMoverleads() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_MOVERLEADS");
	}

	public ComoAcaoControllerEntidade getTipoRelacionamentoCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_REMOVER");
	}

	public ItfAcaoFormularioEntidade getTipoRelacionamentoFrmRemover() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_FRM_REMOVER");
	}

	public ComoAcaoControllerEntidade getTipoRelacionamentoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_RELACIONAMENTO_CTR_SALVAR_MERGE");
	}
}