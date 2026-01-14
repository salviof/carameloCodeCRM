package br.org.carameloCode.erp.modulo.crm.api.model.respostaformulario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.resposta.RespostaFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = RespostaFormulario.class)
public enum CPRespostaFormulario {
	_ID, _NOME, _PESSOA, _CODIGORESPOSTA, _JSONRESPOSTA, _USUARIORESPONSAVELATENDIMENTO, _DATAHORA, _TIPOFORMULARIO, _PESSOAEXISTENTE, _DADOSPROCESSADOSSUCESSO, _ATENDENTENOTIFICADO, _DATAHORAULTIMOPROCESSAMENTO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String pessoa = "pessoa";
	public static final String codigoresposta = "codigoResposta";
	public static final String jsonresposta = "jsonResposta";
	public static final String usuarioresponsavelatendimento = "usuarioResponsavelAtendimento";
	public static final String datahora = "dataHora";
	public static final String tipoformulario = "tipoFormulario";
	public static final String pessoaexistente = "pessoaExistente";
	public static final String dadosprocessadossucesso = "dadosProcessadosSucesso";
	public static final String atendentenotificado = "atendenteNotificado";
	public static final String datahoraultimoprocessamento = "dataHoraUltimoProcessamento";
}