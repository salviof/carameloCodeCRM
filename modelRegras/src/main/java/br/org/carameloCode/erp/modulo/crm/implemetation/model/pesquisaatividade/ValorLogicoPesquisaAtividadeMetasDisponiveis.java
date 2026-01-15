package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.METASDISPONIVEIS)
public class ValorLogicoPesquisaAtividadeMetasDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeMetasDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private boolean metasListadas = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!metasListadas) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            List<MetaRelacionamento> metas = UtilSBPersistencia.getListaTodos(MetaRelacionamento.class, em);
            getPesquisaAtividade().setMetasDisponiveis(metas);
            metasListadas = true;
        }
        return getPesquisaAtividade().getMetasDisponiveis();
    }

    private PesquisaAtividade getPesquisaAtividade() {
        return (PesquisaAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
