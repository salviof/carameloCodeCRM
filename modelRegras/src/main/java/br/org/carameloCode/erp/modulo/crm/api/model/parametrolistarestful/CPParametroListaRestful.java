package br.org.carameloCode.erp.modulo.crm.api.model.parametrolistarestful;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.parametros.ParametroListaRestful;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ParametroListaRestful.class)
public enum CPParametroListaRestful {
	_PAGINA, _LIMITE, _ATRIBUTO, _FILTROS;

	public static final String pagina = "pagina";
	public static final String limite = "limite";
	public static final String atributo = "atributo";
	public static final String filtros = "filtros";
}