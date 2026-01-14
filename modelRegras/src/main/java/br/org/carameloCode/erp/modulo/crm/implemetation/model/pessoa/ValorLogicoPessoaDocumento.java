package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.DOCUMENTO)
public class ValorLogicoPessoaDocumento extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaDocumento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getPessoa().isUmaPessoaFisica()) {
            getPessoa().setDocumento(getPessoa().getComoPessoaFisica().getCpf());
        }
        if (getPessoa().isUmaPessoaJuridica()) {
            getPessoa().setDocumento(getPessoa().getComoPessoaJuridica().getCnpj());
        }
        if (!UtilCRCStringValidador.isNuloOuEmbranco(getPessoa().getDocumento())) {
            getPessoa().setDocumento(UtilCRCStringFiltros.filtrarApenasNumeros(getPessoa().getDocumento()));
        }
        return getPessoa().getDocumento();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
