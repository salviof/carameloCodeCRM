package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesAtendimentoCrm_MeuDashboardMbGestao implements Serializable {

	public ItfAcaoFormulario getMeuDashboardMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_DASHBOARD_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMeuDashboardFrmTotais() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_TOTAIS");
	}

	public ItfAcaoFormularioEntidade getMeuDashboardFrmTotaisAdm() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_TOTAIS_ADM");
	}

	public ItfAcaoFormularioEntidade getMeuDashboardFrmOrigensPublica() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_ORIGENS_PUBLICA");
	}

	public ItfAcaoFormularioEntidade getMeuDashboardFrmOrigensPrivadas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_ORIGENS_PRIVADAS");
	}

	public ItfAcaoFormularioEntidade getMeuDashboardFrmOrigensCompartilhadas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_DASHBOARD_FRM_ORIGENS_COMPARTILHADAS");
	}
}