package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoatividadeequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValorLogicoSolicitacaoAtividadeEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoatividadeequipe.ValoresLogicosSolicitacaoAtividadeEquipe;

@ValorLogicoSolicitacaoAtividadeEquipe(calculo = ValoresLogicosSolicitacaoAtividadeEquipe.NOME)
public class ValorLogicoSolicitacaoAtividadeEquipeNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoAtividadeEquipeNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (UtilCRCStringValidador.isNuloOuEmbranco(getSolicitacao().getNome())) {
            String texto = getSolicitacao().getUsuarioSolicitante().getNome();
            texto = texto + " solicita um novo arquivo em  " + getSolicitacao().getCategoriaArqEquipe().getNome();
            getSolicitacao().setNome(texto);
        }

        return getSolicitacao().getNome();
    }

    public SolicitacaoArquivoEquipe getSolicitacao() {
        return (SolicitacaoArquivoEquipe) getCampoInst().getObjetoDoAtributo();
    }
}
