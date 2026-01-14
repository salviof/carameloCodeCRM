/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.FabTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.FabTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.fluxoAtividade.ItfFabFluxoDeAtividades;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.zonaRelacionamento.FabResultadoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.zonaRelacionamento.ResultadoTipoRelacionamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 *
 *
 * @author sfurbino
 */
public enum FabTipoRelacionamentoMarketingDigital implements ItfFabFluxoDeAtividades {

    NAO_INDICADO,
    CADASTRADO,
    QUALIFICADO,
    PRE_ANALISE_GERADA,
    BRIFING_REALIZADO,
    PROPOSTA_ENTREGUE,
    VALORES_DO_CONTRATO_NEGOCIADOS,
    ATIVO,
    INATIVO,
    NEGOCIACAO_PERDIDA;
    public static final String COR_RELACIONAMENTO_POSITIVO = "#9CB071";
    public static final String COR_RELACIONAMENTO_NEGATIVO = "#C34A2C";
    public static final String COR_RELACIONAMENTO_NEUTRO = "#C34A2C";
    public final TipoRelacionamento novoTipo = new TipoRelacionamento();

    public List<TipoDadoCRM> getTipoDadosNescessarios() {
        List<TipoDadoCRM> lista = new ArrayList<>();
        switch (this) {
            case NAO_INDICADO:

                break;
            case CADASTRADO:
                lista.add(FabTipoDadoCRM.MOTIVO_PROSPECT.getRegistro());
                lista.add(FabTipoDadoCRM.INDICACAO.getRegistro());
                lista.add(FabTipoDadoCRM.PORTE.getRegistro());

                break;
            case QUALIFICADO:
                lista.add(FabTipoDadoCRM.SITE.getRegistro());
                lista.add(FabTipoDadoCRM.BR_E_MOBILE.getRegistro());
                lista.add(FabTipoDadoCRM.BR_E_OTIMIZADO.getRegistro());
                lista.add(FabTipoDadoCRM.BR_SITE_CONCORRENTE1.getRegistro());
                lista.add(FabTipoDadoCRM.BR_SITE_CONCORRENTE2.getRegistro());
                lista.add(FabTipoDadoCRM.BR_SITE_CONCORRENTE3.getRegistro());
                lista.add(FabTipoDadoCRM.PORTE.getRegistro());

                break;
            case PRE_ANALISE_GERADA:
                lista.add(FabTipoDadoCRM.RESPONSAVEL.getRegistro());

                break;
            case BRIFING_REALIZADO:
                lista.add(FabTipoDadoCRM.BR_DESEJO_CLIENTE.getRegistro());
                lista.add(FabTipoDadoCRM.BR_GASTO_ANUAL_PUBLICIDADE.getRegistro());
                lista.add(FabTipoDadoCRM.BR_JA_FEZ_MARKETING_DIGITAL.getRegistro());
                lista.add(FabTipoDadoCRM.BR_POSSIVEL_ECOMERCE.getRegistro());
                break;
            case PROPOSTA_ENTREGUE:

                break;
            case VALORES_DO_CONTRATO_NEGOCIADOS:
                lista.add(FabTipoDadoCRM.CNPJ.getRegistro());
                break;
            case ATIVO:
                lista.add(FabTipoDadoCRM.CNPJ.getRegistro());

                break;
            case INATIVO:
                break;
            case NEGOCIACAO_PERDIDA:

                break;

            default:
                throw new AssertionError(this.name());

        }
        return lista;
    }

