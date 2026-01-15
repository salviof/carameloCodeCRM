package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.QUANTIDADEEMAILSTROCADOS)
public class ValorLogicoPessoaQuantidadeEmailsTrocados
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaQuantidadeEmailsTrocados(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            setValorPorReflexao(getPessoa().getEmailsEnviadoRecebido().size());
            ativarCache(5000);
        }

        return getPessoa().getQuantidadeEmailsTrocados();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
