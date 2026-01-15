/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.MetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.SolicitacaoAcessoCard;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposIntranetCasaNova;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estatisticas.EstatisticasDoUsuario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.annotations.Where;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Usuário CRM "}, icone = "fa fa-user", plural = "Usuários da Central",
        modulo = ERPCrm.NOME_MODULO_ERP)
public class UsuarioCRM extends UsuarioSB {

    public UsuarioCRM() {
        super();
    }

    @Override
    public void prepararNovoObjeto(Object... parametros) {
        try {
            super.prepararNovoObjeto();
            setGrupo((ComoGrupoUsuario) UtilSBPersistencia.loadEntidade(FabGruposIntranetCasaNova.CRM_ATENDIMENTO.getRegistro(), UtilSBPersistencia.getNovoEM()));
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro preparando objeto UsuarioCRM", t);
        }
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prospectos_usuarios_responsaveis",
            joinColumns = @JoinColumn(name = "usuarioCRM_id"),
            inverseJoinColumns = @JoinColumn(name = "prospecto_id"))
    @OrderBy(value = "dataHoraAlterouProspecto desc")
    private List<PessoaJuridica> prospectos;

    @OneToMany(mappedBy = "usuarioDono", targetEntity = OrigemProspectoPrivado.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<OrigemProspectoPrivado> origens;

    @InfoCampo(label = "Lembrar Mais tarde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraLembrarMaisTardeAtividadesInacabadas;

    @ManyToOne(targetEntity = AreaTrabalho.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @InfoCampoValorLogico(nomeCalculo = "Area Padrao")
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "areasDisponiveis")
    private AreaTrabalho areatrabalhoPadrao;

    @OneToMany(mappedBy = "usuario", targetEntity = AreaTrabalho.class, cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<AreaTrabalho> areasDisponiveis;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Prospectos do usuário com peso")
    private List<PessoaJuridica> prospectosRelacionamentoComPeso;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Prospectos do usuário com peso")
    private List<PessoaJuridica> prospectosRelacionamentoSemPeso;

    @ListaProspectos(lista = ListasProspectos.MEUS_PROSPECTOS_ATIVOS)
    @Transient
    private List<PessoaJuridica> meusProspectosAtivos;

    @ListaProspectos(lista = ListasProspectos.MEUS_PROSPECTOS_EM_NEGOCIACAO)
    @Transient
    private List<PessoaJuridica> meusProspectosEmNegociacao;

    @ListaProspectos(lista = ListasProspectos.MEUS_PROSPECTOS_COM_ATIVIDADES_PENDENTES)
    @Transient
    private List<PessoaJuridica> meusProspectosComAtivadadePendentes;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividades não Finalizadas")
    @OneToMany(mappedBy = "usuarioAtividade")
    @Where(clause = "statusAtividade_id = " + FabStatusAtividade.idEmAndamento)
    @OrderBy(value = "dataHoraRealizacao desc")
    public List<AtividadeCRM> atividadeNaoFinalizada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividades AgendadasHojes")
    @OneToMany(mappedBy = "usuarioAtividade", targetEntity = AtividadeCRM.class)
    @Where(clause = "statusAtividade_id=" + FabStatusAtividade.idAgendado + " and DATE(dataHoraPrevisaoExecucao) = CURDATE() "
            + " and tipoEntidade='AtividadeCRM' ")
    public List<AtividadeCRM> atividadeParaHoje;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividades AgendadasHojes")
    @OneToMany(mappedBy = "usuarioAtividade")
    @Where(clause = "dataHoraPrevisaoExecucao < CURDATE() and statusAtividade_id = " + FabStatusAtividade.idAgendado)
    public List<AtividadeCRM> atividadeAgendadaNaoRealizada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividades Agendadas")
    @OneToMany(mappedBy = "usuarioAtividade")
    @Where(clause = "statusAtividade_id = " + FabStatusAtividade.idAgendado)
    public List<AtividadeCRM> atividadeAgendada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean podeAcessarTodosProspectos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, label = "Assinatura E-mail")
    @Column(length = 8000, columnDefinition = "Text")
    @InfoCampoValorLogico(nomeCalculo = "AssinaturaEmail", atualizarSempreQueSalvar = false)
    private String assinaturaEmail;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Atividaes Principais")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "atividadePrincipal_UsuarioCrm",
            joinColumns = @JoinColumn(name = "acesso_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<TipoAtividadeCRM> atividadesPrincipais;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UsuarioCRM_TagsAtendimento",
            joinColumns = @JoinColumn(name = "usuarioCRM_id"),
            inverseJoinColumns = @JoinColumn(name = "tagAtendimento_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Tags Monitoradas")
    private List<TagAtendimento> tagsMonitoradas;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Estatisticas", atualizarSempreQueSalvar = false)
    private EstatisticasDoUsuario estatisticas;

    @ManyToOne(cascade = CascadeType.ALL)
    @InfoCampoValorLogico(nomeCalculo = "contatovinculados")
    private ContatoColaborador contatoVinculado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "CaixaPostal")
    @ManyToOne(cascade = CascadeType.ALL)
    private CaixaPostal caixaPostalPrincipal;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "caixaPostal_UsuarioCRM",
            joinColumns = @JoinColumn(name = "caixaPostal_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    @InfoCampoValorLogico(nomeCalculo = "Caixas postais ")
    private List<CaixaPostal> caixasPostais;

    @OneToMany(mappedBy = "responsavel")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ContatoPessoal> contatosPrivados;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "metaDadosAtendimento", atualizarSempreQueSalvar = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private MetadadoAtendente metadadosAtendente;

    @OneToMany(targetEntity = Orcamento.class, fetch = FetchType.LAZY,
            mappedBy = "usuariocriou")
    private List<Orcamento> orcamentos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "EscopoAgendaCliente", atualizarSempreQueSalvar = false)
    @ManyToOne(targetEntity = EscopoPesquisaMelhorHorario.class, cascade = CascadeType.ALL)
    private EscopoPesquisaMelhorHorario escopoAgendaClientes;

    @OneToMany(targetEntity = Solicitacao.class, mappedBy = "usuarioSolicitado")
    @Where(clause = "finalizado is false")
    private List<Solicitacao> solicitacoesAguardando;

    @OneToMany(targetEntity = SolicitacaoAcessoCard.class, mappedBy = "usuarioSolicitado")
    @Where(clause = "finalizado is false")
    private List<Solicitacao> solicitacoesLiberarCard;

    @OneToMany(targetEntity = Solicitacao.class, mappedBy = "usuarioSolicitante")
    @Where(clause = "finalizado is false")
    private List<Solicitacao> solicitacoesFeitas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String codigoMatrix;

    public List<PessoaJuridica> getProspectos() {
        return prospectos;
    }

    public List<PessoaJuridica> getMeusProspectosAtivos() {
        this.meusProspectosAtivos = ListasProspectos.MEUS_PROSPECTOS_ATIVOS.getLista(meusProspectosAtivos);
        return meusProspectosAtivos;
    }

    public EscopoPesquisaMelhorHorario getEscopoAgendaClientes() {
        return escopoAgendaClientes;
    }

    public void setEscopoAgendaClientes(EscopoPesquisaMelhorHorario escopoAgendaClientes) {
        this.escopoAgendaClientes = escopoAgendaClientes;
    }

    public List<PessoaJuridica> getMeusProspectosEmNegociacao() {
        if (this.meusProspectosEmNegociacao == null) {
            this.meusProspectosEmNegociacao = ListasProspectos.MEUS_PROSPECTOS_EM_NEGOCIACAO.getLista(this);
        }

        return meusProspectosEmNegociacao;
    }

    public List<PessoaJuridica> getMeusProspectosComAtivadadePendentes() {
        if (this.meusProspectosEmNegociacao == null) {
            this.meusProspectosComAtivadadePendentes = ListasProspectos.MEUS_PROSPECTOS_COM_ATIVIDADES_PENDENTES.getLista(this);
        }
        return meusProspectosComAtivadadePendentes;
    }

    public boolean isPodeAcessarTodosProspectos() {
        return podeAcessarTodosProspectos;
    }

    public void setPodeAcessarTodosProspectos(boolean podeAcessarTodosProspectos) {
        this.podeAcessarTodosProspectos = podeAcessarTodosProspectos;
    }

    public List<AtividadeCRM> getAtividadeNaoFinalizada() {
        return atividadeNaoFinalizada;
    }

    public String getAssinaturaEmail() {
        return assinaturaEmail;
    }

    public List<PessoaJuridica> getProspectosRelacionamentoComPeso() {

        return prospectosRelacionamentoComPeso;
    }

    public void setAssinaturaEmail(String assinaturaEmail) {
        this.assinaturaEmail = assinaturaEmail;
    }

    public List<AtividadeCRM> getAtividadeAgendada() {
        return atividadeAgendada;
    }

    public List<AtividadeCRM> getAtividadeParaHoje() {
        return atividadeParaHoje;
    }

    public List<AtividadeCRM> getAtividadeAgendadaNaoRealizada() {
        return atividadeAgendadaNaoRealizada;
    }

    public List<TipoAtividadeCRM> getAtividadesPrincipais() {
        return atividadesPrincipais;
    }

    public Date getDataHoraLembrarMaisTardeAtividadesInacabadas() {
        return dataHoraLembrarMaisTardeAtividadesInacabadas;
    }

    public void setDataHoraLembrarMaisTardeAtividadesInacabadas(Date dataHoraLembrarMaisTardeAtividadesInacabadas) {
        this.dataHoraLembrarMaisTardeAtividadesInacabadas = dataHoraLembrarMaisTardeAtividadesInacabadas;
    }
    @Transient
    private final Map<Long, Integer> calculosMeta = new HashMap();

    public int getEmpresasNestaMeta(MetaRelacionamento pMeta) {
        if (calculosMeta.get(pMeta.getId()) == null) {
            Long qtd = new ConsultaDinamicaDeEntidade(PessoaJuridica.class, SBCore.getCentralDados().getAcessoDadosDoContexto().getEntitiManager())
                    .addCondicaoManyToOneIgualA(pMeta)
                    .addCondicaoManyToManyContendoObjeto("usuariosResponsaveis", this)
                    .resultadoSomarQuantidade();

            calculosMeta.put(pMeta.getId(), qtd.intValue());
            return qtd.intValue();
        }
        return 0;
    }

    public List<TagAtendimento> getTagsMonitoradas() {
        return tagsMonitoradas;
    }

    public void setTagsMonitoradas(List<TagAtendimento> tagsMonitoradas) {
        this.tagsMonitoradas = tagsMonitoradas;
    }

    public EstatisticasDoUsuario getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(EstatisticasDoUsuario estatisticas) {
        this.estatisticas = estatisticas;
    }

    public void setProspectosRelacionamentoComPeso(List<PessoaJuridica> prospectosRelacionamentoComPeso) {
        this.prospectosRelacionamentoComPeso = prospectosRelacionamentoComPeso;
    }

    public AreaTrabalho getAreatrabalhoPadrao() {
        return areatrabalhoPadrao;
    }

    public void setAreatrabalhoPadrao(AreaTrabalho areatrabalhoPadrao) {
        this.areatrabalhoPadrao = areatrabalhoPadrao;
    }

    public List<AreaTrabalho> getAreasDisponiveis() {
        return areasDisponiveis;
    }

    public void setAreasDisponiveis(List<AreaTrabalho> areasDisponiveis) {
        this.areasDisponiveis = areasDisponiveis;
    }

    public ContatoColaborador getContatoVinculado() {
        return contatoVinculado;
    }

    public void setContatoVinculado(ContatoColaborador contatoVinculado) {
        this.contatoVinculado = contatoVinculado;
    }

    public CaixaPostal getCaixaPostalPrincipal() {
        return caixaPostalPrincipal;
    }

    public void setCaixaPostalPrincipal(CaixaPostal caixaPostalPrincipal) {
        this.caixaPostalPrincipal = caixaPostalPrincipal;
    }

    public List<CaixaPostal> getCaixasPostais() {
        return caixasPostais;
    }

    public void setCaixasPostais(List<CaixaPostal> caixasPostais) {
        this.caixasPostais = caixasPostais;
    }

    public List<PessoaJuridica> getProspectosRelacionamentoSemPeso() {
        return prospectosRelacionamentoSemPeso;
    }

    public void setProspectosRelacionamentoSemPeso(List<PessoaJuridica> prospectosRelacionamentoSemPeso) {
        this.prospectosRelacionamentoSemPeso = prospectosRelacionamentoSemPeso;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public List<ContatoPessoal> getContatosPrivados() {
        return contatosPrivados;
    }

    public void setContatosPrivados(List<ContatoPessoal> contatosPrivados) {
        this.contatosPrivados = contatosPrivados;
    }

    public List<OrigemProspectoPrivado> getOrigens() {
        return origens;
    }

    public void setOrigens(List<OrigemProspectoPrivado> origens) {
        this.origens = origens;
    }

    public boolean isUmUsuarioDoCliente() {
        return (this instanceof UsuarioCrmCliente);

    }

    public boolean isUmUsuarioConvidado() {
        return (this instanceof UsuarioConvidado);
    }

    public UsuarioCrmCliente getUsuarioComoUsrCliente() {
        return (UsuarioCrmCliente) this;
    }

    public MetadadoAtendente getMetadadosAtendente() {
        return metadadosAtendente;
    }

    public void setMetadadosAtendente(MetadadoAtendente metadadosAtendente) {
        this.metadadosAtendente = metadadosAtendente;
    }

    public List<Solicitacao> getSolicitacoesAguardando() {
        return solicitacoesAguardando;
    }

    public void setSolicitacoesAguardando(List<Solicitacao> solicitacoesAguardando) {
        this.solicitacoesAguardando = solicitacoesAguardando;
    }

    public List<Solicitacao> getSolicitacoesLiberarCard() {
        return solicitacoesLiberarCard;
    }

    public void setSolicitacoesLiberarCard(List<Solicitacao> solicitacoesLiberarCard) {
        this.solicitacoesLiberarCard = solicitacoesLiberarCard;
    }

    public List<Solicitacao> getSolicitacoesFeitas() {
        return solicitacoesFeitas;
    }

    public void setSolicitacoesFeitas(List<Solicitacao> solicitacoesFeitas) {
        this.solicitacoesFeitas = solicitacoesFeitas;
    }

    public UsuarioCrmCliente getComoUsuarioCliente() {
        return (UsuarioCrmCliente) this;
    }

    public String getCodigoMatrix() {
        return codigoMatrix;
    }

    public void setCodigoMatrix(String codigoMatrix) {
        this.codigoMatrix = codigoMatrix;
    }

}
