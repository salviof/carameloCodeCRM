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
public class AcoesAdminCrm_TipoDadoIaMbGestao implements Serializable {

	public ItfAcaoFormulario getTipoDadoIaMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.TIPO_DADO_IA_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getTipoDadoIaFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.TIPO_DADO_IA_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoIaFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.TIPO_DADO_IA_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getTipoDadoIaFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.TIPO_DADO_IA_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getTipoDadoIaCtrSalvarMerg() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMIA.TIPO_DADO_IA_CTR_SALVAR_MERG");
	}
}