package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.ULTIMOSARQUIVOS)
public class ValorLogicoPessoaUltimosArquivos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaUltimosArquivos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getPessoa().getUltimosArquivos() == null || getPessoa().getUltimosArquivos().isEmpty()) {
            List<ArquivoAnexado> arquivos = new ArrayList<>();
            getPessoa().getArquivos().stream().limit(5).forEach(arquivos::add);
            getPessoa().setUltimosArquivos(arquivos);
        }

        return getPessoa().getUltimosArquivos();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
