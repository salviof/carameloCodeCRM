package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatualizacaoarquivoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValorLogicoSolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatualizacaoarquivoequipe.ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitArqAtualizacaoEqp;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;

@ValorLogicoSolicitacaoAtualizacaoArquivoEquipe(calculo = ValoresLogicosSolicitacaoAtualizacaoArquivoEquipe.NOME)
public class ValorLogicoSolicitacaoAtualizacaoArquivoEquipeNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoAtualizacaoArquivoEquipeNome(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (UtilCRCStringValidador.isNuloOuEmbranco(getSolicitacao().getNome())) {
            String texto = getSolicitacao().getUsuarioSolicitante().getNome();
            texto = texto + " solicita atualizacao do arquivo " + getSolicitacao().getArquivo().getArquivo();
            getSolicitacao().setNome(texto);
        }

        return getSolicitacao().getNome();

    }

    public SolicitArqAtualizacaoEqp getSolicitacao() {
        return (SolicitArqAtualizacaoEqp) getCampoInst().getObjetoRaizDoAtributo();
    }
}
