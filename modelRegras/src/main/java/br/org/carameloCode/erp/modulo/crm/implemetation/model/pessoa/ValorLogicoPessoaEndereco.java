package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocalPostagem;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.ENDERECO)
public class ValorLogicoPessoaEndereco extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaEndereco(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPessoa().getLocalizacao() != null && getPessoa().getLocalizacao().getBairro() != null) {
            if (getPessoa().getLocalizacao().getId() != 0) {

                ComoLocalPostagem localizacao = getPessoa().getCampoInstanciadoByNomeOuAnotacao(CPPessoa.localizacao).getComoCampoLocalizacao().getLocal().getComoLocalPostavel();
                if (!UtilCRCStringValidador.isNuloOuEmbranco(localizacao.getComplemento())) {
                    if (localizacao.getComplemento().matches(".*\\d.*")) {
                        StringBuilder textoLocalizacao = new StringBuilder();
                        textoLocalizacao.append(localizacao.getLogradouro())
                                .append(",")
                                .append(localizacao.getComplemento())
                                .append(" - ")
                                .append(localizacao.getBairro().getNome())
                                .append(" -")
                                .append(localizacao.getBairro().getCidade().getNome())
                                .append(" / ")
                                .append(localizacao.getBairro().getCidade().getUnidadeFederativa().getSigla())
                                .append(" - ")
                                .append(localizacao.getCep());
                        getPessoa().setEndereco(textoLocalizacao.toString());
                    }

                }

            }
        }
        return getPessoa().getEndereco();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }

}
