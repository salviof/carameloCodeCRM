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
public class AcoesAtendimentoCrm_MeuPerfilMb implements Serializable {

	public ItfAcaoFormulario getMeuPerfilMb() {
		return (ItfAcaoFormulario) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_MB");
	}

	public ItfAcaoFormularioEntidade getMeuPerfilFrmEditarTags() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_TAGS");
	}

	public ItfAcaoFormularioEntidade getMeuPerfilFrmEditar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR");
	}

	public ItfAcaoFormularioEntidade getMeuPerfilFrmAlterarSenha() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_FRM_ALTERAR_SENHA");
	}

	public ItfAcaoFormularioEntidade getMeuPerfilFrmAlterarSenhaMatrix() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_FRM_ALTERAR_SENHA_MATRIX");
	}

	public ItfAcaoFormularioEntidade getMeuPerfilFrmAlterarAvatar() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_FRM_ALTERAR_AVATAR");
	}

	public ItfAcaoFormularioEntidade getMeuPerfilFrmEditarAssinaturaEmail() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_ASSINATURA_EMAIL");
	}

	public ItfAcaoFormularioEntidade getMeuPerfilFrmEditarServidorEnvioPersonalizado() {
		return (ItfAcaoFormularioEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_FRM_EDITAR_SERVIDOR_ENVIO_PERSONALIZADO");
	}

	public ComoAcaoControllerEntidade getMeuPerfilCtrSalvarMerge() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_CTR_SALVAR_MERGE");
	}

	public ComoAcaoControllerEntidade getMeuPerfilCtrLembrarMaisTardeAtividadesInacabadas() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_CTR_LEMBRAR_MAIS_TARDE_ATIVIDADES_INACABADAS");
	}

	public ComoAcaoControllerEntidade getMeuPerfilCtrTestar() {
		return (ComoAcaoControllerEntidade) MapaAcoesSistema
				.getAcaoDoSistemaByNomeUnico("FabAcaoCRMAtendimento.MEU_PERFIL_CTR_TESTAR");
	}
}