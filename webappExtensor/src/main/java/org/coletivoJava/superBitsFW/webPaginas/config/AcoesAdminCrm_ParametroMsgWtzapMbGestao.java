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
public class AcoesAdminCrm_ParametroMsgWtzapMbGestao implements Serializable {

	public ItfAcaoFormulario getParametroMsgWtzapMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getParametroMsgWtzapFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getParametroMsgWtzapFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getParametroMsgWtzapFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getParametroMsgWtzapFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getParametroMsgWtzapCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.PARAMETRO_MSG_WTZAP_CTR_SALVAR_MERGE");
	}
}