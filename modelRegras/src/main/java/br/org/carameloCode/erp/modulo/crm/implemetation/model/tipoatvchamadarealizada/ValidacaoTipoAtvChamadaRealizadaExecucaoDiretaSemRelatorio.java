package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatvchamadarealizada;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRealizada;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarealizada.ValidadorTipoAtvChamadaRealizada;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarealizada.ValidadoresTipoAtvChamadaRealizada;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorTipoAtvChamadaRealizada(validador = ValidadoresTipoAtvChamadaRealizada.EXECUCAODIRETASEMRELATORIO)
public class ValidacaoTipoAtvChamadaRealizadaExecucaoDiretaSemRelatorio
		extends
			ValidacaoGenerica<TipoAtvChamadaRealizada> {

	public ValidacaoTipoAtvChamadaRealizadaExecucaoDiretaSemRelatorio(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		CarameloCode
				.getServicoMensagemFireForget()
				.enviarMsgErroAoUsuario(
						"A Validação do campo  Executar direto sem Relatório? não foi implementada");
		return new ArrayList<>();
	}

	public TipoAtvChamadaRealizada getTipoAtvChamadaRealizada() {
		return getObjetoDoAtributo();
	}
}