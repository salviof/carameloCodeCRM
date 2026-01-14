package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.CPEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.CONTEUDOHTMLPROCESSADO)
public class ValorLogicoEmailRecebidoConteudoHtmlProcessado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoConteudoHtmlProcessado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        setValorPorReflexao(getEmail().getCampoInstanciadoByNomeOuAnotacao(CPEmailRecebido.textocomurlimagememanexo).getValor());
        return getEmail().getCampoInstanciadoByNomeOuAnotacao(CPEmailRecebido.textocomurlimagememanexo).getValor();
    }

    public EmailRecebido getEmail() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
