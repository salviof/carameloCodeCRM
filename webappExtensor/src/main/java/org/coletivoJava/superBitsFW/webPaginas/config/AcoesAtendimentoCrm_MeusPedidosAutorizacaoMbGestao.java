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
public class AcoesAtendimentoCrm_MeusPedidosAutorizacaoMbGestao
		implements
			Serializable {

	public ItfAcaoFormulario getMeusPedidosAutorizacaoMbGestao() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PEDIDOS_AUTORIZACAO_MB_GESTAO");
	}

	public ItfAcaoFormularioEntidade getMeusPedidosAutorizacaoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PEDIDOS_AUTORIZACAO_FRM_LISTAR");
	}

	public ComoAcaoControllerEntidade getMeusPedidosAutorizacaoCtrAprovar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PEDIDOS_AUTORIZACAO_CTR_APROVAR");
	}

	public ComoAcaoControllerEntidade getMeusPedidosAutorizacaoCtrNegar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEUS_PEDIDOS_AUTORIZACAO_CTR_NEGAR");
	}
}