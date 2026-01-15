/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.FabTipoRelacionamentoMarketingDigital;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Fabrica para: CRIAÇÃO DE TipoAtividadeCRM CRIAÇÃO e nova AtividadeCRM a
 * partir do tipo
 *
 *
 * @author sfurbino
 */
public enum FabTipoAtividadeCRM implements ComoFabrica {

    CONVERSA_TELEFONE_PASSIVO,
    CONVERSA_TELEFONE_ATIVO,
    CONVERSA_EMAIL,
    /**
     * PASSA A EMPRESA PARA O ESTADO DE
     *
     * @see FabTipoRelacionamentoMarketingDigital#QUALIFICADO
     *
     *
     */
    CRIAR_PROSPECTO,
    /**
     *
     * PASSA A EMPRESA PARA ESTADO DE:
     *
     * @see FabTipoRelacionamentoMarketingDigital#BRIFING_REALIZADO
     *
     */
    REALIZAR_PRE_ANALIZE,
    /**
     * Envia uma pré análise por email
     */
    ENVIAR_PRE_ANALISE,
    /**
     *
     * PASSA A EMPRESA PARA O ESTADO DE
     *
     * @see FabTipoRelacionamentoMarketingDigital#PROPOSTA_ENTREGUE
     *
     */
    REALIZAR_BRIFING,
    /**
     *
     * PASSA A EMPRESA PARA O ESTADO DE
     *
     * @see FabTipoRelacionamentoMarketingDigital#VALORES_DO_CONTRATO_NEGOCIADOS
     *
     */
    CRIAR_NOVA_PROPOSTA,
    CRIAR_PROPOSTA,
    CONSULTAR_DADOS,
    DEFINIR_VALORES,
    IMPRIMIR_PROPOSTA,
    ENVIAR_PROPOSTA,
    /**
     * PASSA A EMPRESA PARA ESTADO DE
     *
     * @see FabTipoRelacionamentoMarketingDigital#ATIVO e (cria CLIENTE)
     */
    COLETAR_ASSINATURA_CONTRATO,
    /**
     *
     * PASSA A EMPRESA PARA O ESTADO DE
     *
     * @see FabTipoRelacionamentoMarketingDigital#INATIVO
     *
     */
    CANCELAR_CONTRATO,
    /**
     *
     * PASSA A EMPRESA PARA O ESTADO DE
     *
     * @see FabTipoRelacionamentoMarketingDigital#NAO_INDICADO
     *
     */
    DECLARAR_COMO_INADEQUADO,
    ENVIAR_PROPAGANDA,
    ENVIAR_BOLETO,
    ATIVAR_CAMPANHA,
    AGENDAR_CONTATO,
    DESISTIR_POR6MESES,
    DECLARAR_COMO_NEGOCIACAO_PERDIDA;

    private final TipoAtividadeCRM novoTipoAtividade = new TipoAtividadeCRM();

