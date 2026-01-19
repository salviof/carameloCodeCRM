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
public class AcoesAdminCrm_TipoAtividadeChamadaRealizadaMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getTipoAtividadeChamadaRealizadaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_REALIZADA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeChamadaRealizadaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_REALIZADA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeChamadaRealizadaFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_REALIZADA_FRM_NOVO");
	}

	public ComoAcaoControllerEntidade getTipoAtividadeChamadaRealizadaCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_REALIZADA_CTR_SALVAR_MERGE");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeChamadaRealizadaFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_REALIZADA_FRM_EDITAR");
	}
}