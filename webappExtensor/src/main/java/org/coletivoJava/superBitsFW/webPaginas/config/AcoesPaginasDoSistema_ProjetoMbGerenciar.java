package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesPaginasDoSistema_ProjetoMbGerenciar implements Serializable {

	public ItfAcaoFormulario getProjetoMbGerenciar() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoSB.PROJETO_MB_GERENCIAR");
	}

	public ItfAcaoFormularioEntidade getProjetoFrmVisualizarAcoes() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_ACOES");
	}

	public ItfAcaoFormularioEntidade getProjetoFrmVisualizarBancoDeDados() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_BANCO_DE_DADOS");
	}

	public ItfAcaoFormularioEntidade getProjetoFrmVisualizarTabela() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoSB.PROJETO_FRM_VISUALIZAR_TABELA");
	}

	public ItfAcaoFormularioEntidade getProjetoFrmVisaoGeral() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoProjetoSB.PROJETO_FRM_VISAO_GERAL");
	}
}