package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValorLogicoUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValoresLogicosUsuarioCrmCliente;

@ValorLogicoUsuarioCrmCliente(calculo = ValoresLogicosUsuarioCrmCliente.CONTATOVINCULADO)
public class ValorLogicoUsuarioCrmClienteContatoVinculado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCrmClienteContatoVinculado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        return getUsuarioCliente().getContatoClienteVinculado();
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return (UsuarioCrmCliente) getCampoInst().getObjetoRaizDoAtributo();
    }
}
