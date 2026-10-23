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
public class AcoesAdminTools_TipoExecucaoIaMbGestao implements Serializable {

	public ItfAcaoFormulario getTipoExecucaoIaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.TIPO_EXECUCAO_IA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getTipoExecucaoIaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.TIPO_EXECUCAO_IA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoExecucaoIaFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.TIPO_EXECUCAO_IA_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getTipoExecucaoIaFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.TIPO_EXECUCAO_IA_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getTipoExecucaoIaFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.TIPO_EXECUCAO_IA_FRM_EDITAR");
	}

	public ComoAcaoControllerEntidade getTipoExecucaoIaCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.TIPO_EXECUCAO_IA_CTR_SALVAR_MERGE");
	}
}