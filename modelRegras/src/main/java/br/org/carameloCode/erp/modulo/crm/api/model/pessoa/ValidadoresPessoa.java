package br.org.carameloCode.erp.modulo.crm.api.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = Pessoa.class)
public enum ValidadoresPessoa {
	EMAIL, RELACIONAMENTO, LOCALIZACAO, USUARIOSRESPONSAVEIS, RESPONSAVEL
}