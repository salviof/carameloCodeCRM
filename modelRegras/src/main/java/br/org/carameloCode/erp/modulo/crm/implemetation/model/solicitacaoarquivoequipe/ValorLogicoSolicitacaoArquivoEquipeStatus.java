package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoarquivoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe.ValorLogicoSolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe.ValoresLogicosSolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoArquivoEquipe(calculo = ValoresLogicosSolicitacaoArquivoEquipe.STATUS)
public class ValorLogicoSolicitacaoArquivoEquipeStatus
        extends
        ValorLogicoSolicitacaoStatus {

    public ValorLogicoSolicitacaoArquivoEquipeStatus(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoArquivoEquipe getSolicitacaoArquivoEquipe() {
        return (SolicitacaoArquivoEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
