package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.TipoExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoFabricaExecucaoIA;
import br.org.coletivoJava.fw.api.erp.ia.escopo.UtilCRCIAJson;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 * Tipos de execução de IA criados por padrão. Outros tipos podem ser
 * cadastrados pelo usuário diretamente em {@link TipoExecucaoIA}.
 *
 * @author salvio
 */
public enum FabTipoExecucaoIA implements ComoFabricaExecucaoIA {

    @InfoObjetoDaFabrica(classeObjeto = TipoExecucaoIA.class, id = 1, icone = "fa fa-envelope-circle-check", nomeObjeto = "E-mail transacional")
    EMAIL_TRANSACIONAL,
    @InfoObjetoDaFabrica(classeObjeto = TipoExecucaoIA.class, id = 2, icone = "fa fa-envelope-open-text", nomeObjeto = "E-mail marketing")
    EMAIL_MARKETING,
    @InfoObjetoDaFabrica(classeObjeto = TipoExecucaoIA.class, id = 3, icone = "fa fa-chess", nomeObjeto = "Estratégia de marketing")
    ESTRATEGIA_MARKETING,
    @InfoObjetoDaFabrica(classeObjeto = TipoExecucaoIA.class, id = 4, icone = "fa fa-bullseye", nomeObjeto = "Análise de posicionamento de marketing")
    ANALISE_POSICIONAMENTO_MARKETING,
    @InfoObjetoDaFabrica(classeObjeto = TipoExecucaoIA.class, id = 5, icone = "fa fa-bullseye", nomeObjeto = "Análise de posicionamento de marketing")
    EXECUCAO_ATIVIDADE;

    @Override
    public TipoExecucaoIA getRegistro() {
        TipoExecucaoIA tipo = (TipoExecucaoIA) ComoFabricaExecucaoIA.super.getRegistro();

        switch (this) {
            case EMAIL_TRANSACIONAL:
                tipo.setDescricao("E-mail de comunicação operacional com o cliente (confirmação, aviso, lembrete), direto e sem apelo comercial.");
                tipo.setStructuredOutputConfig(UtilCRCIAJson.formato(
                        UtilCRCIAJson.texto("assunto"), UtilCRCIAJson.texto("preheader"), UtilCRCIAJson.texto("corpoHtml"), UtilCRCIAJson.texto("corpoTexto")));
                break;
            case EMAIL_MARKETING:
                tipo.setInstrucaoPrincipal("Assunto :\n"
                        + "Entre 30 e 50 caracteres; nunca mais de 60.\n"
                        + "Específico e honesto: diga o que o e-mail entrega. O conteúdo precisa cumprir o que o assunto promete.\n"
                        + "Pode personalizar com {contactfield=firstname|} quando soar natural, no máximo uma vez.\n"
                        + "Sem CAIXA ALTA, sem mais de um ponto de exclamação, sem emojis em excesso (no máximo um, e só se combinar com o tom).\n"
                        + "\n"
                        + "Conteúdo:\n"
                        + "Uma ideia central por e-mail e uma única chamada para ação principal.\n"
                        + "Estrutura: saudação curta, contexto (1 a 2 parágrafos), benefício, chamada para ação e fechamento.\n"
                        + "Parágrafos de 1 a 3 frases. Frases curtas e diretas.\n"
                        + "Escreva para o leitor (\"você\"), com foco no benefício dele, não nas características do produto.\n"
                        + "O e-mail completo deve ser lido em menos de 1 minuto: entre 80 e 200 palavras na maioria dos casos.\n"
                        + "Português do Brasil, tom profissional e próximo. Sem jargão.\n"
                        + "\n");

                tipo.setDescricao("E-mail de divulgação ou relacionamento, com foco em engajamento e chamada para ação.");
                tipo.setStructuredOutputConfig(UtilCRCIAJson.formato(
                        UtilCRCIAJson.texto("assunto"), UtilCRCIAJson.texto("preheader"), UtilCRCIAJson.texto("corpoHtml"), UtilCRCIAJson.texto("corpoTexto"),
                        UtilCRCIAJson.texto("chamadaParaAcao")));
                break;
            case ESTRATEGIA_MARKETING:
                tipo.setDescricao("Plano de marketing com objetivos, público, canais, ações e indicadores.");
                tipo.setStructuredOutputConfig(UtilCRCIAJson.formato(
                        UtilCRCIAJson.texto("resumo"), UtilCRCIAJson.listaTexto("objetivos"), UtilCRCIAJson.texto("publicoAlvo"), UtilCRCIAJson.listaTexto("canais"),
                        UtilCRCIAJson.listaObjetos("acoes", UtilCRCIAJson.texto("titulo"), UtilCRCIAJson.texto("descricao"), UtilCRCIAJson.texto("prazo")),
                        UtilCRCIAJson.listaTexto("indicadores")));
                break;
            case ANALISE_POSICIONAMENTO_MARKETING:
                tipo.setDescricao("Análise do posicionamento da marca frente ao mercado e concorrentes, com recomendações.");
                tipo.setStructuredOutputConfig(UtilCRCIAJson.formato(
                        UtilCRCIAJson.texto("resumo"), UtilCRCIAJson.listaTexto("pontosFortes"), UtilCRCIAJson.listaTexto("pontosFracos"),
                        UtilCRCIAJson.listaTexto("oportunidades"), UtilCRCIAJson.listaTexto("ameacas"), UtilCRCIAJson.listaTexto("diferenciais"),
                        UtilCRCIAJson.listaTexto("recomendacoes")));
                break;
            case EXECUCAO_ATIVIDADE:
                tipo.setDescricao("Apoio para execução de atividades");
                tipo.setStructuredOutputConfig(UtilCRCIAJson.formato(
                        UtilCRCIAJson.texto("assunto"),
                        UtilCRCIAJson.texto("preheader"),
                        UtilCRCIAJson.texto("corpoHtml"),
                        UtilCRCIAJson.texto("corpoTexto")
                ));
                break;
            default:
                throw new AssertionError(this.name());
        }
        return tipo;
    }

    @Override
    public Class<? extends ComoExecucaoIA> getClasseExecucaoPorEntidade(ComoEntidadeSimples pEntidade) {
        if (pEntidade instanceof ModeloEmailMarketing) {
            return ExecucaoIAModeloEmailMkt.class;
        }
        if (pEntidade instanceof AtividadeCRM) {
            return ExecucaoIAAtividade.class;
        }
        if (pEntidade instanceof ModeloEmail) {
            return ExecucaoIAModeloEmailAtividade.class;
        }

        return null;
    }

}
