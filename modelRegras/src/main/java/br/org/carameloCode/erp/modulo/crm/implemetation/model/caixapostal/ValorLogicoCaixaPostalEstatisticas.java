package br.org.carameloCode.erp.modulo.crm.implemetation.model.caixapostal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostalEstatisticas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostal.ValorLogicoCaixaPostal;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostal.ValoresLogicosCaixaPostal;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@ValorLogicoCaixaPostal(calculo = ValoresLogicosCaixaPostal.ESTATISTICAS)
public class ValorLogicoCaixaPostalEstatisticas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoCaixaPostalEstatisticas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getCaixaPostal().getEstatisticas() == null) {
            try {
                CaixaPostalEstatisticas estatisticas = new CaixaPostalEstatisticas();
                estatisticas.prepararNovoObjeto(getCaixaPostal());
                getCaixaPostal().setEstatisticas(estatisticas);
            } catch (ErroPreparandoObjeto ex) {
                Logger.getLogger(ValorLogicoCaixaPostalEstatisticas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return getCaixaPostal().getEstatisticas();
    }

    public CaixaPostal getCaixaPostal() {
        return (CaixaPostal) getCampoInst().getObjetoDoAtributo();
    }

}
