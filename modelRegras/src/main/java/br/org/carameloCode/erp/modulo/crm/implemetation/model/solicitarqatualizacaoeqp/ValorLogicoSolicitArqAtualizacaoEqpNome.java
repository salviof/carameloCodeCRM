package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitarqatualizacaoeqp;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp.ValorLogicoSolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitarqatualizacaoeqp.ValoresLogicosSolicitArqAtualizacaoEqp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitArqAtualizacaoEqp(calculo = ValoresLogicosSolicitArqAtualizacaoEqp.NOME)
public class ValorLogicoSolicitArqAtualizacaoEqpNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitArqAtualizacaoEqpNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (UtilCRCStringValidador.isNuloOuEmbranco(getSolicitArqAtualizacaoEqp().getNome())) {
            String texto = getSolicitArqAtualizacaoEqp().getUsuarioSolicitante().getNome();
            texto = texto + " solicita atualizacao do arquivo " + getSolicitArqAtualizacaoEqp().getArquivo().getArquivo();
            getSolicitArqAtualizacaoEqp().setNome(texto);
        }

        return getSolicitArqAtualizacaoEqp().getNome();

    }

    public SolicitArqAtualizacaoEqp getSolicitArqAtualizacaoEqp() {
        return (SolicitArqAtualizacaoEqp) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
