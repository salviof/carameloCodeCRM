package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.ATIVO)
public class ValorLogicoChamadoClienteAtivo extends ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        switch (getChamado().getStatus().getFabStatus()) {
            case RASCUNHO:
            case FINALIZADO:
                getChamado().setAtivo(false);
                break;
            default:
                getChamado().setAtivo(true);
        }
        return getChamado().isAtivo();
    }

    public ChamadoCliente getChamado() {
        return (ChamadoCliente) getCampoInst().getObjetoDoAtributo();
    }
}
