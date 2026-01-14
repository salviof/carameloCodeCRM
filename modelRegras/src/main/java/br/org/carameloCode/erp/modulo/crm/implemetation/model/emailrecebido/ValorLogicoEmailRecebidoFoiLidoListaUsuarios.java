package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import java.util.stream.Collectors;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.FOILIDOLISTAUSUARIOS)
public class ValorLogicoEmailRecebidoFoiLidoListaUsuarios
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoFoiLidoListaUsuarios(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valorConfigurado = false;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!valorConfigurado) {

            List<UsuarioCRM> usuarios = getEmailRecebido().getLogsLeituraMailRecebido().stream().map(usr -> usr.getUsuario()).distinct().collect(Collectors.toList());
            getEmailRecebido().setFoiLidoListaUsuarios(usuarios);
            valorConfigurado = true;
        }
        return getEmailRecebido().getFoiLidoListaUsuarios();
    }

    public EmailRecebido getEmailRecebido() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
