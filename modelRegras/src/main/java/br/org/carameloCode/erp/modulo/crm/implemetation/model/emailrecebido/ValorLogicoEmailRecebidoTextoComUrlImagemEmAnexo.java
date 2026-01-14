package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexadoEmailEmConteudo.ArquivoAnexadoEmailEmConteudo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmailObjetos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.HashMap;
import java.util.Map;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.TEXTOCOMURLIMAGEMEMANEXO)
public class ValorLogicoEmailRecebidoTextoComUrlImagemEmAnexo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoTextoComUrlImagemEmAnexo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEmailRecebido().getTextoComUrlImagemEmAnexo() == null) {
            if (getEmailRecebido().getArquivosRecebidos().isEmpty()) {
                getEmailRecebido().setTextoComUrlImagemEmAnexo(getEmailRecebido().getTexto());
                return getEmailRecebido().getTextoComUrlImagemEmAnexo();
            } else {
                Map<String, String> mapaUrls = new HashMap();
                getEmailRecebido().getArquivosRecebidos().stream().forEach(arq -> {
                    if (arq instanceof ArquivoAnexadoEmailEmConteudo) {
                        mapaUrls.put(((ArquivoAnexadoEmailEmConteudo) arq).getCid(), arq.getCPinst("arquivo").getComoArquivoDeEntidade().getLinkAbrirArquivo());
                    }

                });
                getEmailRecebido().setTextoComUrlImagemEmAnexo(UtilCRCEmailObjetos.substituirCidPorUrl(getEmailRecebido().getTexto(), mapaUrls));
                return getEmailRecebido().getTextoComUrlImagemEmAnexo();
            }
        }

        return getEmailRecebido().getTextoComUrlImagemEmAnexo();
    }

    public EmailRecebido getEmailRecebido() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
