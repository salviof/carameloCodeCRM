/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos;

import br.org.coletivoJava.fw.api.erp.ia.escopo.ERP_IA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Tipo dado Gerado por IA", plural = "Tipo dado Gerado por ia")
public class TipoDadoIA extends TipoDadoCRM {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA, fabricaDeOpcoes = ERP_IA.class)
    private ERP_IA provedorIA;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Modelo de IA que será utilizado. Modelos mais avançados tendem a gerar respostas melhores, porém com maior custo.")
    private String model;

    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL, descricao = "Controla o nível de variação das respostas. Valores baixos geram respostas mais previsíveis; valores altos tornam o texto mais criativo.")
    private double temperatura;

    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL, descricao = "Limita o conjunto de palavras consideradas pelo modelo. Quanto menor o valor, mais restritas e seguras tendem a ser as respostas.")
    private double top_p;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, descricao = "Quantidade máxima de tokens (palavras aproximadas) que a IA pode gerar na resposta. Limita custo e tamanho do texto.")
    private int maximoTokens;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, descricao = "Tempo máximo de espera pela resposta da IA. Evita travamentos caso o serviço esteja lento ou indisponível.")
    private int timeout;

    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL, descricao = "Penaliza a repetição de ideias já mencionadas. Valores maiores incentivam a introdução de novos tópicos.")
    private double presence_penalty;
    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL, descricao = "Penaliza a repetição excessiva de palavras ou frases. Ajuda a evitar textos redundantes ou circulares.")
    private double frequency_penalty;
    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL, descricao = "Define quanto do histórico da conversa será enviado para a IA. Afeta coerência, custo e uso de tokens.")
    private double context_policy;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Modelo alternativo utilizado automaticamente caso o modelo principal falhe, fique indisponível ou ultrapasse limites.")
    private String fallback_model;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Limite mensal de tokens permitido para este tipo de uso. Ao atingir o limite, novas requisições podem ser bloqueadas ou redirecionadas.")
    private String monthly_token_limit;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean ativo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Conteúdo pergunta, para informar a ia com todos os dados do cliente utilize a tag [dadosLead], para incluir dados do lead, e todo historico de conversas, utilize [dadosLeadHistorico]")
    @Column(length = 5000)
    private String conteudoPerguntaIA;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Em caso de falha com ia e falha ou nulo no tipo dado logica, entra o  texto alternativo, aceita valores chave como [nome] ou [conteudo] [documeto] e dados dinamicos com: [dados.nomeDado]")
    private String conteudoAlternativoCasoFalha;

    @ManyToOne(targetEntity = TipoDadoCRMLogica.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Em caso de falha, um tipo de dado logico pode ser acionado, em caso de falha o conteúdo de texto será utilizado")
    private TipoDadoCRMLogica tipoDadoLogicaCasoFalha;

    public ERP_IA getProvedorIA() {
        return provedorIA;
    }

    public void setProvedorIA(ERP_IA provedorIA) {
        this.provedorIA = provedorIA;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getTop_p() {
        return top_p;
    }

    public void setTop_p(double top_p) {
        this.top_p = top_p;
    }

    public int getMaximoTokens() {
        return maximoTokens;
    }

    public void setMaximoTokens(int maximoTokens) {
        this.maximoTokens = maximoTokens;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public double getPresence_penalty() {
        return presence_penalty;
    }

    public void setPresence_penalty(double presence_penalty) {
        this.presence_penalty = presence_penalty;
    }

    public double getFrequency_penalty() {
        return frequency_penalty;
    }

    public void setFrequency_penalty(double frequency_penalty) {
        this.frequency_penalty = frequency_penalty;
    }

    public double getContext_policy() {
        return context_policy;
    }

    public void setContext_policy(double context_policy) {
        this.context_policy = context_policy;
    }

    public String getFallback_model() {
        return fallback_model;
    }

    public void setFallback_model(String fallback_model) {
        this.fallback_model = fallback_model;
    }

    public String getMonthly_token_limit() {
        return monthly_token_limit;
    }

    public void setMonthly_token_limit(String monthly_token_limit) {
        this.monthly_token_limit = monthly_token_limit;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getConteudoAlternativoCasoFalha() {
        return conteudoAlternativoCasoFalha;
    }

    public void setConteudoAlternativoCasoFalha(String conteudoAlternativoCasoFalha) {
        this.conteudoAlternativoCasoFalha = conteudoAlternativoCasoFalha;
    }

    public TipoDadoCRMLogica getTipoDadoLogicaCasoFalha() {
        return tipoDadoLogicaCasoFalha;
    }

    public void setTipoDadoLogicaCasoFalha(TipoDadoCRMLogica tipoDadoLogicaCasoFalha) {
        this.tipoDadoLogicaCasoFalha = tipoDadoLogicaCasoFalha;
    }

    public String getConteudoPerguntaIA() {
        return conteudoPerguntaIA;
    }

    public void setConteudoPerguntaIA(String conteudoPerguntaIA) {
        this.conteudoPerguntaIA = conteudoPerguntaIA;
    }

}
