package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatualizacaoarquivoequipe;

import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValorLogicoSolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoNotificacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoAtualizacaoArquivoEquipe(calculo = ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe.NOTIFICACAO)
public class ValorLogicoSolicitacaoAtualizacaoArquivoEquipeNotificacao
        extends
        ValorLogicoSolicitacaoNotificacao {

    public ValorLogicoSolicitacaoAtualizacaoArquivoEquipeNotificacao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoAtualizacaoArquivoEquipe getSolicitacaoAtualizacaoArquivoEquipe() {
        return (SolicitacaoAtualizacaoArquivoEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
