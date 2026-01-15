package br.org.carameloCode.erp.modulo.crm.implemetation.model.enderecoemailspan;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.span.EnderecoEmailSpan;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.enderecoemailspan.ValorLogicoEnderecoEmailSpan;
import br.org.carameloCode.erp.modulo.crm.api.model.enderecoemailspan.ValoresLogicosEnderecoEmailSpan;

@ValorLogicoEnderecoEmailSpan(calculo = ValoresLogicosEnderecoEmailSpan.DOMINIOEMAIL)
public class ValorLogicoEnderecoEmailSpanDominioEmail
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnderecoEmailSpanDominioEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEndereco().getDominioEmail() == null) {
            getEndereco().setDominioEmail(getEndereco().getEmailRemetente()
                    .substring(getEndereco().getEmailRemetente().indexOf("@"), getEndereco().getEmailRemetente().length()));
        }
        return getEndereco().getDominioEmail();
    }

    public EnderecoEmailSpan getEndereco() {
        return (EnderecoEmailSpan) getCampoInst().getObjetoDoAtributo();
    }
}
