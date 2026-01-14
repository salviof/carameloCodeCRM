package br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PesquisaLead.class)
public enum ValidadoresPesquisaLead {
	ORIGEM, DATAINICIAL, DATAFINAL, TIPOPESQUISA, USUARIO
}