    /**
     *
     *
     * Retorna as Atividade disponíveis de acordo com prospecto
     *
     *
     * @param pTipoRelacionamento
     * @return
     */
    public List<TipoAtividadeCRM> getAtividadesDisponiveis() {
        List<TipoAtividadeCRM> atividades = new ArrayList<>();

        switch (this) {
            case NAO_INDICADO:

                break;
            case CADASTRADO:
                atividades.add(FabTipoAtividadeCRM.CRIAR_PROSPECTO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.DECLARAR_COMO_INADEQUADO.getRegistro());
                break;
            case QUALIFICADO:
                atividades.add(FabTipoAtividadeCRM.REALIZAR_PRE_ANALIZE.getRegistro());
                atividades.add(FabTipoAtividadeCRM.ENVIAR_PRE_ANALISE.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_ATIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_EMAIL.getRegistro());

                break;
            case PRE_ANALISE_GERADA:
                atividades.add(FabTipoAtividadeCRM.REALIZAR_BRIFING.getRegistro());

                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_ATIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_EMAIL.getRegistro());

                break;
            case BRIFING_REALIZADO:
                atividades.add(FabTipoAtividadeCRM.CRIAR_PROPOSTA.getRegistro());
                atividades.add(FabTipoAtividadeCRM.DESISTIR_POR6MESES.getRegistro());

                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_ATIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_EMAIL.getRegistro());
                break;
            case PROPOSTA_ENTREGUE:
                atividades.add(FabTipoAtividadeCRM.DEFINIR_VALORES.getRegistro());

                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_ATIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_EMAIL.getRegistro());
                break;
            case VALORES_DO_CONTRATO_NEGOCIADOS:
                atividades.add(FabTipoAtividadeCRM.DEFINIR_VALORES.getRegistro());
                atividades.add(FabTipoAtividadeCRM.COLETAR_ASSINATURA_CONTRATO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CRIAR_NOVA_PROPOSTA.getRegistro());

                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_ATIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_EMAIL.getRegistro());

                break;
            case ATIVO:
                atividades.add(FabTipoAtividadeCRM.CANCELAR_CONTRATO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.ENVIAR_BOLETO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONSULTAR_DADOS.getRegistro());

                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_ATIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro());
                atividades.add(FabTipoAtividadeCRM.CONVERSA_EMAIL.getRegistro());

                break;
            case INATIVO:
                atividades.add(FabTipoAtividadeCRM.COLETAR_ASSINATURA_CONTRATO.getRegistro());
                break;
            case NEGOCIACAO_PERDIDA:
                break;
            default:
                throw new AssertionError(this);

        }

        return atividades;
    }

    @Override
    public TipoRelacionamento getRegistro() {
        novoTipo.setNome(this.name());
        novoTipo.setNomeDoRelacionado("Prospecto");
        novoTipo.setResultado(this.getResultado());
        novoTipo.setDadosNescessarios(getTipoDadosNescessarios());

        switch (this) {
            case NAO_INDICADO:
                novoTipo.setId(1l);
                novoTipo.setDescricao("Esta empresa não é indicável como possível cliente");
                novoTipo.setDicas("");
                novoTipo.setNome("Não Indicado");
                novoTipo.setPeso(-1);
                novoTipo.setNomeDoRelacionado("Desprospectado");
                novoTipo.setCor(COR_RELACIONAMENTO_NEGATIVO);
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.ENCONTRADO.getRegistro());
                break;
            case CADASTRADO:
                novoTipo.setId(2l);
                novoTipo.setNome("Cadastrado");
                novoTipo.setDescricao("Ouve a coleta dos dados iniciais");
                novoTipo.setDicas("");
                novoTipo.setNomeDoRelacionado("Coletado");
                novoTipo.setPeso(0);
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.ENCONTRADO.getRegistro());
                break;
            case QUALIFICADO:
                novoTipo.setId(3l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("Um cadastro no estagio de prospecto deve ser pré-analizado, "
                        + "só assim poderemos partir para uma conversa sabendo quem é o cliente");
                novoTipo.setNome("Qualificado");
                novoTipo.setPeso(2);
                novoTipo.setNomeDoRelacionado("Qualificado");
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.ENCONTRADO.getRegistro());

                break;
            case PRE_ANALISE_GERADA:
                novoTipo.setId(4l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("");
                novoTipo.setNome("Pre-Analize Entregue");
                novoTipo.setPeso(4);
                novoTipo.setNomeDoRelacionado("Contato Estabelecido");
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.ENCONTRADO.getRegistro());
                break;
            case BRIFING_REALIZADO:
                novoTipo.setId(5l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("");
                novoTipo.setNome("Brifing Coletado");
                novoTipo.setPeso(6);
                novoTipo.setNomeDoRelacionado("Brifing Coletado");
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.ENCONTRADO.getRegistro());
                break;
            case PROPOSTA_ENTREGUE:
                novoTipo.setId(6l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("");
                novoTipo.setNome("Proposta Entregue");
                novoTipo.setPeso(9);
                novoTipo.setNomeDoRelacionado("Prospecto em negociação");
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.EM_NEGOCIACAO.getRegistro());
                break;
            case VALORES_DO_CONTRATO_NEGOCIADOS:
                novoTipo.setId(7l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("");
                novoTipo.setNome("Negociação em andamento");
                novoTipo.setPeso(9);
                novoTipo.setNomeDoRelacionado("Negociação em andamento");
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.EM_NEGOCIACAO.getRegistro());
                break;
            case ATIVO:
                novoTipo.setId(8l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("");
                novoTipo.setNome("Contrato Ativo");
                novoTipo.setPeso(10);
                novoTipo.setNomeDoRelacionado("Cliente ");
                novoTipo.setCor(COR_RELACIONAMENTO_POSITIVO);
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.CLIENTE.getRegistro());

                break;
            case INATIVO:
                novoTipo.setId(9l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("");
                novoTipo.setNome("Inativo");
                novoTipo.setPeso(-3);
                novoTipo.setNomeDoRelacionado("Ex Cliente");
                novoTipo.setCor(COR_RELACIONAMENTO_NEGATIVO);
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.ENCONTRADO.getRegistro());
                break;
            case NEGOCIACAO_PERDIDA:
                novoTipo.setId(10l);
                novoTipo.setDescricao("");
                novoTipo.setDicas("");
                novoTipo.setNome("Negociação perdida");
                novoTipo.setPeso(-2);
                novoTipo.setNomeDoRelacionado("Quase Fechou");
                novoTipo.setCor(COR_RELACIONAMENTO_NEGATIVO);
                novoTipo.setMetaRelacionamento(FabMetasRelacionamento.ENCONTRADO.getRegistro());
                break;

            default:
                throw new AssertionError(this.name());

        }

        return novoTipo;
    }

    @Override
    public TipoRelacionamento getAtividadeInicial() {
        return CADASTRADO.getRegistro();
    }

    @Override
    public ResultadoTipoRelacionamento getResultado() {
        switch (this) {

            case ATIVO:
                return FabResultadoTipoRelacionamento.POSITIVO.getRegistro();
            case INATIVO:
            case NAO_INDICADO:
            case NEGOCIACAO_PERDIDA:
                return FabResultadoTipoRelacionamento.NEGATIVO.getRegistro();
            default:
                return FabResultadoTipoRelacionamento.NEUTRO.getRegistro();

        }

    }

}
