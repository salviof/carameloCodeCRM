package br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = UsuarioCRM.class)
public enum ValoresLogicosUsuarioCRM {
	AREATRABALHOPADRAO, PROSPECTOSRELACIONAMENTOCOMPESO, PROSPECTOSRELACIONAMENTOSEMPESO, ASSINATURAEMAIL, ESTATISTICAS, CONTATOVINCULADO, CAIXAPOSTALPRINCIPAL, CAIXASPOSTAIS, METADADOSATENDENTE, ESCOPOAGENDACLIENTES
}