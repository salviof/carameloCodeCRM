package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.TAGSDISPONIVEIS)
public class ValorLogicoPesquisaLeadTagsDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadTagsDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        try {
            if (getPesquisaLead().getUsuario() == null) {
                List<TagAtendimento> todasTags = UtilSBPersistencia.getListaTodos(TagAtendimento.class, em);
                getPesquisaLead().setTagsDisponiveis(todasTags);
            } else {
                UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(getPesquisaLead().getUsuario(), em);
                //inicializando Lazymode das tags monitoradas
                usuario.getTagsMonitoradas().size();
                getPesquisaLead().setTagsDisponiveis(usuario.getTagsMonitoradas());
                getPesquisaLead().getUsuario();
            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

        return getPesquisaLead().getTagsDisponiveis();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoRaizDoAtributo();
    }
}
