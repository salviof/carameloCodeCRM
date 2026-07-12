package br.org.carameloCode.erp.modulo.crm.implemetation.model.solicitacaoarquivoequipe;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe.ValorLogicoSolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe.ValoresLogicosSolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoSolicitacaoArquivoEquipe(calculo = ValoresLogicosSolicitacaoArquivoEquipe.LINKEQUIPEVERARQUIVO)
public class ValorLogicoSolicitacaoArquivoEquipeLinkEquipeVerArquivo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoSolicitacaoArquivoEquipeLinkEquipeVerArquivo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getSolicitacaoArquivoEquipe().getId() == null) {
            if (getSolicitacaoArquivoEquipe().getCategoriaArqEquipe() != null && getSolicitacaoArquivoEquipe().getPessoa() != null) {
                String url = CarameloCode.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.DOCUMENTOS_PESSOA_FRM_LISTAR_PASTAS_EQUIPE, getSolicitacaoArquivoEquipe().getPessoa(), getSolicitacaoArquivoEquipe().getCategoriaArqEquipe());
                getSolicitacaoArquivoEquipe().setLinkEquipeVerArquivo(url);
            }
        }
        return getSolicitacaoArquivoEquipe().getLinkEquipeVerArquivo();
    }

    public SolicitacaoArquivoEquipe getSolicitacaoArquivoEquipe() {
        return (SolicitacaoArquivoEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
