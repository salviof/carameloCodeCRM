package br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = UsuarioCrmCliente.class)
public enum ValoresLogicosUsuarioCrmCliente {
	CONTATOCLIENTEVINCULADO, CHAVESECRETARC, METADADOS, RESERVAATIVAMAISPROXIMA, POSSUIRESERVAATIVA, AREATRABALHOPADRAO, PROSPECTOSRELACIONAMENTOCOMPESO, PROSPECTOSRELACIONAMENTOSEMPESO, ASSINATURAEMAIL, ESTATISTICAS, CONTATOVINCULADO, CAIXAPOSTALPRINCIPAL, CAIXASPOSTAIS, METADADOSATENDENTE, ESCOPOAGENDACLIENTES
}