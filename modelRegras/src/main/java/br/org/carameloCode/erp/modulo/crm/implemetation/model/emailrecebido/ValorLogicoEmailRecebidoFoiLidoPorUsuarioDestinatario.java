package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.FOILIDOPORUSUARIODESTINATARIO)
public class ValorLogicoEmailRecebidoFoiLidoPorUsuarioDestinatario
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoFoiLidoPorUsuarioDestinatario(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEmailResposta().isFoiLidoPorUsuarioDestinatario()) {
            return true;
        } else {
            final String email = getEmailResposta().getCaixaPostal().getUsuarioRecepcao();
            boolean leu = getEmailResposta().getLogsLeituraMailRecebido().stream().filter(log -> log.getUsuario().getCaixaPostalPrincipal() != null && log.getUsuario().getCaixaPostalPrincipal().getEmail().equals(email)).findFirst()
                    .isPresent();
            getEmailResposta().setFoiLidoPorUsuarioDestinatario(leu);
        }
        return getEmailResposta().isFoiLidoPorUsuarioDestinatario();
    }

    public EmailRecebido getEmailResposta() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
