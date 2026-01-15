/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ItfMetaLead;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(plural = "Estágios do Lead", tags = "Estágio do lead", icone = "fa fa-handshake-o", generoFeminino = false)
public class MetaRelacionamento extends EntidadeSimplesORM implements ItfMetaLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(label = "codigo")
    private Long id;

    @InfoCampo(label = "", tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(label = "Descritivo cliente")
    private String descricaoEtapaVisaoPrestador;

    @InfoCampo(label = "Descritivo prestador", tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricaoEtapaVisaoCliente;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int peso;

    @InfoCampo(label = "Quantidade", descricao = "Quantidade de Empresas neste estágio")
    @Transient
    private int qtdEmpresasNestaMeta = -1;

    @InfoCampo(tipo = FabTipoAtributoObjeto.COR, label = "Cor")
    private String cor;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean cliente;

    @OneToMany(targetEntity = TipoRelacionamento.class,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "metaRelacionamento")
    private List<TipoRelacionamento> tiposRelacionamento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividade Disponíveis", entidadeOpcoesDisponiveis = TipoAtividadeCRM.class)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TipoAtividadeCRM> tiposAtividadeDisponiveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "tipos atividade updagrade")
    @Transient
    private List<TipoAtividadeCRM> tiposAtividadeUpGrade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "tipos atividade downgrade")
    @Transient
    private List<TipoAtividadeCRM> tiposAtividadeDowGrade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "tipos atividade Grupo novo")
    @Transient
    private List<TipoAtividadeCRM> tiposAtividadeGrupoAtividade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Converter Meta em:", entidadeOpcoesDisponiveis = MetaRelacionamento.class)
    @Transient
    private MetaRelacionamento metaConversao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = Funil.class)
    @InfoCampoValorLogico(nomeCalculo = "Funil")
    private Funil funil;

    @Override
    public Long getId() {
        return id;
    }

    public int getQtdEmpresasNestaMeta() {
        if (qtdEmpresasNestaMeta < 0) {
            Long valor = new ConsultaDinamicaDeEntidade(PessoaJuridica.class, SBCore.getCentralDados().getAcessoDadosDoContexto().getEntitiManager())
                    .addCondicaoManyToOneContemNoIntervalo("relacionamento", getTiposRelacionamento()).resultadoSomarQuantidade();
            qtdEmpresasNestaMeta = valor.intValue();
        }
        return qtdEmpresasNestaMeta;
    }

    public List<TipoRelacionamento> getTiposRelacionamento() {
        return tiposRelacionamento;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public String getCor() {

        if (!UtilCRCStringValidador.isNuloOuEmbranco(cor)) {
            cor = cor.replace("#", "");
        }

        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoEtapaVisaoPrestador() {
        return descricaoEtapaVisaoPrestador;
    }

    public void setDescricaoEtapaVisaoPrestador(String descricaoEtapaVisaoPrestador) {
        this.descricaoEtapaVisaoPrestador = descricaoEtapaVisaoPrestador;
    }

    public String getDescricaoEtapaVisaoCliente() {
        return descricaoEtapaVisaoCliente;
    }

    public void setDescricaoEtapaVisaoCliente(String descricaoEtapaVisaoCliente) {
        this.descricaoEtapaVisaoCliente = descricaoEtapaVisaoCliente;
    }

    @Override
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public List<TipoAtividadeCRM> getTiposAtividadeDisponiveis() {
        return tiposAtividadeDisponiveis;
    }

    public void setTiposAtividadeDisponiveis(List<TipoAtividadeCRM> tiposAtividadeDisponiveis) {
        this.tiposAtividadeDisponiveis = tiposAtividadeDisponiveis;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public MetaRelacionamento getMetaConversao() {
        return metaConversao;
    }

    public void setMetaConversao(MetaRelacionamento metaConversao) {
        this.metaConversao = metaConversao;
    }

    public List<TipoAtividadeCRM> getTiposAtividadeUpGrade() {
        return tiposAtividadeUpGrade;
    }

    public void setTiposAtividadeUpGrade(List<TipoAtividadeCRM> tiposAtividadeUpGrade) {
        this.tiposAtividadeUpGrade = tiposAtividadeUpGrade;
    }

    public List<TipoAtividadeCRM> getTiposAtividadeDowGrade() {
        return tiposAtividadeDowGrade;
    }

    public void setTiposAtividadeDowGrade(List<TipoAtividadeCRM> tiposAtividadeDowGrade) {
        this.tiposAtividadeDowGrade = tiposAtividadeDowGrade;
    }

    public List<TipoAtividadeCRM> getTiposAtividadeGrupoAtividade() {
        return tiposAtividadeGrupoAtividade;
    }

    public void setTiposAtividadeGrupoAtividade(List<TipoAtividadeCRM> tiposAtividadeGrupoAtividade) {
        this.tiposAtividadeGrupoAtividade = tiposAtividadeGrupoAtividade;
    }

    public Funil getFunil() {
        return funil;
    }

    public void setFunil(Funil funil) {
        this.funil = funil;
    }

}
