/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.zonaRelacionamento.FabResultadoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.zonaRelacionamento.ResultadoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.InfoGrupoCampo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 *
 * O tipo de relacionamento não pode conter ligações em tabelas, se tiver
 * precisam ser egger: motivo: é um campo fabricado
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Tipo de Relacionamento ", icone = "fa fa-heart", plural = "Tipos de Relacionamento")
public class TipoRelacionamento extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, valorMaximo = 25)
    private String nome;
    @Column(length = 2000)
    @InfoCampo(label = "Descrição")
    private String descricao;
    @InfoCampo(label = "Nome dado ao prospecto", descricao = "Ex: Qualificado, ou Cliente Ativo")
    private String nomeDoRelacionado;

    @Column(length = 2000)
    @InfoCampo(label = "Dicas", descricao = "As dicas são informações detalhadas sobre a operação deste tipo de relacionamento")
    private String dicas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dadosObrigatorios_tipoRelacionamento",
            joinColumns = @JoinColumn(name = "tipoRelacionamento_id"),
            inverseJoinColumns = @JoinColumn(name = "tipoDadoCRM_id"))
    @InfoGrupoCampo(camposDeclarados = {"nome", "label", "mascara", "fabricaTipoAtributo"})
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    public List<TipoDadoCRM> dadosNescessarios;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividade Permitidas",
            entidadeOpcoesDisponiveis = TipoAtividadeCRM.class
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TipoAtividadeCRM> tiposAtividadeDisponiveis;

    @OneToMany(mappedBy = "tipoAtividade", targetEntity = AtividadeCRM.class)
    private List<AtividadeCRM> atividades;

    @CalculoTipoRelacionamento(calculo = CalculosTipoRelacionamento.QUANTIDADE_ATIVIDADE)
    private Long totalAtividades;

    @Transient
    @CalculoTipoRelacionamento(calculo = CalculosTipoRelacionamento.QUANTIDADE_POR_USUARIO)
    private Long totalAtividadesPorUsuario;
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "Peso do Relacionamento", descricao = "Quanto maior mais o relacionamento tem importância")
    private int peso;
    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String cor;

    @ManyToOne(targetEntity = ResultadoTipoRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, entidadeOpcoesDisponiveis = ResultadoTipoRelacionamento.class)
    private ResultadoTipoRelacionamento resultado;

    @ManyToOne(targetEntity = MetaRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Estágio de relacionameto", descricao = "Os estágios podem ser etapas do funil de negocios.", obrigatorio = true)
    private MetaRelacionamento metaRelacionamento;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "relacionamento_usuarios_responsaveis",
            joinColumns = @JoinColumn(name = "relacionamento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuarioCRM_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, entidadeOpcoesDisponiveis = UsuarioCRM.class)
    private List<UsuarioCRM> usuariosResponsaveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, descricao = "Indica que todos os usuários do prospect devem ser exluidos ao adicionar os novos responsáveis")
    private boolean responsabilidadeexclusiva;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, descricao = "Indica que todos os responsáveis pelo relacionamento serão adicionados")
    private boolean adicionarTodosResponsaveis;

    @InfoCampo(label = "Tempo desejavel neste relacionamento", descricao = "Tempo desejavel neste relacionamento, em Horas, 0 para infinito")
    private int tempoAceitavelResolucao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "Tempo ação automatica por inércia", descricao = "Em caso de inércia, após o tempo aceitável de resulução, executar ação automática")
    private int tempoAcaoInerciaRelacionamento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Novo relacionamento após inércia", descricao = "Após o tempo máximo de inercia, este será o novo relacionamento")
    @ManyToOne(targetEntity = TipoRelacionamento.class)
    private TipoRelacionamento relacionamentoPeranteInercia;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "qtd empresas neste Tipo de Relacionamento")
    @Transient
    @InfoCampoValorLogico(somenteLeitura = true, nomeCalculo = "qtd neste Tipo de Relacionamento")
    private int qtdEmpresasNesteTipoRelacionamento = -1;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Tipo RelacionamentoDestino",
            entidadeOpcoesDisponiveis = TipoRelacionamento.class)
    private TipoRelacionamento tipoRelacionamentoDestino;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Lista de Empresas neste Tipo de Relacionamento")
    @Transient
    @InfoCampoValorLogico(somenteLeitura = true, nomeCalculo = "Empresas neste Tipo de Relacionamento")
    private List<PessoaJuridica> pessoasComEsteRelacionamento;

    @ManyToOne(targetEntity = TipoMensagemMktWhatsApp.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoMensagemMktWhatsApp tipoMensagemWtzp;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, entidadeOpcoesDisponiveis = TipoRelacionamento.class)
    private TipoRelacionamento tipoRelacionamentoConversao;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Ativiades que voltam para metas menores")
    @InfoCampoValorLogico(nomeCalculo = "Atividades que retornam a metas anteriores")
    private List<TipoAtividadeCRM> atividadesDownGradeMeta;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividades que vão para próxima meta")
    @InfoCampoValorLogico(nomeCalculo = "Atividades que levam a próxima meta")
    private List<TipoAtividadeCRM> atividadesUpGradeMeta;

    public int getQtdEmpresasNesteTipoRelacionamento() {

        return qtdEmpresasNesteTipoRelacionamento;
    }

    public void setQtdEmpresasNesteTipoRelacionamento(int qtdEmpresasNesteTipoRelacionamento) {
        this.qtdEmpresasNesteTipoRelacionamento = qtdEmpresasNesteTipoRelacionamento;
    }

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Proximos Reponsaveis")
    public List<UsuarioCRM> proximosResponsaveis;

    public TipoRelacionamento() {

    }

    public List<AtividadeCRM> getAtividades() {
        return atividades;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDicas() {
        return dicas;
    }

    public void setDicas(String dicas) {
        this.dicas = dicas;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNomeDoRelacionado() {
        return nomeDoRelacionado;
    }

    public void setNomeDoRelacionado(String nomeDoRelacionado) {
        this.nomeDoRelacionado = nomeDoRelacionado;
    }

    public String getCor() {
        if (cor == null) {
            return getResultado().getCorResultado();
        }
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public ResultadoTipoRelacionamento getResultado() {
        if (getPeso() == 0) {
            return FabResultadoTipoRelacionamento.NEUTRO.getRegistro();
        }
        if (getPeso() > 1) {
            return FabResultadoTipoRelacionamento.POSITIVO.getRegistro();
        } else {
            return FabResultadoTipoRelacionamento.NEGATIVO.getRegistro();
        }

    }

    public void setResultado(ResultadoTipoRelacionamento resultado) {
        this.resultado = resultado;
    }

    public TipoAtividadeCRM gerarAtividadeGeradoraDeResultado(FabResultadoTipoRelacionamento pTipo) {

        for (TipoAtividadeCRM tipoAtividade : getTiposAtividadeDisponiveis()) {
            if (tipoAtividade.getRelacionamentoGerado().getResultado().equals(pTipo.getRegistro())) {
                return tipoAtividade;
            }
        }
        return null;
    }

    public List<TipoAtividadeCRM> getTiposAtividadeDisponiveis() {
        return tiposAtividadeDisponiveis;
    }

    public void setTiposAtividadeDisponiveis(List<TipoAtividadeCRM> tiposAtividadeDisponiveis) {
        this.tiposAtividadeDisponiveis = tiposAtividadeDisponiveis;
    }

    public List<TipoAtividadeCRM> getAtividadesGeradorasNovoRelacionemtnos() {
        List<TipoAtividadeCRM> atividadesGeradoras = new ArrayList<>();
        for (TipoAtividadeCRM tipoAtividade : getTiposAtividadeDisponiveis()) {
            if (tipoAtividade.getRelacionamentoGerado() != null) {
                atividadesGeradoras.add(tipoAtividade);
            }
        }
        return atividadesGeradoras;
    }

    public List<TipoAtividadeCRM> getAtividadesNAOGeradorasNovoRelacionemtnos() {
        List<TipoAtividadeCRM> atividadesMapGeradoras = new ArrayList<>();
        for (TipoAtividadeCRM tipoAtividade : getTiposAtividadeDisponiveis()) {
            if (tipoAtividade.getRelacionamentoGerado() == null) {
                atividadesMapGeradoras.add(tipoAtividade);
            }
        }
        return atividadesMapGeradoras;
    }

    public boolean isTemAtividadeGeradora() {
        return !getAtividadesGeradorasNovoRelacionemtnos().isEmpty();
    }

    public List<TipoDadoCRM> getDadosNescessarios() {
        return dadosNescessarios;
    }

    public void setDadosNescessarios(List<TipoDadoCRM> dadosNescessarios) {
        this.dadosNescessarios = dadosNescessarios;
    }

    public Long getTotalAtividades() {
        totalAtividades = (Long) CalculosTipoRelacionamento.QUANTIDADE_ATIVIDADE.getValor(this);
        return totalAtividades;
    }

    public Long getTotalAtividadesPorUsuario(UsuarioSB pUsuario) {

        totalAtividadesPorUsuario = (Long) CalculosTipoRelacionamento.QUANTIDADE_POR_USUARIO.getValor(this, pUsuario);
        return totalAtividadesPorUsuario;
    }

    public List<UsuarioCRM> getUsuariosResponsaveis() {
        return usuariosResponsaveis;
    }

    public void setUsuariosResponsaveis(List<UsuarioCRM> usuariosResponsaveis) {
        this.usuariosResponsaveis = usuariosResponsaveis;
    }

    public Long getTotalAtividadesPorUsuario() {
        return totalAtividadesPorUsuario;
    }

    public void setTotalAtividadesPorUsuario(Long totalAtividadesPorUsuario) {
        this.totalAtividadesPorUsuario = totalAtividadesPorUsuario;
    }

    public MetaRelacionamento getMetaRelacionamento() {

        return metaRelacionamento;
    }

    public void setMetaRelacionamento(MetaRelacionamento metaRelacionamento) {
        this.metaRelacionamento = metaRelacionamento;
    }

    public int getTempoAceitavelResolucao() {
        return tempoAceitavelResolucao;
    }

    public void setTempoAceitavelResolucao(int tempoAceitavelResolucao) {
        this.tempoAceitavelResolucao = tempoAceitavelResolucao;
    }

    public int getTempoAcaoInerciaRelacionamento() {
        return tempoAcaoInerciaRelacionamento;
    }

    public void setTempoAcaoInerciaRelacionamento(int tempoAcaoInerciaRelacionamento) {
        this.tempoAcaoInerciaRelacionamento = tempoAcaoInerciaRelacionamento;
    }

    public TipoRelacionamento getRelacionamentoPeranteInercia() {
        return relacionamentoPeranteInercia;
    }

    public void setRelacionamentoPeranteInercia(TipoRelacionamento relacionamentoPeranteInercia) {
        this.relacionamentoPeranteInercia = relacionamentoPeranteInercia;
    }

    public boolean isResponsabilidadeexclusiva() {
        return responsabilidadeexclusiva;
    }

    public void setResponsabilidadeexclusiva(boolean responsabilidadeexclusiva) {
        this.responsabilidadeexclusiva = responsabilidadeexclusiva;
    }

    public boolean isAdicionarTodosResponsaveis() {
        return adicionarTodosResponsaveis;
    }

    public void setAdicionarTodosResponsaveis(boolean adicionarTodosResponsaveis) {
        this.adicionarTodosResponsaveis = adicionarTodosResponsaveis;
    }

    public TipoRelacionamento getTipoRelacionamentoDestino() {
        return tipoRelacionamentoDestino;
    }

    public void setTipoRelacionamentoDestino(TipoRelacionamento tipoRelacionamentoDestino) {
        this.tipoRelacionamentoDestino = tipoRelacionamentoDestino;
    }

    public List<PessoaJuridica> getPessoasComEsteRelacionamento() {
        return pessoasComEsteRelacionamento;
    }

    public void setPessoasComEsteRelacionamento(List<PessoaJuridica> pessoasComEsteRelacionamento) {
        this.pessoasComEsteRelacionamento = pessoasComEsteRelacionamento;
    }

    public TipoMensagemMktWhatsApp getTipoMensagemWtzp() {
        return tipoMensagemWtzp;
    }

    public void setTipoMensagemWtzp(TipoMensagemMktWhatsApp tipoMensagemWtzp) {
        this.tipoMensagemWtzp = tipoMensagemWtzp;
    }

    @Override
    public Long getId() {
        return id;
    }

    public TipoRelacionamento getTipoRelacionamentoConversao() {
        return tipoRelacionamentoConversao;
    }

    public void setTipoRelacionamentoConversao(TipoRelacionamento tipoRelacionamentoConversao) {
        this.tipoRelacionamentoConversao = tipoRelacionamentoConversao;
    }

    public List<TipoAtividadeCRM> getAtividadesDownGradeMeta() {
        return atividadesDownGradeMeta;
    }

    public void setAtividadesDownGradeMeta(List<TipoAtividadeCRM> atividadesDownGradeMeta) {
        this.atividadesDownGradeMeta = atividadesDownGradeMeta;
    }

    public List<TipoAtividadeCRM> getAtividadesUpGradeMeta() {
        return atividadesUpGradeMeta;
    }

    public void setAtividadesUpGradeMeta(List<TipoAtividadeCRM> atividadesUpGradeMeta) {
        this.atividadesUpGradeMeta = atividadesUpGradeMeta;
    }

    public List<UsuarioCRM> getProximosResponsaveis() {
        return proximosResponsaveis;
    }

    public void setProximosResponsaveis(List<UsuarioCRM> proximosResponsaveis) {
        this.proximosResponsaveis = proximosResponsaveis;
    }

}
