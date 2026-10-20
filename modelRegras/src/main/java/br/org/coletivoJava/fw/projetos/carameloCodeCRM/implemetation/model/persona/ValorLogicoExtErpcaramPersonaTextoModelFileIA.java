package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.persona;

import br.org.carameloCode.erp.modulo.ia.implemetation.model.persona.ValorLogicoPersonaTextoModelFileIA;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.persona.ValorLogicoPersona;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.persona.ValoresLogicosPersona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoPersona(calculo = ValoresLogicosPersona.TEXTOMODELFILEIA)
public class ValorLogicoExtErpcaramPersonaTextoModelFileIA
        extends
        ValorLogicoPersonaTextoModelFileIA {

    public ValorLogicoExtErpcaramPersonaTextoModelFileIA(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
