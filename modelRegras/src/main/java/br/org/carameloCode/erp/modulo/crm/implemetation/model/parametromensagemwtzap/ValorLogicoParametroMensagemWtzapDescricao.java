package br.org.carameloCode.erp.modulo.crm.implemetation.model.parametromensagemwtzap;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.ParametroMensagemWtzap;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap.ValorLogicoParametroMensagemWtzap;
import br.org.carameloCode.erp.modulo.crm.api.model.parametromensagemwtzap.ValoresLogicosParametroMensagemWtzap;

@ValorLogicoParametroMensagemWtzap(calculo = ValoresLogicosParametroMensagemWtzap.DESCRICAO)
public class ValorLogicoParametroMensagemWtzapDescricao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoParametroMensagemWtzapDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        StringBuilder str = new StringBuilder();
        if (getParametro().getTipoDado() != null) {
            if (getParametro().getDescricao() != null) {
                if (getParametro().getDescricao().contains(".")) {
                    return getParametro().getDescricao();
                }
            }
            str.append("Parametro tipo:");
            str.append(getParametro().getTipoDado().getNome());
            str.append(" de ");
            str.append(getParametro().getTipoMensagem().getNome());
            getParametro().setDescricao(str.toString());
        }
        return getParametro().getDescricao();
    }

    public ParametroMensagemWtzap getParametro() {
        return (ParametroMensagemWtzap) getCampoInst().getObjetoDoAtributo();
    }

}
