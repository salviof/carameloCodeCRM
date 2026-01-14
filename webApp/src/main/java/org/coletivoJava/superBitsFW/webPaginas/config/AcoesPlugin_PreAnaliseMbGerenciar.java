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
public class AcoesPlugin_PreAnaliseMbGerenciar implements Serializable {

	public ItfAcaoFormulario getPreAnaliseMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgencia.PRE_ANALISE_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getPreAnaliseFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgencia.PRE_ANALISE_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getPreAnaliseFrmListarComProspectos() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgencia.PRE_ANALISE_FRM_LISTAR_COM_PROSPECTOS");
	}

	public ItfAcaoFormularioEntidade getPreAnaliseFrmVisualisar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgencia.PRE_ANALISE_FRM_VISUALISAR");
	}

	public ItfAcaoFormularioEntidade getPreAnaliseFrmNova() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgencia.PRE_ANALISE_FRM_NOVA");
	}

	public ComoAcaoControllerEntidade getPreAnaliseCtrAssociarPreanise() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgencia.PRE_ANALISE_CTR_ASSOCIAR_PREANISE");
	}

	public ComoAcaoControllerEntidade getPreAnaliseCtrGerarDocumento() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoAgencia.PRE_ANALISE_CTR_GERAR_DOCUMENTO");
	}
}