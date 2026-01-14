package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValorLogicoUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValoresLogicosUsuarioCrmCliente;

@ValorLogicoUsuarioCrmCliente(calculo = ValoresLogicosUsuarioCrmCliente.METADADOS)
public class ValorLogicoUsuarioCrmClienteMetadados
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCrmClienteMetadados(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getUsuarioCrmCliente().getMetadados() == null) {
            getUsuarioCrmCliente().setMetadados(new MetadadoUsuarioCliente());
            getUsuarioCrmCliente().getMetadados().setUsuario(getUsuarioCrmCliente());

        }
        return getUsuarioCrmCliente().getMetadados();
    }

    public UsuarioCrmCliente getUsuarioCrmCliente() {
        return (UsuarioCrmCliente) getCampoInst().getObjetoDoAtributo();
    }
}
