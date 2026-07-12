package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.tiponotificacao;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.tiponotificacao.ValidacaoTipoNotificacaoEstruturaEntidade;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.tipoNotificacao.TipoNotificacao;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacao.ValidadorTipoNotificacao;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.tiponotificacao.ValidadoresTipoNotificacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;

@ValidadorTipoNotificacao(validador = ValidadoresTipoNotificacao.ESTRUTURAENTIDADE)
public class ValidacaoExtErpcaramTipoNotificacaoEstruturaEntidade
		extends
			ValidacaoTipoNotificacaoEstruturaEntidade {

	public ValidacaoExtErpcaramTipoNotificacaoEstruturaEntidade(
			ItfCampoInstanciado pCampo) {
		super(pCampo);
	}

	@Override
	public List validar(java.lang.Object o) throws ErroValidacao {
		return super.validar(o);
	}

	public TipoNotificacao getTipoNotificacao() {
		return getObjetoDoAtributo();
	}
}