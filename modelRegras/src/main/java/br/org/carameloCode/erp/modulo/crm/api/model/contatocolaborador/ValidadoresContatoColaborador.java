package br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoColaborador.ContatoColaborador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ContatoColaborador.class)
public enum ValidadoresContatoColaborador {
	NOME, EMAIL
}