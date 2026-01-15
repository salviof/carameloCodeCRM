package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNome;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.NOME)
public class ValorLogicoPessoaNome extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getProspecto().setNome(UtilCRCStringNome.gerarPrimeiraLetraMaiusculaPalavras(getProspecto().getNome()));
        return getProspecto().getNome();
    }

    public Pessoa getProspecto() {

        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
