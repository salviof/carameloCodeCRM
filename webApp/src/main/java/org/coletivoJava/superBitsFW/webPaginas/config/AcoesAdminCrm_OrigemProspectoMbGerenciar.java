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
public class AcoesAdminCrm_OrigemProspectoMbGerenciar implements Serializable {

	public ItfAcaoFormulario getOrigemProspectoMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getOrigemProspectoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getOrigemProspectoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getOrigemProspectoFrmRemover() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_REMOVER");
	}

	public ComoAcaoControllerEntidade getOrigemProspectoCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_REMOVER");
	}

	public ItfAcaoFormularioEntidade getOrigemProspectoFrmConverter() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_CONVERTER");
	}

	public ComoAcaoControllerEntidade getOrigemProspectoCtrConverter() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_CONVERTER");
	}

	public ComoAcaoControllerEntidade getOrigemProspectoCtrMover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_MOVER");
	}

	public ItfAcaoFormularioEntidade getOrigemProspectoFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getOrigemProspectoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_FRM_LISTAR");
	}

	public ComoAcaoControllerEntidade getOrigemProspectoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.ORIGEM_PROSPECTO_CTR_SALVAR_MERGE");
	}
}