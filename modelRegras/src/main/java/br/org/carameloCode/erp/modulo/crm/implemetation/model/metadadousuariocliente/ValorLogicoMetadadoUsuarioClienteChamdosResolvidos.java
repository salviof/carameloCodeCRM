package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadousuariocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValorLogicoMetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValoresLogicosMetadadoUsuarioCliente;

@ValorLogicoMetadadoUsuarioCliente(calculo = ValoresLogicosMetadadoUsuarioCliente.CHAMDOSRESOLVIDOS)
public class ValorLogicoMetadadoUsuarioClienteChamdosResolvidos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoUsuarioClienteChamdosResolvidos(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getMetadadosClienteUsuario().getUsuario() != null) {
            if (isCacheAtivado()) {

                ativarCache(30);
            }

        }
        return getMetadadosClienteUsuario().getChamdosResolvidos();
    }

    public MetadadoUsuarioCliente getMetadadosClienteUsuario() {

        return (MetadadoUsuarioCliente) getCampoInst().getObjetoDoAtributo();

    }
}
