package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoarquivoequipe;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.SolicitacaoArquivoEquipe;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe.ValorLogicoSolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe.ValoresLogicosSolicitacaoArquivoEquipe;

@ValorLogicoSolicitacaoArquivoEquipe(calculo = ValoresLogicosSolicitacaoArquivoEquipe.NOME)
public class ValorLogicoSolicitacaoArquivoEquipeNome
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoArquivoEquipeNome(ItfCampoInstanciado pCampo) {
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
