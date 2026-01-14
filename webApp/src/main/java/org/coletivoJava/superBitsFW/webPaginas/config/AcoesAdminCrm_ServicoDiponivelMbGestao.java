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
public class AcoesAdminCrm_ServicoDiponivelMbGestao implements Serializable {

	public ItfAcaoFormulario getServicoDiponivelMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getServicoDiponivelFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getServicoDiponivelFrmNovoItemRecorrente() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_FRM_NOVO_ITEM_RECORRENTE");
	}

	public ItfAcaoFormularioEntidade getServicoDiponivelFrmNovoItemSazonal() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_FRM_NOVO_ITEM_SAZONAL");
	}

	public ComoAcaoControllerEntidade getServicoDiponivelCtrConverterParaSazonal() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_CONVERTER_PARA_SAZONAL");
	}

	public ComoAcaoControllerEntidade getServicoDiponivelCtrConverterParaRecorrente() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_CONVERTER_PARA_RECORRENTE");
	}

	public ItfAcaoFormularioEntidade getServicoDiponivelFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getServicoDiponivelFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_FRM_EDITAR");
	}

	public ComoAcaoControllerEntidade getServicoDiponivelCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_SALVAR_MERGE");
	}

	public ItfAcaoFormularioEntidade getServicoDiponivelFrmRemover() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_FRM_REMOVER");
	}

	public ComoAcaoControllerEntidade getServicoDiponivelCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.SERVICO_DIPONIVEL_CTR_REMOVER");
	}
}