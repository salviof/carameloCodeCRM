package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoformulario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.ValorLogicoTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.ValoresLogicosTipoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoFormulario(calculo = ValoresLogicosTipoFormulario.NOME)
public class ValorLogicoTipoFormularioNome extends ValorLogicoCalculoGenerico {

    public ValorLogicoTipoFormularioNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getTipoForm().getNome();
    }

    public TipoFormulario getTipoForm() {
        return (TipoFormulario) getCampoInst().getObjetoRaizDoAtributo();
    }

}
