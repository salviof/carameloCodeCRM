package br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ChamadoCliente.class)
public enum ValidadoresChamadoCliente {
	TIPOCHAMADO, DESCRICAO
}