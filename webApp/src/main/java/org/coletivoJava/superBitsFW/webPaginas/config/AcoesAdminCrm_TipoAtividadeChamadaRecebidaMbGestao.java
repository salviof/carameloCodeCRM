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
public class AcoesAdminCrm_TipoAtividadeChamadaRecebidaMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getTipoAtividadeChamadaRecebidaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_RECEBIDA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeChamadaRecebidaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_RECEBIDA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeChamadaRecebidaFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_RECEBIDA_FRM_NOVO");
	}

	public ComoAcaoControllerEntidade getTipoAtividadeChamadaRecebidaCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_RECEBIDA_CTR_SALVAR_MERGE");
	}

	public ItfAcaoFormularioEntidade getTipoAtividadeChamadaRecebidaFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_ATIVIDADE_CHAMADA_RECEBIDA_FRM_EDITAR");
	}
}