    @Override
    public TipoAtividadeCRM getRegistro() {

        novoTipoAtividade.setIconeAcao("fa fa-beer");
        novoTipoAtividade.setCor("439fe0");

        switch (this) {
            case CONVERSA_TELEFONE_PASSIVO:
                novoTipoAtividade.setId(1l);
                novoTipoAtividade.setNomeAtividade("Atendimento Telefonico Passivo");
                novoTipoAtividade.setIconeAcao("fa fa-phone");
                novoTipoAtividade.setNomeInicioAtivida("Atender Telefone");
                novoTipoAtividade.setNomeFimAtividaed("Finalizar Atendimento");
                break;
            case CONVERSA_TELEFONE_ATIVO:
                novoTipoAtividade.setId(2l);
                novoTipoAtividade.setIconeAcao("fa fa-bullhorn");
                novoTipoAtividade.setNomeAtividade("Atendimento Telefonico Ativo");
                novoTipoAtividade.setNomeInicioAtivida("Ligar Para Empresa");
                novoTipoAtividade.setNomeFimAtividaed("Finalizar Ligação");
                break;
            case CONVERSA_EMAIL:
                novoTipoAtividade.setId(3l);
                novoTipoAtividade.setIconeAcao("fa fa-envelope");
                novoTipoAtividade.setNomeAtividade("Contato Via E-mail");
                novoTipoAtividade.setNomeInicioAtivida("Criar e-mail");
                novoTipoAtividade.setNomeFimAtividaed("Enviar");

                break;
            case REALIZAR_PRE_ANALIZE:
                novoTipoAtividade.setId(4l);
                novoTipoAtividade.setIconeAcao("fa fa-bar-chart");
                novoTipoAtividade.setNomeAtividade("Realização de Pré análize");
                novoTipoAtividade.setNomeInicioAtivida("Cadastrar Pré Análise");
                novoTipoAtividade.setNomeFimAtividaed("Concluir Pré Análise");
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.PRE_ANALISE_GERADA.getRegistro());
                novoTipoAtividade.setProgresso(true);
                break;
            case ENVIAR_PRE_ANALISE:
                novoTipoAtividade.setId(5l);
                novoTipoAtividade.setIconeAcao("fa fa-pencil-square-o ");
                novoTipoAtividade.setNomeAtividade("Envio de Pré Análize");
                novoTipoAtividade.setNomeInicioAtivida("Enviar Pré Análize");
                novoTipoAtividade.setNomeFimAtividaed("Enviar");
                novoTipoAtividade.setXhtmlAlternativo("atividadesPersonalizadas/enviarPreanalise.xhtml");
                break;
            case REALIZAR_BRIFING:
                novoTipoAtividade.setId(6l);
                novoTipoAtividade.setNomeAtividade("Realização de  Brifing");
                novoTipoAtividade.setNomeInicioAtivida("Realizar Brifing");
                novoTipoAtividade.setNomeFimAtividaed("Finalizar Brifing");
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.PROPOSTA_ENTREGUE.getRegistro());
                novoTipoAtividade.setProgresso(true);
                break;
            case CRIAR_PROPOSTA:
                novoTipoAtividade.setId(7l);
                novoTipoAtividade.setNomeAtividade("Criação de proposta");
                novoTipoAtividade.setNomeInicioAtivida("Montar Proposta");
                novoTipoAtividade.setNomeFimAtividaed("Finalizar Proposta");
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.VALORES_DO_CONTRATO_NEGOCIADOS.getRegistro());
                novoTipoAtividade.setProgresso(true);
                novoTipoAtividade.setXhtmlAlternativo("proposta.xhtml");
                break;
            case CONSULTAR_DADOS:
                novoTipoAtividade.setId(8l);
                novoTipoAtividade.setNomeAtividade("Consulta de dados");
                novoTipoAtividade.setNomeInicioAtivida("ver Prospecto");
                novoTipoAtividade.setNomeFimAtividaed("OK");
                break;
            case DEFINIR_VALORES:
                novoTipoAtividade.setId(9l);
                novoTipoAtividade.setNomeAtividade("Definição de valores");
                novoTipoAtividade.setNomeInicioAtivida("Devinir Valores");
                novoTipoAtividade.setNomeFimAtividaed("Finalizar definição");
                break;
            case IMPRIMIR_PROPOSTA:
                novoTipoAtividade.setId(10l);
                novoTipoAtividade.setNomeAtividade("Impressão de Proposta");
                novoTipoAtividade.setNomeInicioAtivida("Imprimir Proposta");
                novoTipoAtividade.setNomeFimAtividaed("Imprimir");
                break;
            case ENVIAR_PROPOSTA:
                novoTipoAtividade.setId(11l);
                novoTipoAtividade.setNomeAtividade("Envio de proposta");
                novoTipoAtividade.setNomeInicioAtivida("Enviar proposta");
                novoTipoAtividade.setNomeFimAtividaed("Enviar");
                break;
            case ENVIAR_PROPAGANDA:
                novoTipoAtividade.setId(12l);
                novoTipoAtividade.setNomeAtividade("Envio de propaganda");
                novoTipoAtividade.setNomeInicioAtivida("Disparar e-mail Marketing");
                novoTipoAtividade.setNomeFimAtividaed("Enviar");
                break;
            case ENVIAR_BOLETO:
                novoTipoAtividade.setId(13l);
                novoTipoAtividade.setNomeAtividade("Envio de boleto");
                novoTipoAtividade.setNomeInicioAtivida("Enviar Boleto");
                novoTipoAtividade.setNomeFimAtividaed("Enviar");
                novoTipoAtividade.setXhtmlAlternativo("atividadesPersonalizadas/enviarEmail.xhtml");
                break;
            case COLETAR_ASSINATURA_CONTRATO:
                novoTipoAtividade.setId(14l);
                novoTipoAtividade.setNomeAtividade("Assinatura de contrato");
                novoTipoAtividade.setNomeInicioAtivida("Arquivar assinatura de contrato");
                novoTipoAtividade.setNomeFimAtividaed("Arquivar");
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.ATIVO.getRegistro());
                novoTipoAtividade.setProgresso(true);
                break;
            case ATIVAR_CAMPANHA:
                novoTipoAtividade.setId(15l);
                novoTipoAtividade.setNomeAtividade("Ativação de campanhas");
                novoTipoAtividade.setNomeInicioAtivida("Ativar Campanha");
                novoTipoAtividade.setNomeFimAtividaed("Ativar");
                break;
            case AGENDAR_CONTATO:
                novoTipoAtividade.setId(16l);
                novoTipoAtividade.setNomeAtividade("Agenda de novo Contato");
                novoTipoAtividade.setNomeInicioAtivida("Agendar Contato");
                novoTipoAtividade.setNomeFimAtividaed("Agendar");
                break;
            case DESISTIR_POR6MESES:
                novoTipoAtividade.setId(17l);
                novoTipoAtividade.setNomeAtividade("Desistir de Prospecto");
                novoTipoAtividade.setNomeInicioAtivida("Abandonar Prospecto");
                novoTipoAtividade.setNomeFimAtividaed("Abandonar por 6 meses");
                break;
            case CRIAR_PROSPECTO:
                novoTipoAtividade.setId(18l);
                novoTipoAtividade.setNomeAtividade("Criação de prospecto");
                novoTipoAtividade.setNomeInicioAtivida("Cadastrar Prospecto");
                novoTipoAtividade.setNomeFimAtividaed("Cadastrar");
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.QUALIFICADO.getRegistro());
                break;
            case CANCELAR_CONTRATO:
                novoTipoAtividade.setId(19l);
                novoTipoAtividade.setNomeAtividade("Cancelar contrato");
                novoTipoAtividade.setNomeInicioAtivida("Cancelar Contrato");
                novoTipoAtividade.setNomeFimAtividaed("Cancelar");
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.INATIVO.getRegistro());
                novoTipoAtividade.setRegresso(true);
                break;
            case DECLARAR_COMO_INADEQUADO:
                novoTipoAtividade.setId(20l);
                novoTipoAtividade.setNomeAtividade("Declaração como inadequado");
                novoTipoAtividade.setNomeInicioAtivida("Descartar Coleta");
                novoTipoAtividade.setNomeFimAtividaed("Descartar");
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.NAO_INDICADO.getRegistro());
                novoTipoAtividade.setRegresso(true);
                break;
            case CRIAR_NOVA_PROPOSTA:
                novoTipoAtividade.setId(21l);
                novoTipoAtividade.setNomeAtividade("Criar nova Proposta");
                novoTipoAtividade.setNomeInicioAtivida("Modificar Proposta");
                novoTipoAtividade.setNomeFimAtividaed("Salvar modificações");

                break;
            case DECLARAR_COMO_NEGOCIACAO_PERDIDA:
                novoTipoAtividade.setId(22l);
                novoTipoAtividade.setNomeAtividade("Negociação Perdida");
                novoTipoAtividade.setNomeInicioAtivida("Declarar Desistencia");
                novoTipoAtividade.setNomeFimAtividaed("Desistir");
                novoTipoAtividade.setRegresso(true);
                novoTipoAtividade.setRelacionamentoGerado(FabTipoRelacionamentoMarketingDigital.NEGOCIACAO_PERDIDA.getRegistro());
                break;

            default:
                throw new AssertionError(this.name());
        }

        return novoTipoAtividade;
    }

    public static List<TipoAtividadeCRM> getAtividadesIniciais() {
        List<TipoAtividadeCRM> resp = new ArrayList<>();
        resp.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_ATIVO.getRegistro());
        resp.add(FabTipoAtividadeCRM.CONVERSA_TELEFONE_PASSIVO.getRegistro());
        resp.add(FabTipoAtividadeCRM.CONVERSA_EMAIL.getRegistro());
        resp.add(FabTipoAtividadeCRM.CONSULTAR_DADOS.getRegistro());
        return resp;

    }

    public static List<TipoAtividadeCRM> getTodosTipos() {
        List<TipoAtividadeCRM> tipos = new ArrayList<>();
        for (FabTipoAtividadeCRM at : FabTipoAtividadeCRM.values()) {
            tipos.add(at.getRegistro());
        }
        return tipos;
    }

    public List<FabTipoDadoCRM> getDadosObrigatorios() {
        List<FabTipoDadoCRM> dadosObrigatorios = new ArrayList<>();

        switch (this) {
            case CONVERSA_TELEFONE_PASSIVO:
                break;
            case CONVERSA_TELEFONE_ATIVO:
                break;
            case CONVERSA_EMAIL:
                break;
            case CRIAR_PROSPECTO:
                break;
            case REALIZAR_PRE_ANALIZE:
                break;
            case ENVIAR_PRE_ANALISE:

                break;
            case REALIZAR_BRIFING:
                break;
            case CRIAR_NOVA_PROPOSTA:
                break;
            case CONSULTAR_DADOS:
                break;
            case DEFINIR_VALORES:
                break;
            case IMPRIMIR_PROPOSTA:
                break;
            case ENVIAR_PROPOSTA:
                break;
            case COLETAR_ASSINATURA_CONTRATO:
                break;
            case CANCELAR_CONTRATO:
                break;
            case DECLARAR_COMO_INADEQUADO:
                break;
            case ENVIAR_PROPAGANDA:
                break;
            case ENVIAR_BOLETO:
                break;
            case ATIVAR_CAMPANHA:
                break;
            case AGENDAR_CONTATO:
                break;
            case DESISTIR_POR6MESES:
                break;
            default:
                return null;

        }
        return dadosObrigatorios;

    }

}
