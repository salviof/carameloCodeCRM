package br.org.carameloCode.erp.modulo.crm.implemetation.model.enderecoemailspan;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.span.EnderecoEmailSpan;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.enderecoemailspan.ValorLogicoEnderecoEmailSpan;
import br.org.carameloCode.erp.modulo.crm.api.model.enderecoemailspan.ValoresLogicosEnderecoEmailSpan;

@ValorLogicoEnderecoEmailSpan(calculo = ValoresLogicosEnderecoEmailSpan.BLOQUEARTODODOMINIO)
public class ValorLogicoEnderecoEmailSpanBloquearTodoDominio
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnderecoEmailSpanBloquearTodoDominio(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEndereco().getBloquearTodoDominio() == null) {
            if (getEndereco().getBloquearTodoDominio() == null) {
                if (getEndereco().getEmailRemetente().contains("gmail")
                        || getEndereco().getEmailRemetente().contains("hotmail")
                        || getEndereco().getEmailRemetente().contains("yahoo")) {
                    getEndereco().setBloquearTodoDominio(false);
                }
                if (UtilCRCStringFiltros.filtrarApenasNumeros(getEndereco().getEmailRemetente()).length() > 10) {
                    getEndereco().setBloquearTodoDominio(true);
                }
            }
        }
        return getEndereco().getBloquearTodoDominio();
    }

    public EnderecoEmailSpan getEndereco() {
        return (EnderecoEmailSpan) getCampoInst().getObjetoDoAtributo();
    }
}
