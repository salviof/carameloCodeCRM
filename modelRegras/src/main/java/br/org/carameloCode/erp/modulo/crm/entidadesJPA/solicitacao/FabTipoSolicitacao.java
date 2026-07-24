/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salvio
 */
public enum FabTipoSolicitacao implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 1, icone = "fa fa-unlock", nomeObjeto = "Solicitação de acesso")
    SOLICITACAO_ACESSO,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 2, icone = "fa fa-list-check", nomeObjeto = "Solicitação de atividade")
    SOLICITACAO_ATIVIDADE_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 3, icone = "fa fa-handshake", nomeObjeto = "Solicitação colaboração")
    SOLICITACAO_ATIVIDADE_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 4, icone = "fa fa-file-circle-plus", nomeObjeto = "Solicitação doc Equipe")
    SOLICITACAO_NOVO_DOCUMENTO_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 5, icone = "fa fa-file-contract", nomeObjeto = "Solicitação Doc Cliente")
    SOLICITACAO_NOVO_DOCUMENTO_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 6, icone = "fa fa-file-pen", nomeObjeto = "Solicitação Atualização Doc.")
    SOLICITACAO_ATUALIZAR_DOCUMENTO_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 7, icone = "fa fa-ticket", nomeObjeto = "Solicitação Ref Chamado")
    SOLICITACAO_CHAMADO,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 8, icone = "fa fa-receipt", nomeObjeto = "Solicitação Ref Orçamento")
    SOLICITACAO_ORCAMENTO,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 9, icone = "fa fa-circle-check", nomeObjeto = "Solicitação Confirmação Equipe")
    SOLICITACAO_CONFIRMACAO_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 10, icone = "fa fa-circle-notch", nomeObjeto = "Solicitação Confirmação Cliente")
    SOLICITACAO_CONFIRMACAO_CLIENTE;

    @Override
    public TipoSolicitacao getRegistro() {
        return (TipoSolicitacao) ComoFabricaComPersistencia.super.getRegistro(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Solicitacao getDominioEstrategia() {
        switch (this) {

            case SOLICITACAO_ACESSO:
                return new SolicitacaoAcessoCard();

            case SOLICITACAO_NOVO_DOCUMENTO_EQUIPE:
                return new SolicitacaoArquivoEquipe();

            case SOLICITACAO_NOVO_DOCUMENTO_CLIENTE:
                return new SolicitacaoArquivoCliente();

            case SOLICITACAO_CHAMADO:
                return new SolicitacaoChamado();

            case SOLICITACAO_ORCAMENTO:
                return new SolicitacaoOrcamento();

            case SOLICITACAO_ATIVIDADE_EQUIPE:
                return new SolicitacaoAtividadeEquipe();

            case SOLICITACAO_ATIVIDADE_CLIENTE:
                return new SolicitacaoAtividadeEquipe();

            case SOLICITACAO_ATUALIZAR_DOCUMENTO_EQUIPE:
                return new SolicitacaoAtualizacaoArquivoEquipe();
            case SOLICITACAO_CONFIRMACAO_EQUIPE:
                return new SolicitacaoConfirmacaoEquipe();

            case SOLICITACAO_CONFIRMACAO_CLIENTE:
                return new SolicitacaoConfirmacaoCliente();

            default:
                throw new AssertionError();
        }
    }

}
