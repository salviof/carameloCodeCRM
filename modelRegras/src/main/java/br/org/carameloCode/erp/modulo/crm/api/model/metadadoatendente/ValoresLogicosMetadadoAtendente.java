package br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.MetadadoAtendente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = MetadadoAtendente.class)
public enum ValoresLogicosMetadadoAtendente {
	CHAMADOSEMATENDIMENTO, CHAMADOSAGUARDANDOATENDIMENTO, RESERVASATIVAS, CLIENTESSATISFEITOS, CLIENTESMUITOSATISFEITOS, CLIENTESINSATISFEITOS
}