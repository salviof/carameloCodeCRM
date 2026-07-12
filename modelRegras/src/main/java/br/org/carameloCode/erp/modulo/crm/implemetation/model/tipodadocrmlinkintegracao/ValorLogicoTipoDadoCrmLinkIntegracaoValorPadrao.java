package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrmlinkintegracao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValorLogicoTipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrmlinkintegracao.ValoresLogicosTipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoTipoDadoCrmLinkIntegracao(calculo = ValoresLogicosTipoDadoCrmLinkIntegracao.VALORPADRAO)
public class ValorLogicoTipoDadoCrmLinkIntegracaoValorPadrao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoDadoCrmLinkIntegracaoValorPadrao(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getTipoDadoCrmLinkIntegracao().getNomeClasseLogica() != null && getTipoDadoCrmLinkIntegracao().getNomeClasseLogica().isEmpty()) {
            String enderecoClasse = "org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao.ValorLogicoLink" + getTipoDadoCrmLinkIntegracao().getNomeClasseLogica();
            getTipoDadoCrmLinkIntegracao().setValorPadrao(enderecoClasse + ".class");
        }
        return getTipoDadoCrmLinkIntegracao().getValorPadrao();
    }

    public TipoDadoCrmLinkIntegracao getTipoDadoCrmLinkIntegracao() {
        return (TipoDadoCrmLinkIntegracao) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
