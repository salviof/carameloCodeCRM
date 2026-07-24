package br.org.carameloCode.erp.modulo.crm.implemetation.model.categoriaarquivoequipe;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivoequipe.ValorLogicoCategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.categoriaarquivoequipe.ValoresLogicosCategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacaoarquivoequipe.CPSolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.FabStatusSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoCategoriaArquivoEquipe(calculo = ValoresLogicosCategoriaArquivoEquipe.TEMSOLICITACAOPARAMIM)
public class ValorLogicoCategoriaArquivoEquipeTemSolicitacaoParaMim
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoCategoriaArquivoEquipeTemSolicitacaoParaMim(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    boolean valorDefinido = false;

    @Override
    public synchronized Object getValor(Object... pEntidade) {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        if (!valorDefinido) {
            try {
                if (!CarameloCode.getServicoSessao().getSessaoAtual().isIdentificado()) {
                    getCategoriaArquivoEquipe().setTemSolicitacaoParaMim(false);
                } else {
                    ConsultaDinamicaDeEntidade pesquisaSolicitacao = new ConsultaDinamicaDeEntidade(SolicitacaoArquivoEquipe.class, em);
                    pesquisaSolicitacao.addCondicaoManyToOneIgualA(CPSolicitacao.status, FabStatusSolicitacao.EQUIPE_DEVENDO_ARQUIVO_A_EQUIPE.getRegistro());
                    if (getCategoriaArquivoEquipe() != null) {

                        pesquisaSolicitacao.addCondicaoManyToOneIgualA(CPSolicitacaoArquivoEquipe.categoriaarqequipe, getCategoriaArquivoEquipe());
                    }

                    pesquisaSolicitacao.addCondicaoManyToOneIgualA(CPSolicitacao.usuariosolicitado, CarameloCode.getUsuarioLogado());
                    List<SolicitacaoArquivoEquipe> soicitacoes = pesquisaSolicitacao.gerarResultados();
                    getCategoriaArquivoEquipe().setTemSolicitacaoParaMim(!soicitacoes.isEmpty());
                }
            } finally {
                UtilSBPersistencia.fecharEM(em);
                valorDefinido = true;
            }
        }
        return getCategoriaArquivoEquipe().isTemSolicitacaoParaMim();

    }

    public CategoriaArquivoEquipe getCategoriaArquivoEquipe() {
        return (CategoriaArquivoEquipe) getCampoInst()
                .getObjetoRaizDoAtributo();
    }

}
