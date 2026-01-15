package br.org.carameloCode.erp.modulo.crm.api.model.origemprospectoprivado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = OrigemProspectoPrivado.class)
public enum CPOrigemProspectoPrivado {
	_USUARIODONO, _PORCENTAGEMAOCOMPARTILHAR;

	public static final String usuariodono = "usuarioDono";
	public static final String porcentagemaocompartilhar = "porcentagemAoCompartilhar";
}