package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.ServicoOferecido;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.SERVICOS)
public class ValorLogicoPessoaServicos extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaServicos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPessoa().getServicos() == null || getPessoa().getServicos().isEmpty()) {

            if (getPessoa().getOrcamentos().isEmpty()) {
                //compatibilidade para Lead Legado (quando não existiam orçamentos
                List<ServicoOferecido> servicos = UtilSBPersistencia.getListaRegistrosByHQL("from ServicoOferecido where orcamento_id = null and prospecto_id =" + getPessoa().getId(), 0, UtilSBPersistencia.getEMDoContexto());
                getPessoa().setServicos(servicos);
            } else {

                Orcamento orcamento = (Orcamento) getPessoa().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.ultimoorcamento).getValor();
                if (orcamento != null) {
                    getPessoa().setServicos(orcamento.getServicoOferecido());
                }
            }
        }
        return getPessoa().getServicos();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
