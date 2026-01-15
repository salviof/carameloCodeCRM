package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.TITULO)
public class ValorLogicoChamadoClienteTitulo extends ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteTitulo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getChamado().getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())) {
            if (getChamado().getTipoChamado() == null) {
                getChamado().setTitulo("Chamado em desenvolvimento");
            } else {
                getChamado().setTitulo("Rascunho do chamado indefinido");
            }

        } else {
            if (getChamado().getTipoChamado() != null) {
                getChamado().setTitulo(getChamado().getTipoChamado().getNome() + " No" + getChamado().getId());
            } else {
                getChamado().setTitulo("Rascunho do chamado indefinido");
            }

        }
        return getChamado().getTitulo();
    }

    public ChamadoCliente getChamado() {
        return (ChamadoCliente) getCampoInst().getObjetoDoAtributo();
    }
}
