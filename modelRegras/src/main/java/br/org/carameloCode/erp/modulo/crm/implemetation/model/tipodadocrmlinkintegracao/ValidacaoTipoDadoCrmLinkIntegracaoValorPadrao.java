package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrmlinkintegracao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValidadorTipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValidadoresTipoDadoCrmLinkIntegracao;

@ValidadorTipoDadoCrmLinkIntegracao(validador = ValidadoresTipoDadoCrmLinkIntegracao.VALORPADRAO)
public class ValidacaoTipoDadoCrmLinkIntegracaoValorPadrao extends ValidacaoGenerica<TipoDadoCrmLinkIntegracao> {

    public ValidacaoTipoDadoCrmLinkIntegracaoValorPadrao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList<>();
    }

    public TipoDadoCrmLinkIntegracao getTipoDadoCrmLinkIntegracao() {
        return getObjetoDoAtributo();
    }
}
