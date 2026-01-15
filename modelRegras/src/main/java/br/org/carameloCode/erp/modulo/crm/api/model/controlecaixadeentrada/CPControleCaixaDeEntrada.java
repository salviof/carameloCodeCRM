package br.org.carameloCode.erp.modulo.crm.api.model.controlecaixadeentrada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.ControleCaixaDeEntrada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = ControleCaixaDeEntrada.class)
public enum CPControleCaixaDeEntrada {
	_ID, _EMAIL, _NOMEPASTA, _CODIGOPRIMEIROEMAIL, _CODIGOULTIMOEMAIL, _QUANTIDADEEMAILS, _UMACAIXADESAPAN, _USUARIOPRINCIPAL;

	public static final String id = "id";
	public static final String email = "email";
	public static final String nomepasta = "nomePasta";
	public static final String codigoprimeiroemail = "codigoPrimeiroEmail";
	public static final String codigoultimoemail = "codigoUltimoEmail";
	public static final String quantidadeemails = "quantidadeEmails";
	public static final String umacaixadesapan = "umaCaixaDeSapan";
	public static final String usuarioprincipal = "usuarioPrincipal";
}