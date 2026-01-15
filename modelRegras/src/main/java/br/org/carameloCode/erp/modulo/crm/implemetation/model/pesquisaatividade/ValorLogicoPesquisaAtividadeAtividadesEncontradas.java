package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.ATIVIDADESENCONTRADAS)
public class ValorLogicoPesquisaAtividadeAtividadesEncontradas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeAtividadesEncontradas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        boolean temData = getPesquisa().getDatainicial() != null;

        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(AtividadeCRM.class, UtilSBPersistencia.getEMDoContexto());
        if (temData) {
            consulta.addCondicaoDataHoraMaiorOuIgualA(CPAtividadeCRM.datahorainicioatividade, getPesquisa().getDatainicial());
            consulta.addCondicaoDataHoraMenorOuIgualA(CPAtividadeCRM.datahorainicioatividade, getPesquisa().getDatafinal());
        }
        if (getPesquisa().getTipoRelacionamento() != null) {
            consulta.addCondicaoManyToOneIgualA(CPAtividadeCRM.relacionamentogerado, getPesquisa().getTipoRelacionamento());
        }

        if (getPesquisa().getMetaRelacionamento() != null) {
            //consulta.addCondicaoManyToOneIgualA(CPAtividadeCRM., pEtidadeManyToOne)
        }
        if (getPesquisa().getUsuario() != null) {
            consulta.addCondicaoManyToOneIgualA(CPAtividadeCRM.usuarioatividade, getPesquisa().getUsuario());
        }

        consulta.setOrdemIdReversa();
        List<AtividadeCRM> atividades = consulta.resultadoRegistros();
        getPesquisa().setAtividadesEncontradas(atividades);
        return getPesquisa().getAtividadesEncontradas();
    }

    public PesquisaAtividade getPesquisa() {
        return (PesquisaAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
