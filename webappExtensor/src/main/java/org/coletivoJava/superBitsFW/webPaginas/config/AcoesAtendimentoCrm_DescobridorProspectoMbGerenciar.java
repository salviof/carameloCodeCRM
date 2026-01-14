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
public class AcoesAtendimentoCrm_DescobridorProspectoMbGerenciar
		implements
			Serializable {

	public ItfAcaoFormulario getDescobridorProspectoMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getDescobridorProspectoFrmListarDescobertas() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_FRM_LISTAR_DESCOBERTAS");
	}

	public ItfAcaoFormularioEntidade getDescobridorProspectoFrmVerProspectosDaDescoberta() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_FRM_VER_PROSPECTOS_DA_DESCOBERTA");
	}

	public ItfAcaoFormularioEntidade getDescobridorProspectoFrmPesquisa() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_FRM_PESQUISA");
	}

	public ComoAcaoControllerEntidade getDescobridorProspectoCtrPesquisar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_CTR_PESQUISAR");
	}

	public ComoAcaoControllerEntidade getDescobridorProspectoCtrSalvarResultado() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_CTR_SALVAR_RESULTADO");
	}
}