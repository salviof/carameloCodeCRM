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
public class AcoesAdminCrm_TipoFormularioTypebotMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getTipoFormularioTypebotMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getTipoFormularioTypebotFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_FRM_LISTAR");
	}

	public ComoAcaoControllerEntidade getTipoFormularioTypebotCtrProcessar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_CTR_PROCESSAR");
	}

	public ComoAcaoControllerEntidade getTipoFormularioTypebotCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_CTR_SALVAR_MERGE");
	}

	public ItfAcaoFormularioEntidade getTipoFormularioTypebotFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_FORMULARIO_TYPEBOT_FRM_EDITAR");
	}
}