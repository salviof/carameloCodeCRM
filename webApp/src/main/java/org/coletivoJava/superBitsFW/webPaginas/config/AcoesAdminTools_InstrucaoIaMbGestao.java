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
public class AcoesAdminTools_InstrucaoIaMbGestao implements Serializable {

	public ItfAcaoFormulario getInstrucaoIaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.INSTRUCAO_IA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getInstrucaoIaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.INSTRUCAO_IA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getInstrucaoIaFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.INSTRUCAO_IA_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getInstrucaoIaFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.INSTRUCAO_IA_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getInstrucaoIaFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.INSTRUCAO_IA_FRM_EDITAR");
	}

	public ComoAcaoControllerEntidade getInstrucaoIaCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoIAExecucao.INSTRUCAO_IA_CTR_SALVAR_MERGE");
	}
}