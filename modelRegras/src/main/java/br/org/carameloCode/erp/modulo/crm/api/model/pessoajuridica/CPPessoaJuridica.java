package br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoReferenciaEntidade;

@InfoReferenciaEntidade(tipoObjeto = PessoaJuridica.class)
public enum CPPessoaJuridica {
	_COMPLEMENTO, _SITE, _CNPJ, _ORIGEM, _CONTATOSPROSPECTO, _ARQUIVOSENVIADOS, _ARQUIVOSRECEBIDOS, _CAMINHOLOCALIMAGEM, _CAMINHOURLIMAGEM, _CADASTROCOMPLETO, _ENDERECOSECUNDARIO, _TIPOEMPRESA, _PORTE;

	public static final String complemento = "complemento";
	public static final String site = "site";
	public static final String cnpj = "cnpj";
	public static final String origem = "origem";
	public static final String contatosprospecto = "contatosProspecto";
	public static final String arquivosenviados = "arquivosEnviados";
	public static final String arquivosrecebidos = "arquivosRecebidos";
	public static final String caminholocalimagem = "caminhoLocalImagem";
	public static final String caminhourlimagem = "caminhoUrlImagem";
	public static final String cadastrocompleto = "cadastrocompleto";
	public static final String enderecosecundario = "enderecoSecundario";
	public static final String tipoempresa = "tipoEmpresa";
	public static final String porte = "porte";
}