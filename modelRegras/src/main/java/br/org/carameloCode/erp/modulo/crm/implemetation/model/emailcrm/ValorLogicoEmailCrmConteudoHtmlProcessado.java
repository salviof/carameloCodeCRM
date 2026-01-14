package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailcrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.HashMap;
import java.util.Map;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValorLogicoEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.ValoresLogicosEmailCrm;

@ValorLogicoEmailCrm(calculo = ValoresLogicosEmailCrm.CONTEUDOHTMLPROCESSADO)
public class ValorLogicoEmailCrmConteudoHtmlProcessado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailCrmConteudoHtmlProcessado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        Map<String, ArquivoAnexado> mapaArquivosRecebidosAnaxado = new HashMap<>();

        //getEmail().getArquivos().stream().filter(arquivo -> (arquivo instanceof ArquivoAnexadoEmailRecebido) == true).forEach(arq -> arq.getComoArquivoAnexadoEmailRecebido());
        return getEmail().getTexto();
    }

    public EmailCrm getEmail() {
        return (EmailCrm) getCampoInst().getObjetoDoAtributo();
    }

}
