/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.Date;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author sfurbino
 */
@InfoObjetoSB(tags = "Pesquisa de atividade", plural = "Pesquisas de atividades", modulo = ERPCrm.NOME_MODULO_ERP)
public class PesquisaAtividade extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampoValorLogico(nomeCalculo = "descrição")
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Origens Disponíveis")
    private List<OrigemProspecto> origensPublicas;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Origens Privadas")
    private List<OrigemProspectoPrivado> origensPrivadas;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Origens Compartilhadas")
    private List<OrigemProspectoPrivado> origensPrivadasCompatilhadas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Origem")
    @InfoCampoValidadorLogico()
    private OrigemProspecto origem;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Estágios disponíveis")
    private List<MetaRelacionamento> metasDisponiveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Origens")
    private TipoRelacionamento tipoRelacionamento;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Tipos de relacionamento")
    private List<TipoRelacionamento> tiposDeRelacionamentoDisponivel;

    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATA)
    @InfoCampoValorLogico(nomeCalculo = "dataInicial")
    @InfoCampoValidadorLogico()
    private Date datainicial = UtilCRCDataHora.decrementarDias(new Date(), 30);

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "momentoAtual")
    private boolean momentoAtual = true;

    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATA)
    @InfoCampoValorLogico(nomeCalculo = "dataInicial")
    @InfoCampoValidadorLogico()
    private Date datafinal = new Date();

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "Texto Intervalo dadas")
    private String textoIntervaloDatas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, nomeOrigem = "Pesquise Aqui", descricao = "Pesquise em meus prospectos, ou em todos os prospectos")
    private String termoPesquisa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "hashUltimaPesquisa")
    private String hashUltimaPesquisa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoRelacionamento relacionamento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private MetaRelacionamento metaRelacionamento;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "usuariosDisponiveis")
    @InfoCampoValidadorLogico()
    private UsuarioCRM usuario;

    @ManyToOne(targetEntity = TagAtendimento.class)
    private TagAtendimento tagAtendimento;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "usuariosDisponiveis")
    private List<UsuarioCRM> usuariosDisponiveis;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Atividades")
    private List<AtividadeCRM> atividadesEncontradas;

    public void limparDatas() {
        setDatafinal(new Date());
        setMomentoAtual(true);
        setDatainicial(null);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<OrigemProspecto> getOrigensPublicas() {
        return origensPublicas;
    }

    public void setOrigensPublicas(List<OrigemProspecto> origensPublicas) {
        this.origensPublicas = origensPublicas;
    }

    public List<OrigemProspectoPrivado> getOrigensPrivadas() {
        return origensPrivadas;
    }

    public void setOrigensPrivadas(List<OrigemProspectoPrivado> origensPrivadas) {
        this.origensPrivadas = origensPrivadas;
    }

    public List<OrigemProspectoPrivado> getOrigensPrivadasCompatilhadas() {
        return origensPrivadasCompatilhadas;
    }

    public void setOrigensPrivadasCompatilhadas(List<OrigemProspectoPrivado> origensPrivadasCompatilhadas) {
        this.origensPrivadasCompatilhadas = origensPrivadasCompatilhadas;
    }

    public OrigemProspecto getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemProspecto origem) {
        this.origem = origem;
    }

    public List<MetaRelacionamento> getMetasDisponiveis() {
        return metasDisponiveis;
    }

    public void setMetasDisponiveis(List<MetaRelacionamento> metasDisponiveis) {
        this.metasDisponiveis = metasDisponiveis;
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

    public List<TipoRelacionamento> getTiposDeRelacionamentoDisponivel() {
        return tiposDeRelacionamentoDisponivel;
    }

    public void setTiposDeRelacionamentoDisponivel(List<TipoRelacionamento> tiposDeRelacionamentoDisponivel) {
        this.tiposDeRelacionamentoDisponivel = tiposDeRelacionamentoDisponivel;
    }

    public Date getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(Date datainicial) {
        this.datainicial = datainicial;
    }

    public boolean isMomentoAtual() {
        return momentoAtual;
    }

    public void setMomentoAtual(boolean momentoAtual) {
        this.momentoAtual = momentoAtual;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public String getTextoIntervaloDatas() {
        return textoIntervaloDatas;
    }

    public void setTextoIntervaloDatas(String textoIntervaloDatas) {
        this.textoIntervaloDatas = textoIntervaloDatas;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;

    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public String getHashUltimaPesquisa() {
        return hashUltimaPesquisa;
    }

    public void setHashUltimaPesquisa(String hashUltimaPesquisa) {
        this.hashUltimaPesquisa = hashUltimaPesquisa;
    }

    public TipoRelacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(TipoRelacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }

    public MetaRelacionamento getMetaRelacionamento() {
        return metaRelacionamento;
    }

    public void setMetaRelacionamento(MetaRelacionamento metaRelacionamento) {
        this.metaRelacionamento = metaRelacionamento;
    }

    public UsuarioCRM getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCRM usuario) {
        this.usuario = usuario;
    }

    public TagAtendimento getTagAtendimento() {
        return tagAtendimento;
    }

    public void setTagAtendimento(TagAtendimento tagAtendimento) {
        this.tagAtendimento = tagAtendimento;
    }

    public List<UsuarioCRM> getUsuariosDisponiveis() {
        return usuariosDisponiveis;
    }

    public void setUsuariosDisponiveis(List<UsuarioCRM> usuariosDisponiveis) {
        this.usuariosDisponiveis = usuariosDisponiveis;
    }

    public List<AtividadeCRM> getAtividadesEncontradas() {
        return atividadesEncontradas;
    }

    public void setAtividadesEncontradas(List<AtividadeCRM> atividadesEncontradas) {
        this.atividadesEncontradas = atividadesEncontradas;
    }

}
