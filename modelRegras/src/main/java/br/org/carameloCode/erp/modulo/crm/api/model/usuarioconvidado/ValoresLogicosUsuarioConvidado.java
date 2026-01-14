package br.org.carameloCode.erp.modulo.crm.api.model.usuarioconvidado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = UsuarioConvidado.class)
public enum ValoresLogicosUsuarioConvidado {
	AREATRABALHOPADRAO, PROSPECTOSRELACIONAMENTOCOMPESO, PROSPECTOSRELACIONAMENTOSEMPESO, ASSINATURAEMAIL, ESTATISTICAS, CONTATOVINCULADO, CAIXAPOSTALPRINCIPAL, CAIXASPOSTAIS, METADADOSATENDENTE, ESCOPOAGENDACLIENTES
}