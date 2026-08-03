package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValorLogicoSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValoresLogicosSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoConfirmacaoEquipe(calculo = ValoresLogicosSolicitacaoConfirmacaoEquipe.DESCRICAOCONFIRMACAO)
public class ValorLogicoSolicitacaoConfirmacaoEquipeDescricaoConfirmacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoConfirmacaoEquipeDescricaoConfirmacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        StringBuilder pretexto = new StringBuilder();
        if (!getSolicitacaoConfirmacaoEquipe().isFoiFinalizada()) {
            if (getSolicitacaoConfirmacaoEquipe().getTipoRespostaSelecionada() != null) {
                pretexto.append(getSolicitacaoConfirmacaoEquipe().getTipoRespostaSelecionada().getNome());
                pretexto.append("; ");
                String texto;
                if (getSolicitacaoConfirmacaoEquipe().getDescricaoConfirmacao() != null) {
                    if (!getSolicitacaoConfirmacaoEquipe().getDescricaoConfirmacao().contains("; ")) {
                        texto = pretexto.toString() + getSolicitacaoConfirmacaoEquipe().getDescricaoConfirmacao();
                    } else {
                        texto = getSolicitacaoConfirmacaoEquipe().getDescricaoConfirmacao();
                    }
                } else {
                    texto = pretexto.toString();
                }
                getSolicitacaoConfirmacaoEquipe().setDescricaoConfirmacao(texto);
            }
        }
        return getSolicitacaoConfirmacaoEquipe().getDescricaoConfirmacao();
    }

    public SolicitacaoConfirmacaoEquipe getSolicitacaoConfirmacaoEquipe() {
        return (SolicitacaoConfirmacaoEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
