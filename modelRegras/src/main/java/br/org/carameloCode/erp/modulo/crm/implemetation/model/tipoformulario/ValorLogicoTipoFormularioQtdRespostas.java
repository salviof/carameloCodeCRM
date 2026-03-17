package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoformulario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.ValorLogicoTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.ValoresLogicosTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoFormulario(calculo = ValoresLogicosTipoFormulario.QTDRESPOSTAS)
public class ValorLogicoTipoFormularioQtdRespostas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoFormularioQtdRespostas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getTipoFormulario().setQtdRespostas(getTipoFormulario().getRespostas().size());
        return getTipoFormulario().getQtdRespostas();
    }

    public TipoFormulario getTipoFormulario() {
        return (TipoFormulario) getCampoInst().getObjetoRaizDoAtributo();
    }
}
