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
public class AcoesAdminCrm_ManutencaoDadosProspectoMb implements Serializable {

	public ItfAcaoFormulario getManutencaoDadosProspectoMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_MB");
	}

	public ItfAcaoFormularioEntidade getManutencaoDadosProspectoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getManutencaoDadosProspectoFrmVisualizarDado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_VISUALIZAR_DADO");
	}

	public ItfAcaoFormularioEntidade getManutencaoDadosProspectoFrmProximoDado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_PROXIMO_DADO");
	}

	public ItfAcaoFormularioEntidade getManutencaoDadosProspectoFrmDadoAnterior() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_FRM_DADO_ANTERIOR");
	}

	public ComoAcaoControllerEntidade getManutencaoDadosProspectoCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.MANUTENCAO_DADOS_PROSPECTO_CTR_SALVAR_MERGE");
	}
}