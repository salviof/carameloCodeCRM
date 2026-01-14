package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesAdminCrm_RespostasFormularioTypebotMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getRespostasFormularioTypebotMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.RESPOSTAS_FORMULARIO_TYPEBOT_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getRespostasFormularioTypebotFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.RESPOSTAS_FORMULARIO_TYPEBOT_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getRespostasFormularioTypebotFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.RESPOSTAS_FORMULARIO_TYPEBOT_FRM_VISUALIZAR");
	}
}