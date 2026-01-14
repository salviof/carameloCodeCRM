package br.org.carameloCode.erp.modulo.crm.api.model.subpastacliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubpastaCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SubpastaCliente.class)
public enum ValoresLogicosSubpastaCliente {
	DIRETORIOPROXIMO, PASTAPUBLICA, MIGALHASDEPAO
}