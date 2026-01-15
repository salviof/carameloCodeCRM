package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estilo.EstiloVisualizacaoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.ESTILOVISUALIZACAO)
public class ValorLogicoPessoaEstiloVisualizacao
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaEstiloVisualizacao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getProspecto().getEstiloVisualizacao() == null) {
            getProspecto().setEstiloVisualizacao(new EstiloVisualizacaoProspecto(getProspecto()));
        }
        return getProspecto().getEstiloVisualizacao();

    }

    public Pessoa getProspecto() {

        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
