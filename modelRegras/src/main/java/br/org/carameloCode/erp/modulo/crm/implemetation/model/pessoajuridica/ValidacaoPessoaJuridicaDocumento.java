package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadorPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadoresPessoaJuridica;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorPessoaJuridica(validador = ValidadoresPessoaJuridica.DOCUMENTO)
public class ValidacaoPessoaJuridicaDocumento
		extends
			ValidacaoGenerica<PessoaJuridica> {

	public ValidacaoPessoaJuridicaDocumento(ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode.getServicoMensagemFireForget().enviarMsgErroAoUsuario(
				"A Validação do campo  Documento não foi implementada");
		return new ArrayList<>();
	}

	public PessoaJuridica getPessoaJuridica() {
		return getObjetoDoAtributo();
	}
}