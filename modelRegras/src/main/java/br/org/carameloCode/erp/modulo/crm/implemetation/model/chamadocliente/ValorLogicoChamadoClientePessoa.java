package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.PESSOA)
public class ValorLogicoChamadoClientePessoa extends ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClientePessoa(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getChamado().getPessoa() == null) {
            Pessoa pessoa = getChamado().getUsuarioCliente().getRepresentanteLegal();
            getChamado().setPessoa(pessoa);
        }
        return getChamado().getPessoa();
    }

    public ChamadoCliente getChamado() {
        return (ChamadoCliente) getCampoInst().getObjetoDoAtributo();
    }

}
