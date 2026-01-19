package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatvchamadarecebida;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRecebida;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarecebida.ValidadorTipoAtvChamadaRecebida;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvchamadarecebida.ValidadoresTipoAtvChamadaRecebida;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorTipoAtvChamadaRecebida(validador = ValidadoresTipoAtvChamadaRecebida.EXECUCAODIRETASEMRELATORIO)
public class ValidacaoTipoAtvChamadaRecebidaExecucaoDiretaSemRelatorio
		extends
			ValidacaoGenerica<TipoAtvChamadaRecebida> {

	public ValidacaoTipoAtvChamadaRecebidaExecucaoDiretaSemRelatorio(
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

	public TipoAtvChamadaRecebida getTipoAtvChamadaRecebida() {
		return getObjetoDoAtributo();
	}
}