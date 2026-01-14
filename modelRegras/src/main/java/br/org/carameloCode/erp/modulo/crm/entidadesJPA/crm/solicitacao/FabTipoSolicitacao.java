/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao;

import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 *
 * @author salvio
 */
public enum FabTipoSolicitacao implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 1, nomeObjeto = "Solicitação de acesso")
    SOLICITACAO_ACESSO,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 2, nomeObjeto = "Solicitação de atividade")
    SOLICITACAO_ATIVIDADE_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 3, nomeObjeto = "Solicitação colaboração")
    SOLICITACAO_ATIVIDADE_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 4, nomeObjeto = "Solicitação doc Equipe")
    SOLICITACAO_NOVO_DOCUMENTO_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 5, nomeObjeto = "Solicitação Doc Cliente")
    SOLICITACAO_NOVO_DOCUMENTO_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 6, nomeObjeto = "Solicitação Atualização Doc.")
    SOLICITACAO_ATUALIZAR_DOCUMENTO_EQUIPE,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 7, nomeObjeto = "Solicitação Ref Chamado")
    SOLICITACAO_CHAMADO,
    @InfoObjetoDaFabrica(classeObjeto = TipoSolicitacao.class, id = 8, nomeObjeto = "Solicitação Ref Orçamento")
    SOLICITACAO_ORCAMENTO;

    @Override
    public TipoSolicitacao getRegistro() {
        TipoSolicitacao tipoSolicitacao = (TipoSolicitacao) ComoFabricaComPersistencia.super.getRegistro(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        switch (this) {

            case SOLICITACAO_ACESSO:
                tipoSolicitacao.setIcone("fa fa-share-alt");
                break;
            case SOLICITACAO_NOVO_DOCUMENTO_EQUIPE:
                tipoSolicitacao.setIcone("fa fa-file");
                break;
            case SOLICITACAO_NOVO_DOCUMENTO_CLIENTE:
                tipoSolicitacao.setIcone("fa fa-file");
                break;
            case SOLICITACAO_CHAMADO:
                tipoSolicitacao.setIcone("fa fa-ticket");
                break;
            case SOLICITACAO_ORCAMENTO:
                tipoSolicitacao.setIcone("fa fa-money");
                break;
            case SOLICITACAO_ATIVIDADE_EQUIPE:
                tipoSolicitacao.setIcone("fa fa-pencil");
                break;
            case SOLICITACAO_ATIVIDADE_CLIENTE:
                tipoSolicitacao.setIcone("fa fa-pencil");
                break;
            case SOLICITACAO_ATUALIZAR_DOCUMENTO_EQUIPE:
                tipoSolicitacao.setIcone("fa fa-upload");
                break;
            default:
                throw new AssertionError();
        }

        return tipoSolicitacao;
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

            default:
                throw new AssertionError();
        }
    }

}
