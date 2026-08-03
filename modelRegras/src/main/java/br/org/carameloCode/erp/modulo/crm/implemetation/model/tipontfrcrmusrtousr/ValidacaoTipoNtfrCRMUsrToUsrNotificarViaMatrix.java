package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipontfrcrmusrtousr;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TipoNtfrCRMUsrToUsr;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfrcrmusrtousr.ValidadorTipoNtfrCRMUsrToUsr;
import br.org.carameloCode.erp.modulo.crm.api.model.tipontfrcrmusrtousr.ValidadoresTipoNtfrCRMUsrToUsr;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorTipoNtfrCRMUsrToUsr(validador = ValidadoresTipoNtfrCRMUsrToUsr.NOTIFICARVIAMATRIX)
public class ValidacaoTipoNtfrCRMUsrToUsrNotificarViaMatrix
		extends
			ValidacaoGenerica<TipoNtfrCRMUsrToUsr> {

	public ValidacaoTipoNtfrCRMUsrToUsrNotificarViaMatrix(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode.getServicoMensagemFireForget().enviarMsgErroAoUsuario(
				"A Validação do campo  Via Matrix não foi implementada");
		return new ArrayList<>();
	}

	public TipoNtfrCRMUsrToUsr getTipoNtfrCRMUsrToUsr() {
		return getObjetoDoAtributo();
	}
}