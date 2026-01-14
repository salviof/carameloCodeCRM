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
public class AcoesClienteContato_DashboardMbGestao implements Serializable {

	public ItfAcaoFormulario getDashboardMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DASHBOARD_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getDashboardFrmMeusDados() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DASHBOARD_FRM_MEUS_DADOS");
	}

	public ComoAcaoControllerEntidade getDashboardCtrMuitoSatisfeito() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DASHBOARD_CTR_MUITO_SATISFEITO");
	}

	public ComoAcaoControllerEntidade getDashboardCtrSatisfeito() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DASHBOARD_CTR_SATISFEITO");
	}

	public ComoAcaoControllerEntidade getDashboardCtrInsatisfeito() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMCliente.DASHBOARD_CTR_INSATISFEITO");
	}
}