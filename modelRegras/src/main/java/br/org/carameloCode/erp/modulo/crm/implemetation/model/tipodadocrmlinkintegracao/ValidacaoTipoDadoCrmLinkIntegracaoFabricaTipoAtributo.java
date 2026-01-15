package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrmlinkintegracao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValidadorTipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValidadoresTipoDadoCrmLinkIntegracao;

@ValidadorTipoDadoCrmLinkIntegracao(validador = ValidadoresTipoDadoCrmLinkIntegracao.FABRICATIPOATRIBUTO)
public class ValidacaoTipoDadoCrmLinkIntegracaoFabricaTipoAtributo extends ValidacaoGenerica<TipoDadoCrmLinkIntegracao> {

    public ValidacaoTipoDadoCrmLinkIntegracaoFabricaTipoAtributo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList();
    }

    public TipoDadoCrmLinkIntegracao getTipoDadoCrmLinkIntegracao() {
        return getObjetoDoAtributo();
    }
}
