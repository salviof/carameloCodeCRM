package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica.ValorLogicoPessoaFisicaUsuariosResponsaveis;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.USUARIOSRESPONSAVEIS)
public class ValorLogicoPessoaUsuariosResponsaveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaUsuariosResponsaveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPessoa().getUsuarioResponsavel() != null && getPessoa().getUsuariosResponsaveis() != null) {

            if (!getPessoa().getUsuariosResponsaveis().contains(getPessoa().getUsuarioResponsavel())) {
                getPessoa().getUsuariosResponsaveis().add(getPessoa().getUsuarioResponsavel());
            }
        }
        return getPessoa().getUsuariosResponsaveis();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoRaizDoAtributo();
    }
}
