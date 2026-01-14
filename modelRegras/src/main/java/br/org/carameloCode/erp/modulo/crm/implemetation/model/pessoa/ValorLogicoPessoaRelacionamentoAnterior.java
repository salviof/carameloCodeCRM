package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Optional;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.RELACIONAMENTOANTERIOR)
public class ValorLogicoPessoaRelacionamentoAnterior
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaRelacionamentoAnterior(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        Optional<AtividadeCRM> atividades = getPessoa().getAtividadesRealizadas().stream().filter(atv -> atv.getRelacionamentoGerado() != null && !atv.getRelacionamentoGerado().equals(getPessoa().getRelacionamento())).findFirst();

        Optional<HistoricoRelacionamento> historico = getPessoa().getHistoricoRelacionamento().stream().filter(hist -> hist.getNovoRelacionamento() != null && !hist.getNovoRelacionamento().equals(getPessoa().getRelacionamento())).findFirst();
        if (historico.isPresent() || atividades.isPresent()) {
            if (historico.isPresent()) {
                getPessoa().setRelacionamentoAnterior(historico.get().getNovoRelacionamento());
            } else {
                getPessoa().setRelacionamentoAnterior(atividades.get().getRelacionamentoGerado());
            }
        } else {

            getPessoa().setRelacionamentoAnterior(null);
        }

        return getPessoa().getRelacionamentoAnterior();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
