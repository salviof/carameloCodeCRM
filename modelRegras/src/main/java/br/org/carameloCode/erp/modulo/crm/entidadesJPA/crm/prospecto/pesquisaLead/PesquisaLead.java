/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Pesquisa Lead", plural = "Pesquisas de Leads", icone = "fa fa-briefcase", fabricaVinculada = FabTipoPesquisaLeads.class)
public class PesquisaLead extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(targetEntity = OrigemProspecto.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Origem")
    @InfoCampoValidadorLogico()
    private OrigemProspecto origem;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Metas disponíveis")
    private List<MetaRelacionamento> metasDisponiveis;

    @ManyToOne(targetEntity = OrigemProspecto.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Origens")
    private TipoRelacionamento tipoRelacionamento;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Tipos de relacionamento")
    private List<TipoRelacionamento> tiposDeRelacionamentoDisponivel;

    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATA)
    @InfoCampoValorLogico(nomeCalculo = "dataInicial")
    @InfoCampoValidadorLogico()
    private Date datainicial;

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

    @ManyToOne
    @InfoCampo(fabricaDeOpcoes = FabTipoPesquisaLeads.class)
    @InfoCampoValorLogico(nomeCalculo = "tipoPesquisa")
    @InfoCampoValidadorLogico()
    private TipoPesquisaLead tipoPesquisa;

    @OneToMany
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "tiposDisponiveis")
    private List<TipoPesquisaLead> tiposDisponiveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, nomeOrigem = "Pesquise Aqui", descricao = "Pesquise em meus prospectos, ou em todos os prospectos")
    private String termoPesquisa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "hashUltimaPesquisa")
    private String hashUltimaPesquisa;

    @ManyToOne(targetEntity = TipoRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoRelacionamento relacionamento;

    @ManyToOne(targetEntity = MetaRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private MetaRelacionamento metaRelacionamento;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "usuariosDisponiveis")
    @InfoCampoValidadorLogico()
    private UsuarioCRM usuario;

    @ManyToOne(targetEntity = TagAtendimento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "tagsdisponiveis")
    private TagAtendimento tagAtendimento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "tagsDisponiveis")
    @Transient
    private List<TagAtendimento> tagsDisponiveis;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "usuariosDisponiveis")
    private List<UsuarioCRM> usuariosDisponiveis;

    @InfoCampoValorLogico(nomeCalculo = "quantidadeLeadsUrgentes")
    @Transient
    private int quantidadeLeadsUrgentes;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "leads")
    private List<Pessoa> leadsEncontrados;

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

    public Date getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(Date datainicial) {
        this.datainicial = datainicial;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
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

    public List<Pessoa> getLeadsEncontrados() {
        return leadsEncontrados;
    }

    public void setLeadsEncontrados(List<Pessoa> leadsEncontrados) {
        this.leadsEncontrados = leadsEncontrados;
    }

    public List<UsuarioCRM> getUsuariosDisponiveis() {
        return usuariosDisponiveis;
    }

    public void setUsuariosDisponiveis(List<UsuarioCRM> usuariosDisponiveis) {
        this.usuariosDisponiveis = usuariosDisponiveis;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public String getHashUltimaPesquisa() {
        return hashUltimaPesquisa;
    }

    public void setHashUltimaPesquisa(String hashUltimaPesquisa) {
        this.hashUltimaPesquisa = hashUltimaPesquisa;
    }

    public TagAtendimento getTagAtendimento() {
        return tagAtendimento;
    }

    public void setTagAtendimento(TagAtendimento tagAtendimento) {
        this.tagAtendimento = tagAtendimento;
    }

    public List<TipoPesquisaLead> getTiposDisponiveis() {
        return tiposDisponiveis;
    }

    public void setTiposDisponiveis(List<TipoPesquisaLead> tiposDisponiveis) {
        this.tiposDisponiveis = tiposDisponiveis;
    }

    public TipoPesquisaLead getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(TipoPesquisaLead tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }

    public boolean isMomentoAtual() {
        return momentoAtual;
    }

    public void setMomentoAtual(boolean momentoAtual) {
        this.momentoAtual = momentoAtual;
    }

    public List<OrigemProspecto> getOrigensPublicas() {
        return origensPublicas;
    }

    public void setOrigensPublicas(List<OrigemProspecto> origensPublicas) {
        this.origensPublicas = origensPublicas;
    }

    public OrigemProspecto getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemProspecto origem) {
        this.origem = origem;
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

    public List<MetaRelacionamento> getMetasDisponiveis() {
        return metasDisponiveis;
    }

    public void setMetasDisponiveis(List<MetaRelacionamento> metasDisponiveis) {
        this.metasDisponiveis = metasDisponiveis;
    }

    public void limparMeta() {
        metaRelacionamento = null;
    }

    public void limparTipoRelacionamento() {
        tipoRelacionamento = null;
    }

    public void limparOrigem() {
        origem = null;
    }

    public int getQuantidadeLeadsUrgentes() {
        return quantidadeLeadsUrgentes;
    }

    public void setQuantidadeLeadsUrgentes(int quantidadeLeadsUrgentes) {
        this.quantidadeLeadsUrgentes = quantidadeLeadsUrgentes;
    }

    public String getTextoIntervaloDatas() {
        return textoIntervaloDatas;
    }

    public void setTextoIntervaloDatas(String textoIntervaloDatas) {
        this.textoIntervaloDatas = textoIntervaloDatas;
    }

    public List<TagAtendimento> getTagsDisponiveis() {
        return tagsDisponiveis;
    }

    public void setTagsDisponiveis(List<TagAtendimento> tagsDisponiveis) {
        this.tagsDisponiveis = tagsDisponiveis;
    }

}
