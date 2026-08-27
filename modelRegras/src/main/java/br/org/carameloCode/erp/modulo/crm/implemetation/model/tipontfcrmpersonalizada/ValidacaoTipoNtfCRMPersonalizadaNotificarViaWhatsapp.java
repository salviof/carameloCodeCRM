package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipontfcrmpersonalizada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TipoNtfCRMPersonalizada;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfcrmpersonalizada.ValidadorTipoNtfCRMPersonalizada;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfcrmpersonalizada.ValidadoresTipoNtfCRMPersonalizada;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorTipoNtfCRMPersonalizada(validador = ValidadoresTipoNtfCRMPersonalizada.NOTIFICARVIAWHATSAPP)
public class ValidacaoTipoNtfCRMPersonalizadaNotificarViaWhatsapp
		extends
			ValidacaoGenerica<TipoNtfCRMPersonalizada> {

	public ValidacaoTipoNtfCRMPersonalizadaNotificarViaWhatsapp(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode.getServicoMensagemFireForget().enviarMsgErroAoUsuario(
				"A Validação do campo  Via Msg Whatsapp não foi implementada");
		return new ArrayList<>();
	}

	public TipoNtfCRMPersonalizada getTipoNtfCRMPersonalizada() {
		return getObjetoDoAtributo();
	}
}