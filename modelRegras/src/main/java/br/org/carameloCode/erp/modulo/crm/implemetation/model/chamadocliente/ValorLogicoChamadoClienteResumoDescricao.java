package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;
import org.jsoup.Jsoup;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.RESUMODESCRICAO)
public class ValorLogicoChamadoClienteResumoDescricao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteResumoDescricao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        int maximoCaracteres = 50;
        if (getChamado().getDescricao() == null) {
            return getChamado().getResumoDescricao();
        }
        if (getChamado().getDescricao().length() < 50) {
            maximoCaracteres = getChamado().getDescricao().length();
        }
        String descricaoSemtags = getChamado().getDescricao();
        try {
            descricaoSemtags = Jsoup
                    .parse(getChamado().getDescricao()).text();
        } catch (Throwable t) {

        }
        if (descricaoSemtags.length() > maximoCaracteres) {
            getChamado().setResumoDescricao(descricaoSemtags.substring(0, maximoCaracteres - 1) + " ...");
        } else {
            getChamado().setResumoDescricao(descricaoSemtags);
        }
        return getChamado().getResumoDescricao();
    }

    public ChamadoCliente getChamado() {
        return (ChamadoCliente) getCampoInst().getObjetoDoAtributo();
    }

}
