package org.coletivoJava.superBitsFW.webPaginas.config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;

@Named
@ApplicationScoped
public class AcoesPaginasDoSistema_PaginaNativaRecuperacaoSenhaMb
		implements
			Serializable {

	public ItfAcaoFormulario getPaginaNativaRecuperacaoSenhaMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_MB");
	}

	public ItfAcaoFormularioEntidade getPaginaNativaRecuperacaoSenhaFrmGerarNovaSenha() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoPaginasDoSistema.PAGINA_NATIVA_RECUPERACAO_SENHA_FRM_GERAR_NOVA_SENHA");
	}
}