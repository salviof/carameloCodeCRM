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
public class AcoesAdminCrm_MetaRelacionamentoMb implements Serializable {

	public ItfAcaoFormulario getMetaRelacionamentoMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_MB");
	}

	public ItfAcaoFormularioEntidade getMetaRelacionamentoFrmListar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_FRM_LISTAR");
	}

	public ItfAcaoFormularioEntidade getMetaRelacionamentoFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getMetaRelacionamentoFrmAtividadesVinculados() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_FRM_ATIVIDADES_VINCULADOS");
	}

	public ItfAcaoFormularioEntidade getMetaRelacionamentoFrmNovo() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_FRM_NOVO");
	}

	public ComoAcaoControllerEntidade getMetaRelacionamentoCtrMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_CTR_MERGE");
	}

	public ItfAcaoFormularioEntidade getMetaRelacionamentoFrmConverter() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_FRM_CONVERTER");
	}

	public ComoAcaoControllerEntidade getMetaRelacionamentoCtrConverter() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_CTR_CONVERTER");
	}

	public ComoAcaoControllerEntidade getMetaRelacionamentoCtrRemover() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCrmAdmin.META_RELACIONAMENTO_CTR_REMOVER");
	}
}