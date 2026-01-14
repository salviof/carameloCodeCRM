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
public class AcoesAtendimentoCrm_MinhasOrigensMbGestao implements Serializable {

	public ItfAcaoFormulario getMinhasOrigensMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MINHAS_ORIGENS_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMinhasOrigensFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MINHAS_ORIGENS_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getMinhasOrigensFrmVisualizar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MINHAS_ORIGENS_FRM_VISUALIZAR");
	}

	public ItfAcaoFormularioEntidade getMinhasOrigensFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MINHAS_ORIGENS_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getMinhasOrigensFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MINHAS_ORIGENS_FRM_NOVO");
	}

	public ComoAcaoControllerEntidade getMinhasOrigensCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MINHAS_ORIGENS_CTR_SALVAR_MERGE");
	}
}