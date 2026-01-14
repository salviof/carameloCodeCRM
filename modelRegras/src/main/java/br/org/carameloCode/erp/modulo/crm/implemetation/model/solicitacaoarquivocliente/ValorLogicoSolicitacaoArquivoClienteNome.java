package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoarquivocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoCliente;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente.ValorLogicoSolicitacaoArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivocliente.ValoresLogicosSolicitacaoArquivoCliente;

@ValorLogicoSolicitacaoArquivoCliente(calculo = ValoresLogicosSolicitacaoArquivoCliente.NOME)
public class ValorLogicoSolicitacaoArquivoClienteNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoArquivoClienteNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (UtilCRCStringValidador.isNuloOuEmbranco(getSolicitacao().getNome())) {
            String texto = getSolicitacao().getUsuarioSolicitante().getNome();
            texto = texto + " solicita um novo arquivo em  " + getSolicitacao().getCategoriaArqCliente().getNome();
            getSolicitacao().setNome(texto);
        }

        return getSolicitacao().getNome();

    }

    public SolicitacaoArquivoCliente getSolicitacao() {
        return (SolicitacaoArquivoCliente) getCampoInst().getObjetoDoAtributo();
    }
}
