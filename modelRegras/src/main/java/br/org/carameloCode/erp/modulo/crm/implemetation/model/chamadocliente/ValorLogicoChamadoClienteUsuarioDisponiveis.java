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
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.USUARIODISPONIVEIS)
public class ValorLogicoChamadoClienteUsuarioDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteUsuarioDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getChamado().getUsuarioDisponiveis() != null && !getChamado().getUsuarioDisponiveis().isEmpty()) {
            return getChamado().getUsuarioDisponiveis();
        }
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {
            List<UsuarioCRM> usuariosDisponiveis = new ArrayList<>();
            if (getChamado().getPessoa() != null) {
                Pessoa pessoa = UtilSBPersistencia.loadEntidade(getChamado().getPessoa(), em);
                List<UsuarioCRM> responsaveis = (List) pessoa.getCPinst(CPPessoa.usuariosresponsaveis).getValor();
                responsaveis.stream().forEach(usuariosDisponiveis::add);
            }
            TipoChamado tipoChamado = UtilSBPersistencia.loadEntidade(getChamado().getTipoChamado(), em);
            if (!tipoChamado.getResponsaveis().isEmpty()) {

                List<UsuarioCRM> responsaveisDesteChamado = tipoChamado.getResponsaveis();

                responsaveisDesteChamado.stream().filter(usuario -> (!usuariosDisponiveis.contains(usuario))).forEachOrdered(usuario -> {
                    if (!usuariosDisponiveis.contains(usuario)) {
                        usuariosDisponiveis.add(usuario);
                    }
                });
            }

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
