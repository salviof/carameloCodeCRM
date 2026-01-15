/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.webPaginas.centralComunicacao.CentralComunicaoWebPadrao;
import java.util.List;
import javax.persistence.EntityManager;
import org.primefaces.PrimeFaces;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoArmazenamentoComunicacao;

/**
 *
 * @author salvio
 */
public class ConfigClasseComunicacaoCRM extends CentralComunicaoWebPadrao {

    @Override
    public List<ItfDialogo> getComunicacoesAguardandoRespostaDoDestinatario(ComoUsuario pDestinatario) {
        List<ItfDialogo> comunicacoes = super.getComunicacoesAguardandoRespostaDoDestinatario(pDestinatario);
        return comunicacoes;
    }

    @Override
    public ComoArmazenamentoComunicacao getArmazenamento() {
        return super.getArmazenamento();
    }

    @Override
    public boolean responderComunicacao(String codigoSeloComunicacao, ItfRespostaComunicacao pResposta) {

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            ItfDialogo pComunicacao = SBCore.getServicoComunicacao().getArmazenamento().getDialogoByCodigoSelo(codigoSeloComunicacao);
            if (pComunicacao instanceof SolicitacaoAcessoCard) {
                SolicitacaoAcessoCard solicitacaoAcesso = UtilSBPersistencia.loadEntidade(pComunicacao, em);
                if (pResposta.getTipoResposta().isRespostasPosiva()) {
                    return ModuloCRMAtendimento.solicitacaoConcederAcessoCard(solicitacaoAcesso).isSucesso();
                } else {
                    return ModuloCRMAtendimento
                            .solicitacaoNegarAcessoCard(solicitacaoAcesso).isSucesso();
                }
            }

            if (pComunicacao instanceof SolicitacaoArquivoEquipe) {
                SolicitacaoArquivoEquipe solicitacaoArquivoEquipe = UtilSBPersistencia.loadEntidade(pComunicacao, em);
                if (pResposta.getTipoResposta().isRespostasPosiva()) {
                    String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.SOLICITACAO_FRM_ENVIAR_ARQUIVO_EQUIPE, solicitacaoArquivoEquipe, solicitacaoArquivoEquipe.getPessoa());

                    //
                    PrimeFaces contextPrime = PrimeFaces.current();
                    //execute javascript oncomplete

                    contextPrime.executeScript("window.top.location.href = '" + url + "';");

                    return true;
                } else {
                    return super.responderComunicacao(solicitacaoArquivoEquipe.getCodigoSelo(), pResposta);
                }
            }

            return super.responderComunicacao(pComunicacao.getCodigoSelo(), pResposta);
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

    }

}
