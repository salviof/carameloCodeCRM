package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadoia;

import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValidadorTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadoia.ValidadoresTipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciadoDInamico.UtilCRCReflexaoCampoLogicoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;

@ValidadorTipoDadoIA(validador = ValidadoresTipoDadoIA.VALORPADRAO)
public class ValidacaoTipoDadoIAValorPadrao
        extends ValidacaoGenerica<TipoDadoIA> {

    public ValidacaoTipoDadoIAValorPadrao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (pValor == null) {
            return new ArrayList<>();
        }
        String novoValor = (String) pValor;
        if (novoValor.endsWith(".class") || novoValor.startsWith("ValorLogicoDD")) {
            DadoCRM dado = new DadoCRM(new Pessoa());
            dado.setTipoDadoCRM(getTipoDadoCRMLogica());
            ItfCalculoValorLogicoAtributoObjeto logica = UtilCRCReflexaoCampoLogicoDinamico.getLogicaValorCampo(novoValor, dado.getCampoInstanciado());
            if (logica == null) {
                throw new ErroValidacao("Os Algorítimos para " + pValor + " não foram encontrados no sistema");
            }
        } else {
            throw new ErroValidacao("Utilize ValorLogicoDD[NomeLogica] ou [NomeLogica.class], consulte o administrador para conhecer os campos lógicos diposníveis");
        }
        return new ArrayList<>();
    }

    public TipoDadoIA getTipoDadoCRMLogica() {
        return getObjetoDoAtributo();
    }

}
