package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValorLogicoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValoresLogicosUsuarioCRM;

@ValorLogicoUsuarioCRM(calculo = ValoresLogicosUsuarioCRM.CAIXAPOSTALPRINCIPAL)
public class ValorLogicoUsuarioCRMCaixaPostalPrincipal
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCRMCaixaPostalPrincipal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getUsuario().getCaixaPostalPrincipal() == null) {

            getUsuario().setCaixaPostalPrincipal(new CaixaPostal());
            getUsuario().getCaixaPostalPrincipal().setUsuarioRecepcao(getUsuario().getEmail());
            getUsuario().getCaixaPostalPrincipal().setUsuarioSMTP(getUsuario().getEmail());
        }
        return getUsuario().getCaixaPostalPrincipal();
    }

    public UsuarioCRM getUsuario() {
        return (UsuarioCRM) getCampoInst().getObjetoRaizDoAtributo();
    }
}
