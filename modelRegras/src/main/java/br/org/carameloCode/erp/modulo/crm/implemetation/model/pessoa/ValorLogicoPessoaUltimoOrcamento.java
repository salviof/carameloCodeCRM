package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.ULTIMOORCAMENTO)
public class ValorLogicoPessoaUltimoOrcamento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaUltimoOrcamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!getPessoa().getOrcamentos().isEmpty()) {
            if (getPessoa().getUltimoOrcamento() == null) {
                getPessoa().setUltimoOrcamento(getPessoa().getOrcamentos().get(0));
            }
        } else {
            if (getPessoa().getUltimoOrcamento() == null) {
                try {
                    getPessoa().setUltimoOrcamento(new Orcamento());
                    getPessoa().getUltimoOrcamento().prepararNovoObjeto(getPessoa());
                } catch (ErroPreparandoObjeto ex) {
                    getPessoa().setUltimoOrcamento(null);
                }
            }
        }

        return getPessoa().getUltimoOrcamento();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
