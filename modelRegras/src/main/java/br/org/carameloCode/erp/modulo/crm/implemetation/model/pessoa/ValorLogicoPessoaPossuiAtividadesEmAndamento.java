package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.POSSUIATIVIDADESEMANDAMENTO)
public class ValorLogicoPessoaPossuiAtividadesEmAndamento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaPossuiAtividadesEmAndamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPessoa().getId() == null) {
            return false;
        }
        boolean temAtividadeEmAndamento = (getPessoa().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.ultimaatividadeemandamento).getValor() != null);
        getPessoa().setPossuiAtividadesEmAndamento(temAtividadeEmAndamento);
        return getPessoa().isPossuiAtividadesEmAndamento();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
