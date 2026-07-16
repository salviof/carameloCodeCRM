package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.notificacaosb;

import br.org.carameloCode.erp.modulo.notificacao.implemetation.model.notificacaosb.ValorLogicoNotificacaoSBConteudoHtml;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.notificacaosb.ValorLogicoNotificacaoSB;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.notificacaosb.ValoresLogicosNotificacaoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoNotificacaoSB(calculo = ValoresLogicosNotificacaoSB.CONTEUDOHTML)
public class ValorLogicoExtErpcaramNotificacaoSBConteudoHtml
        extends
        ValorLogicoNotificacaoSBConteudoHtml {

    public ValorLogicoExtErpcaramNotificacaoSBConteudoHtml(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return super.getValor(pEntidade);
    }
}
