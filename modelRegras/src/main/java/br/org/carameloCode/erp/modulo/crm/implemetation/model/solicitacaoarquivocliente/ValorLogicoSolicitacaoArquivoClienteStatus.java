package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoarquivocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente.ValorLogicoSolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente.ValoresLogicosSolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacao.ValorLogicoSolicitacaoStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoArquivoCliente(calculo = ValoresLogicosSolicitacaoArquivoCliente.STATUS)
public class ValorLogicoSolicitacaoArquivoClienteStatus
        extends
        ValorLogicoSolicitacaoStatus {

    public ValorLogicoSolicitacaoArquivoClienteStatus(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public SolicitacaoArquivoCliente getSolicitacaoArquivoCliente() {
        return (SolicitacaoArquivoCliente) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
