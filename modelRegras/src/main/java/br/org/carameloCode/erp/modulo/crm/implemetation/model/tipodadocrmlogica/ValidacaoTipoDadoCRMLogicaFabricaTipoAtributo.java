package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrmlogica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRMLogica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlogica.ValidadorTipoDadoCRMLogica;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlogica.ValidadoresTipoDadoCRMLogica;

@ValidadorTipoDadoCRMLogica(validador = ValidadoresTipoDadoCRMLogica.FABRICATIPOATRIBUTO)
public class ValidacaoTipoDadoCRMLogicaFabricaTipoAtributo extends ValidacaoGenerica<TipoDadoCRMLogica> {

    public ValidacaoTipoDadoCRMLogicaFabricaTipoAtributo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList();
    }

    public TipoDadoCRMLogica getTipoDadoCRMLogica() {
        return getObjetoDoAtributo();
    }
}
