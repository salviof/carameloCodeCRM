package br.org.carameloCode.erp.modulo.crm.entidadesJPA.persona;

import br.org.coletivoJava.fw.api.erp.ia.escopo.ItfPersona;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@InfoObjetoSB(plural = "Personas", tags = "Persona", icone = "fa fa-users")
public class Persona extends EntidadeSimples implements ItfPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Curta descrição que define a persona de forma clara.", label = "Descrição")
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Define o tom de voz da persona (ex.: amigável, profissional, formal, casual).", label = "Tonalidade da resposta")
    private String tonalidade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Define o objetivo da persona (ex.: resolver problemas, ajudar outras pessoas, etc.).", label = "Objetivo da resposta")
    private String objetivo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Regras para dizer quais as regras que a IA deve seguir", label = "Regras de Resposta")
    private String regrasResposta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, descricao = "Define o limite de palavras que a resposta pode ter.", label = "Limite de palavras")
    private int limitePalavras;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Contexto da interação, como área de atuação da persona ou detalhes relevantes.", label = "Contexto")
    private String contexto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Instruções especiais para guiar a IA em cenários específicos.", label = "Instruções Adicionais")
    private String instrucoesAdicionais;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Define o publico alvo quem a persona está se dirigindo (ex: possiveis clientes, criancas, idosos, adultos). Isso pode influenciar a linguagem e a complexidade da resposta. ")
    private String publicoAlvo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Idioma padrão em que a persona interagirá.", label = "Idioma Padrão")
    private String idioma;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Tipos de respostas padrões que a IA irá fornecer de acordo com certas perguntas", label = "Tipos de resposta padrao")
    private String tipoRespostasPadrao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Palavras bloqueadas ou proibidas", label = "Palavras proibidas")
    private String palavrasProibidas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, descricao = "Definicao se a Persona está ativa ou inativa", label = "Status Persona")
    private boolean status;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Tipo da persona (ex: interna, externa, buyer persona)", label = "Tipo Persona")
    private String tipoPersona;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Texto do model file que será usado pela IA", label = "Texto Model File IA")
    private String textoModelFileIA;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getTonalidade() {
        return tonalidade;
    }

    @Override
    public void setTonalidade(String tonalidade) {
        this.tonalidade = tonalidade;
    }

    @Override
    public String getObjetivo() {
        return objetivo;
    }

    @Override
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public String getRegrasResposta() {
        return regrasResposta;
    }

    @Override
    public void setRegrasResposta(String regrasResposta) {
        this.regrasResposta = regrasResposta;
    }

    @Override
    public int getLimitePalavras() {
        return limitePalavras;
    }

    @Override
    public void setLimitePalavras(int limitePalavras) {
        this.limitePalavras = limitePalavras;
    }

    @Override
    public String getContexto() {
        return contexto;
    }

    @Override
    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    @Override
    public String getInstrucoesAdicionais() {
        return instrucoesAdicionais;
    }

    @Override
    public void setInstrucoesAdicionais(String instrucoesAdicionais) {
        this.instrucoesAdicionais = instrucoesAdicionais;
    }

    @Override
    public String getTipoPersona() {
        return tipoPersona;
    }

    @Override
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    @Override
    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    @Override
    public String getIdioma() {
        return idioma;
    }

    @Override
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String getTipoRespostasPadrao() {
        return tipoRespostasPadrao;
    }

    @Override
    public void setTipoRespostasPadrao(String tipoRespostasPadrao) {
        this.tipoRespostasPadrao = tipoRespostasPadrao;
    }

    @Override
    public String getPalavrasProibidas() {
        return palavrasProibidas;
    }

    @Override
    public void setPalavrasProibidas(String palavrasProibidas) {
        this.palavrasProibidas = palavrasProibidas;
    }

    @Override
    public boolean isStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getTextoModelFileIA() {
        return textoModelFileIA;
    }

    @Override
    public void setTextoModelFileIA(String s) {
        this.textoModelFileIA = s;
    }
}
