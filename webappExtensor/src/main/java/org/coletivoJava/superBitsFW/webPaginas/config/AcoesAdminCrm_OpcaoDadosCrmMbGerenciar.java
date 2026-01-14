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
public class AcoesAdminCrm_OpcaoDadosCrmMbGerenciar implements Serializable {

	public ItfAcaoFormulario getOpcaoDadosCrmMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getOpcaoDadosCrmFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getOpcaoDadosCrmFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getOpcaoDadosCrmFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getOpcaoDadosCrmFrmDadosDinamicos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_FRM_DADOS_DINAMICOS");
	}

	public ItfAcaoFormularioEntidade getOpcaoDadosCrmFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getOpcaoDadosCrmCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getOpcaoDadosCrmCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.OPCAO_DADOS_CRM_CTR_REMOVER");
	}
}