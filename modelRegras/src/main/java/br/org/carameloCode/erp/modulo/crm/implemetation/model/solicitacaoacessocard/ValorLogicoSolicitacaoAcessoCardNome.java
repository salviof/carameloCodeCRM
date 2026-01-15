package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoacessocard;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValorLogicoSolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoacessocard.ValoresLogicosSolicitacaoAcessoCard;

@ValorLogicoSolicitacaoAcessoCard(calculo = ValoresLogicosSolicitacaoAcessoCard.NOME)
public class ValorLogicoSolicitacaoAcessoCardNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoAcessoCardNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        getSolicitacao().setNome(getSolicitacao().getUsuarioSolicitante().getNome() + " solicita acesso ao Cartão " + getSolicitacao().getPessoa().getNome());
        return getSolicitacao().getNome();
    }

    public SolicitacaoAcessoCard getSolicitacao() {
        return (SolicitacaoAcessoCard) getCampoInst().getObjetoDoAtributo();
    }

}
