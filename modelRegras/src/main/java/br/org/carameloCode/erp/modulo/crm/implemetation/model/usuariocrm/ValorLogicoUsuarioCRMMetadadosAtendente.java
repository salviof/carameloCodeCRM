package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.MetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValorLogicoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValoresLogicosUsuarioCRM;

@ValorLogicoUsuarioCRM(calculo = ValoresLogicosUsuarioCRM.METADADOSATENDENTE)
public class ValorLogicoUsuarioCRMMetadadosAtendente
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCRMMetadadosAtendente(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getUsuario().getMetadadosAtendente() == null) {
            getUsuario().setMetadadosAtendente(new MetadadoAtendente());
            getUsuario().getMetadadosAtendente().setUsuario(getUsuario());
        }

        return getUsuario().getMetadadosAtendente();
    }

    public UsuarioCRM getUsuario() {
        return (UsuarioCRM) getCampoInst().getObjetoDoAtributo();
    }
}
