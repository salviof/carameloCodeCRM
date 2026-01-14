package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoarquivocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente.ValorLogicoSolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente.ValoresLogicosSolicitacaoArquivoCliente;

@ValorLogicoSolicitacaoArquivoCliente(calculo = ValoresLogicosSolicitacaoArquivoCliente.LINKCONVITE)
public class ValorLogicoSolicitacaoArquivoClienteLinkConvite
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoArquivoClienteLinkConvite(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valordefinido;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valordefinido) {
            String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_NOVO_ARQUIVO_EQUIPE, getSolicitacao());
            getSolicitacao().setLinkConvite(url);
            valordefinido = true;
        }
        return getSolicitacao().getLinkConvite();
    }

    public Solicitacao getSolicitacao() {
        return (Solicitacao) getCampoInst().getObjetoDoAtributo();
    }
}
