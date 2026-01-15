package br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = OrigemProspecto.class)
public enum ValoresLogicosOrigemProspecto {
	UMAORIGEMPRIVADA, UMAORIGEMPUBLICA, QUANTIDADELEADS, QUANTIDADEMEUSLEADS, QTDLEADSATIVOS, QTDLEADSINATIVOS
}