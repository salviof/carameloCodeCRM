package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.TIPOSDERELACIONAMENTODISPONIVEL)
public class ValorLogicoPesquisaLeadTiposDeRelacionamentoDisponivel
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadTiposDeRelacionamentoDisponivel(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getPesquisaLead().getMetaRelacionamento() == null) {
            getPesquisaLead().setTiposDeRelacionamentoDisponivel(new ArrayList<>());
        } else {
            EntityManager em = UtilSBPersistencia.getEMDoContexto();

            List<TipoRelacionamento> tiposDeRelacionamento = new ConsultaDinamicaDeEntidade(TipoRelacionamento.class, em)
                    .addCondicaoManyToOneIgualA("metaRelacionamento", getPesquisaLead().getMetaRelacionamento()).resultadoRegistros();

            getPesquisaLead().setTiposDeRelacionamentoDisponivel(tiposDeRelacionamento);

            UtilSBPersistencia.fecharEM(em);
        }

        return getPesquisaLead().getTiposDeRelacionamentoDisponivel();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
