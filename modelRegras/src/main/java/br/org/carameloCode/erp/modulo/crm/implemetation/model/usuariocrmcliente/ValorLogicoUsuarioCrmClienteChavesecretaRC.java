package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrmcliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCCriptoAES;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringGerador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValorLogicoUsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrmcliente.ValoresLogicosUsuarioCrmCliente;

@ValorLogicoUsuarioCrmCliente(calculo = ValoresLogicosUsuarioCrmCliente.CHAVESECRETARC)
public class ValorLogicoUsuarioCrmClienteChavesecretaRC
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCrmClienteChavesecretaRC(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (UtilCRCStringValidador.isNuloOuEmbranco(getUsuario().getChavesecretaRC())) {
            String chaveSecreta = SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.CHAVE_SECRETA_RC_USUARIO_CLIENTE);
            String senha = UtilCRCStringGerador.getStringRandomicaUUID();
            String valorCriptografado = UtilCRCCriptoAES.encrypt(senha, chaveSecreta);
            getUsuario().setChavesecretaRC(valorCriptografado);
        }
        return getUsuario().getChavesecretaRC();
    }

    public UsuarioCrmCliente getUsuario() {
        return (UsuarioCrmCliente) getCampoInst().getObjetoDoAtributo();
    }

}
