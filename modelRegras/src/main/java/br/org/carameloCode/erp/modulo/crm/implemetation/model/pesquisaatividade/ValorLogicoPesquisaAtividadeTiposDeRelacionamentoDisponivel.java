package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.TIPOSDERELACIONAMENTODISPONIVEL)
public class ValorLogicoPesquisaAtividadeTiposDeRelacionamentoDisponivel
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeTiposDeRelacionamentoDisponivel(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getPesquisaAtv().getMetaRelacionamento() == null) {
            getPesquisaAtv().setTiposDeRelacionamentoDisponivel(new ArrayList<>());
        } else {
            EntityManager em = UtilSBPersistencia.getEMDoContexto();

            List<TipoRelacionamento> tiposDeRelacionamento = new ConsultaDinamicaDeEntidade(TipoRelacionamento.class, em)
                    .addCondicaoManyToOneIgualA("metaRelacionamento", getPesquisaAtv().getMetaRelacionamento()).resultadoRegistros();

            getPesquisaAtv().setTiposDeRelacionamentoDisponivel(tiposDeRelacionamento);

            UtilSBPersistencia.fecharEM(em);
        }

        return getPesquisaAtv().getTiposDeRelacionamentoDisponivel();
    }

    private PesquisaAtividade getPesquisaAtv() {
        return (PesquisaAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
