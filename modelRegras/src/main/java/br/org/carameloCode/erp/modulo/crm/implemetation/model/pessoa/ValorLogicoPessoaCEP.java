package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.CEP)
public class ValorLogicoPessoaCEP extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaCEP(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getPessoa().getLocalizacao() != null) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getLocalizacao().getCep())) {
                getPessoa().setCEP(getPessoa().getLocalizacao().getCep());
            }
        }

        return getPessoa().getCEP();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
