package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.USUARIODISPONIVEIS)
public class ValorLogicoChamadoClienteUsuarioDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteUsuarioDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        List<UsuarioCRM> usuariosDisponiveis = new ArrayList<>();
        if (getChamado().getTipoChamado() == null) {
            return getChamado().getUsuarioDisponiveis();
        }
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {
            TipoChamado tipoChamado = UtilSBPersistencia.loadEntidade(getChamado().getTipoChamado(), em);
            UsuarioCrmCliente usuarioCliente = UtilSBPersistencia.loadEntidade(getChamado().getUsuarioCliente(), em);
            tipoChamado.getResponsaveis().stream().filter(usuario -> (!usuariosDisponiveis.contains(usuario))).forEachOrdered(usuario -> {
                usuariosDisponiveis.add(usuario);
            });
            usuarioCliente.getRepresentanteLegal().getUsuariosResponsaveis().stream().filter(usuario -> (!usuariosDisponiveis.contains(usuario))).forEachOrdered(usuario -> {
                usuariosDisponiveis.add(usuario);
            });
            getChamado().setUsuarioDisponiveis(usuariosDisponiveis);
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return getChamado().getUsuarioDisponiveis();
    }

    public ChamadoCliente getChamado() {
        return (ChamadoCliente) getCampoInst().getObjetoRaizDoAtributo();
    }
}
