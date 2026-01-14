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
public class AcoesAdminCrm_MauticMbIntegracao implements Serializable {

	public ItfAcaoFormulario getMauticMbIntegracao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MAUTIC_MB_INTEGRACAO");
	}

	public ItfAcaoFormularioEntidade getMauticFrmConfiguracoes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MAUTIC_FRM_CONFIGURACOES");
	}

	public ComoAcaoControllerEntidade getMauticCtrSalvarConfiguracoes() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MAUTIC_CTR_SALVAR_CONFIGURACOES");
	}

	public ItfAcaoFormularioEntidade getMauticFrmExportar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MAUTIC_FRM_EXPORTAR");
	}

	public ItfAcaoFormularioEntidade getMauticFrmSelecionarOpcoes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MAUTIC_FRM_SELECIONAR_OPCOES");
	}

	public ComoAcaoControllerEntidade getMauticCtrExportarDados() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MAUTIC_CTR_EXPORTAR_DADOS");
	}
}