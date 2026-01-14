package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;

@Named
@ApplicationScoped
public class AcoesClienteContato_ChamadoMbGestao implements Serializable {

	public ItfAcaoFormulario getChamadoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getChamadoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getChamadoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getChamadoFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR");
	}

	public ComoAcaoControllerEntidade getChamadoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_CTR_SALVAR_MERGE");
	}

	public ComoAcaoController getChamadoCtrNotificarChamadosAbertosAutoExec() {
		return (ComoAcaoController) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_CTR_NOTIFICAR_CHAMADOS_ABERTOS_AUTO_EXEC");
	}

	public ComoAcaoControllerEntidade getChamadoCtrNotificarResonsaveis() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_CTR_NOTIFICAR_RESONSAVEIS");
	}

	public ComoAcaoControllerEntidade getChamadoCtrFinalizar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_CTR_FINALIZAR");
	}

	public ComoAcaoControllerEntidade getChamadoCtrAbrirChamado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_CTR_ABRIR_CHAMADO");
	}

	public ComoAcaoControllerEntidade getChamadoCtrCancelar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.CHAMADO_CTR_CANCELAR");
	}
}