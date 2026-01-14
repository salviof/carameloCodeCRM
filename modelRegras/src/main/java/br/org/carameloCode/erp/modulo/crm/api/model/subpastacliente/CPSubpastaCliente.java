package br.org.carameloCode.erp.modulo.crm.api.model.subpastacliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubpastaCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = SubpastaCliente.class)
public enum CPSubpastaCliente {
	_CATEGORIACLIENTE, _DIRETORIOPROXIMO, _SUBPASTAS, _ARQUIVOS;

	public static final String categoriacliente = "categoriaCliente";
	public static final String diretorioproximo = "diretorioProximo";
	public static final String subpastas = "subPastas";
	public static final String arquivos = "arquivos";
}