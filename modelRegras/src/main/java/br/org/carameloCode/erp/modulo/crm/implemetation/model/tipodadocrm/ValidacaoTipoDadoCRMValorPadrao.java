package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.ValidadorTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.ValidadoresTipoDadoCRM;

@ValidadorTipoDadoCRM(validador = ValidadoresTipoDadoCRM.VALORPADRAO)
public class ValidacaoTipoDadoCRMValorPadrao extends ValidacaoGenerica<TipoDadoCRM> {

    public ValidacaoTipoDadoCRMValorPadrao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList<>();
    }

    public TipoDadoCRM getTipoDadoCRM() {
        return getObjetoDoAtributo();
    }
}
