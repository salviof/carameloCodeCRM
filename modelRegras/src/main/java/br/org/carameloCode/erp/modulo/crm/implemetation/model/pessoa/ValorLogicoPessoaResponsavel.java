package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.RESPONSAVEL)
public class ValorLogicoPessoaResponsavel extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaResponsavel(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!isCacheAtivado()) {
            if (getPessoa().getId() != null && getPessoa().getId() > 0) {
                if (getPessoa().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.contatoprincipal).getValor() != null) {
                    if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getContatoPrincipal().getNome())) {
                        getPessoa().setResponsavel(getPessoa().getContatoPrincipal().getNome());
                    }
                } else {
                    if (getPessoa().getResponsavel() == null) {
                        //   getPessoa().setResponsavel("Nenhum contato definido");
                    }
                }
            }

            ativarCache(10);
        }
        return getPessoa().getResponsavel();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
