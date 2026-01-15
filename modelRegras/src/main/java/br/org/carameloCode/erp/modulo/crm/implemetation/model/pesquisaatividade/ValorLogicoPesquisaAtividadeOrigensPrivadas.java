package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.ORIGENSPRIVADAS)
public class ValorLogicoPesquisaAtividadeOrigensPrivadas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeOrigensPrivadas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private UsuarioCRM ultimoUsuarioUtilizado = null;

    @Override
    public Object getValor(Object... pEntidade) {

        if (ultimoUsuarioUtilizado == null || !ultimoUsuarioUtilizado.equals(getPesquisaAtividade().getUsuario())) {
            EntityManager em = UtilSBPersistencia.getEMDoContexto();
            if (getPesquisaAtividade().getUsuario() == null) {

                //getPesquisaLead().setUsuario(UtilSBPersistencia.loadEntidade((UsuarioCRM) SBCore.getUsuarioLogado(), em));
                getPesquisaAtividade().setOrigensPrivadas(new ArrayList<>());
            } else {
                UsuarioCRM usuario = UtilSBPersistencia.loadEntidade((UsuarioCRM) getPesquisaAtividade().getUsuario(), em);

                getPesquisaAtividade().setOrigensPrivadas(usuario.getOrigens());
                usuario.getOrigens().size();
            }

            ultimoUsuarioUtilizado = getPesquisaAtividade().getUsuario();

        }

        return getPesquisaAtividade().getOrigensPrivadas();

    }

    private PesquisaAtividade getPesquisaAtividade() {
        return (PesquisaAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
