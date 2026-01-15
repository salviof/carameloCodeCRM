package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.ULTIMAATIVIDADEEMANDAMENTO)
public class ValorLogicoPessoaUltimaAtividadeEmAndamento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaUltimaAtividadeEmAndamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPessoa().getId() == null) {
            getPessoa().setUltimaAtividadeEmAndamento(null);
            return getPessoa().getUltimaAtividadeEmAndamento();
        }
        try {
            if (getPessoa().getAtividadesEmAndamento().isEmpty()) {
                getPessoa().setUltimaAtividadeEmAndamento(null);
                return getPessoa().getUltimaAtividadeEmAndamento();
            }

            AtividadeCRM atividade = getPessoa().getAtividadesEmAndamento().get(getPessoa().getAtividadesEmAndamento().size() - 1);
            getPessoa().setUltimaAtividadeEmAndamento(atividade);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando ultima atividade em andamento", t);

        }
        return getPessoa().getUltimaAtividadeEmAndamento();
    }

    public Pessoa getPessoa() {
        Pessoa p = (Pessoa) getCampoInst().getObjetoDoAtributo();
        try {
            if (p.getId() != 0) {
                p.getAtividadesEmAndamento().isEmpty();
            }
        } catch (Throwable coxexaoFechada) {
            p = UtilSBPersistencia.loadEntidade(p, UtilSBPersistencia.getEMDoContexto());
        }

        return p;
    }

}
