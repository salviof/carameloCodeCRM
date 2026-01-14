package br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ContatoProspecto.class)
public enum ValoresLogicosContatoProspecto {
	CELULARFORMATOINTERNACIONAL, PROSPECTOSDISPONIVEIS, CODIGOMAUTIC, USUARIOVINCULADO, ATIVO
}