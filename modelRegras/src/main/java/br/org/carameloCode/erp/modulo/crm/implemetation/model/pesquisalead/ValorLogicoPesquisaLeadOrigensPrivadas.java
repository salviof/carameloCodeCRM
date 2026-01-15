package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.ORIGENSPRIVADAS)
public class ValorLogicoPesquisaLeadOrigensPrivadas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadOrigensPrivadas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private UsuarioCRM ultimoUsuarioUtilizado = null;

    @Override
    public Object getValor(Object... pEntidade) {

        if (ultimoUsuarioUtilizado == null || !ultimoUsuarioUtilizado.equals(getPesquisaLead().getUsuario())) {
            EntityManager em = UtilSBPersistencia.getEMDoContexto();
            if (getPesquisaLead().getUsuario() == null) {

                //getPesquisaLead().setUsuario(UtilSBPersistencia.loadEntidade((UsuarioCRM) SBCore.getUsuarioLogado(), em));
                getPesquisaLead().setOrigensPrivadas(new ArrayList<>());
            } else {
                UsuarioCRM usuario = UtilSBPersistencia.loadEntidade((UsuarioCRM) getPesquisaLead().getUsuario(), em);

                getPesquisaLead().setOrigensPrivadas(usuario.getOrigens());
                usuario.getOrigens().size();
            }

            ultimoUsuarioUtilizado = getPesquisaLead().getUsuario();

        }

        return getPesquisaLead().getOrigensPrivadas();

    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
