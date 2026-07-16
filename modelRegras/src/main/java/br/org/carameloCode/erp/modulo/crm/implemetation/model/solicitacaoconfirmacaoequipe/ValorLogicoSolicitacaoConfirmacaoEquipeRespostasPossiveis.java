package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoconfirmacaoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValorLogicoSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoconfirmacaoequipe.ValoresLogicosSolicitacaoConfirmacaoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoConfirmacaoEquipe;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.ArrayList;
import java.util.List;

@ValorLogicoSolicitacaoConfirmacaoEquipe(calculo = ValoresLogicosSolicitacaoConfirmacaoEquipe.RESPOSTASPOSSIVEIS)
public class ValorLogicoSolicitacaoConfirmacaoEquipeRespostasPossiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoConfirmacaoEquipeRespostasPossiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getSolicitacaoConfirmacaoEquipe().getTipoComunicacao() != null) {
            List<ComoTipoRespostaComunicacao> respostas = getSolicitacaoConfirmacaoEquipe().getTipoComunicacao().getTipoRespostasPossiveis();
            getSolicitacaoConfirmacaoEquipe().setRespostasPossiveis((List) respostas);
        } else {
            getSolicitacaoConfirmacaoEquipe().setRespostasPossiveis(new ArrayList<>());
        }
        return getSolicitacaoConfirmacaoEquipe().getRespostasPossiveis();
    }

    public SolicitacaoConfirmacaoEquipe getSolicitacaoConfirmacaoEquipe() {
        return (SolicitacaoConfirmacaoEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
