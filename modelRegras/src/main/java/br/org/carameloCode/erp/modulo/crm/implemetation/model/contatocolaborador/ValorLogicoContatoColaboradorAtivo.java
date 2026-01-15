package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatocolaborador;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValorLogicoContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValoresLogicosContatoColaborador;

@ValorLogicoContatoColaborador(calculo = ValoresLogicosContatoColaborador.ATIVO)
public class ValorLogicoContatoColaboradorAtivo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoContatoColaboradorAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getContatoColaborador().getUsuario() == null) {
            getContatoColaborador().setAtivo(false);
        } else {
            getContatoColaborador().setAtivo(getContatoColaborador().getUsuario().isAtivo());
        }
        return getContatoColaborador().isAtivo();
    }

    public ContatoColaborador getContatoColaborador() {
        return (ContatoColaborador) getCampoInst().getObjetoDoAtributo();
    }
}
