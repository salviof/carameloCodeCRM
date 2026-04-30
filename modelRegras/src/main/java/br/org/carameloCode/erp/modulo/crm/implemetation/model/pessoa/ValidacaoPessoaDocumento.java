package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadorPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadoresPessoa;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorPessoa(validador = ValidadoresPessoa.DOCUMENTO)
public class ValidacaoPessoaDocumento extends ValidacaoGenerica<Pessoa> {

	public ValidacaoPessoaDocumento(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode.getServicoMensagemFireForget().enviarMsgErroAoUsuario(
				"A Validação do campo  Documento não foi implementada");
		return new ArrayList<>();
	}

	public Pessoa getPessoa() {
		return getObjetoDoAtributo();
	}
}