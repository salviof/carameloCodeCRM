package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaoequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoAcessoCard;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValorLogicoSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValoresLogicosSolicitacaoConfirmacaoEquipe;

@ValorLogicoSolicitacaoConfirmacaoEquipe(calculo = ValoresLogicosSolicitacaoConfirmacaoEquipe.NOME)
public class ValorLogicoSolicitacaoConfirmacaoEquipeNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoConfirmacaoEquipeNome(ItfCampoInstanciado pCampo) {
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
