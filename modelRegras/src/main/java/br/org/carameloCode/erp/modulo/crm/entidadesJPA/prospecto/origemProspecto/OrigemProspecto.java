/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 *
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Origem Prospecto", plural = "Origens de Prospecto", icone = "fa fa-address-book-o")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoOrigem")
public class OrigemProspecto extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, label = "ID", nomeOrigem = "Código Referência", somenteLeitura = true)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Nome", obrigatorio = true)
    @Column(unique = true)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, label = "Descrição", obrigatorio = true, descricao = "Descreva de forma reduzida os métodos utilizados para encontrar os Lead serão inseridas nesta origem")
    private String descricao;

    @ManyToOne(targetEntity = TipoRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Relacionamento inicial")
    private TipoRelacionamento relacionamentoPadrao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Pessoa.class, mappedBy = "origem")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<PessoaJuridica> prospectos;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoOrigem;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "privado")
    private boolean umaOrigemPrivada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "publico")
    private boolean umaOrigempublica;

    @InfoCampoValorLogico(nomeCalculo = "leads")
    private long quantidadeLeads;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "meusleadsnestaOrgem")
    private long quantidadeMeusLeads;

    @InfoCampoValorLogico(nomeCalculo = "leadAtivo")
    private long qtdLeadsAtivos;

    @InfoCampoValorLogico(nomeCalculo = "leadInativo")
    private long qtdLeadsInativos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "origens_usuarioCRM",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "origem_id")
    )
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<UsuarioCRM> usuariosComAcesso;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    private boolean ativo;

    public List<PessoaJuridica> getProspectos() {
        return prospectos;
    }

    public void setProspectos(List<PessoaJuridica> prospectos) {
        this.prospectos = prospectos;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public TipoRelacionamento getRelacionamentoPadrao() {
        return relacionamentoPadrao;
    }

    public void setRelacionamentoPadrao(TipoRelacionamento relacionamentoPadrao) {
        this.relacionamentoPadrao = relacionamentoPadrao;
    }

    public List<UsuarioCRM> getUsuariosComAcesso() {
        return usuariosComAcesso;
    }

    public void setUsuariosComAcesso(List<UsuarioCRM> usuariosComAcesso) {
        this.usuariosComAcesso = usuariosComAcesso;
    }

    public String getTipoOrigem() {
        return tipoOrigem;
    }

    public void setTipoOrigem(String tipoOrigem) {
        this.tipoOrigem = tipoOrigem;
    }

    public boolean isUmaOrigemPrivada() {
        return umaOrigemPrivada;
    }

    public void setUmaOrigemPrivada(boolean umaOrigemPrivada) {
        this.umaOrigemPrivada = umaOrigemPrivada;
    }

    public boolean isUmaOrigempublica() {
        return umaOrigempublica;
    }

    public void setUmaOrigempublica(boolean umaOrigempublica) {
        this.umaOrigempublica = umaOrigempublica;
    }

    public long getQuantidadeLeads() {
        return quantidadeLeads;
    }

    public void setQuantidadeLeads(long quantidadeLeads) {
        this.quantidadeLeads = quantidadeLeads;
    }

    public long getQtdLeadsAtivos() {
        return qtdLeadsAtivos;
    }

    public void setQtdLeadsAtivos(long qtdLeadsAtivos) {
        this.qtdLeadsAtivos = qtdLeadsAtivos;
    }

    public long getQtdLeadsInativos() {
        return qtdLeadsInativos;
    }

    public void setQtdLeadsInativos(long qtdLeadsInativos) {
        this.qtdLeadsInativos = qtdLeadsInativos;
    }

    public long getQuantidadeMeusLeads() {
        return quantidadeMeusLeads;
    }

    public void setQuantidadeMeusLeads(long quantidadeMeusLeads) {
        this.quantidadeMeusLeads = quantidadeMeusLeads;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
