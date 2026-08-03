package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiponotificacaocrm;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadorTiponotificacaoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tiponotificacaocrm.ValidadoresTiponotificacaoCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorTiponotificacaoCRM(validador = ValidadoresTiponotificacaoCRM.ACAORESPOSTAPERSONALIZADA)
public class ValidacaoTiponotificacaoCRMAcaoRespostaPersonalizada
		extends
			ValidacaoGenerica<TiponotificacaoCRM> {

	public ValidacaoTiponotificacaoCRMAcaoRespostaPersonalizada(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode
				.getServicoMensagemFireForget()
				.enviarMsgErroAoUsuario(
						"A Validação do campo  Gatilho após confirmação de entrega não foi implementada");
		return new ArrayList<>();
	}

	public TiponotificacaoCRM getTiponotificacaoCRM() {
		return getObjetoDoAtributo();
	}
}