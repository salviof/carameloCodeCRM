package br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = AreaTrabalho.class)
public enum CPAreaTrabalho {
	_ID, _NOME, _USUARIO, _META, _RELACIONAMENTO, _TAG, _NAOLIDOEMAILQTD, _NAOLIDOEMAIDESCONHECIDOLQTD, _NAOLIDOEMAILASSINATURASQTD, _NAOLIDOEMAILPESSOASQTD, _NAOLIDAMENSAGEMQTD, _NAOEXECUTADOAGENDAQTD, _MOSTRARLISTAMINHASPESSOAS, _DATAHORAATUALIZACAO;

	public static final String id = "id";
	public static final String nome = "nome";
	public static final String usuario = "usuario";
	public static final String meta = "meta";
	public static final String relacionamento = "relacionamento";
	public static final String tag = "tag";
	public static final String naolidoemailqtd = "naoLidoEmailQtd";
	public static final String naolidoemaidesconhecidolqtd = "naoLidoEmaiDesconhecidolQtd";
	public static final String naolidoemailassinaturasqtd = "naoLidoEmailAssinaturasQtd";
	public static final String naolidoemailpessoasqtd = "naoLidoEmailPessoasQtd";
	public static final String naolidamensagemqtd = "naoLidaMensagemQtd";
	public static final String naoexecutadoagendaqtd = "naoExecutadoAgendaQtd";
	public static final String mostrarlistaminhaspessoas = "mostrarListaMinhasPessoas";
	public static final String datahoraatualizacao = "dataHoraAtualizacao";
}