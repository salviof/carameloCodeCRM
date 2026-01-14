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
public class AcoesAdminCrm_TipoMensagemMktWtzapMbGestao implements Serializable {

	public ItfAcaoFormulario getTipoMensagemMktWtzapMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getTipoMensagemMktWtzapFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getTipoMensagemMktWtzapFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_FRM_NOVO");
	}

	public ItfAcaoFormularioEntidade getTipoMensagemMktWtzapFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_FRM_EDITAR");
	}

	public ComoAcaoControllerEntidade getTipoMensagemMktWtzapCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.TIPO_MENSAGEM_MKT_WTZAP_CTR_SALVAR_MERGE");
	}
}