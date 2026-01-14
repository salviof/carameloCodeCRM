package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estatisticas.EstatisticasDoUsuario;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValorLogicoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValoresLogicosUsuarioCRM;

@ValorLogicoUsuarioCRM(calculo = ValoresLogicosUsuarioCRM.ESTATISTICAS)
public class ValorLogicoUsuarioCRMEstatisticas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCRMEstatisticas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        UsuarioCRM usuario = getUsuario();

        if (usuario.getEstatisticas() == null) {
            usuario.setEstatisticas(new EstatisticasDoUsuario(usuario));
        }
        return usuario.getEstatisticas();
    }

    public UsuarioCRM getUsuario() {
        return (UsuarioCRM) getCampoInst().getObjetoRaizDoAtributo();
    }

}
