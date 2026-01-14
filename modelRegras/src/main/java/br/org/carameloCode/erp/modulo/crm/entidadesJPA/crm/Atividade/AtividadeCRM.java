package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoBloqueio.FabTipoBloqueio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chatBot.ChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso.CodigoConviteAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.DocumentoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.InfoGrupoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.atividadades.ItfAtividadeProgramada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import br.org.carameloCode.erp.modulo.crm.api.model.mensagemmktwhatsapp.CPMensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Atividade "}, plural = "Atividades", icone = "fa fa-pencil", modulo = ERPCrm.NOME_MODULO_ERP)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidade")
@EntityListeners(ListenerEntidadePadrao.class)
public class AtividadeCRM extends EntidadeORMNormal implements ItfAtividadeProgramada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEntidade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO, label = "Realizado em:")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraRealizacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Realizado em:")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraInicioAtividade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, label = "Agendado para")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraPrevisaoExecucao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @InfoCampoValorLogico(nomeCalculo = "nome Ativiadede")
    private String nomeAtividade;

    @ManyToOne(targetEntity = Solicitacao.class, optional = true, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private Solicitacao solicitacao;

    @ManyToOne(targetEntity = TipoAtividadeCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @NotFound(action = NotFoundAction.IGNORE)
    private TipoAtividadeCRM tipoAtividade;

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private UsuarioSB usuarioAtividade;

    @ManyToOne(targetEntity = Pessoa.class)
    private Pessoa prospectoEmpresa;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "DadoColetado_Atividade",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "dado_id"))
    private List<DadoCRM> dadosColetados;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DadoNaoColetado_Atividade",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "dado_id"))
    private List<DadoCRM> dadosNaoColetados;

    @OneToMany(mappedBy = "atividadeCRM", targetEntity = DadoCRM.class)
    private List<DadoCRM> dadosVinculoDireto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, label = "Relatório da Atividade", valorMinimo = 0, valorMaximo = 400)
    @Column(length = 400)
    private String anotacoes;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, fabricaDeOpcoes = FabStatusAtividade.class)
    @ManyToOne(targetEntity = StatusAtividade.class)
    private StatusAtividade statusAtividade;

    @OneToMany(targetEntity = DocumentoAtividadeCRM.class, mappedBy = "atividadeGeradora", cascade = {CascadeType.REMOVE})
    private List<DocumentoAtividadeCRM> documentosGerados;

    @OneToMany(mappedBy = "atividade", targetEntity = CodigoConviteAtividade.class, cascade = {CascadeType.REMOVE})
    private List<CodigoConviteAtividade> codigosConvite;

    @Transient
    private boolean dadosNaoColetadosAtualizados = false;

    @InfoCampoValorLogico(nomeCalculo = "documentoFoiEnviado")
    private boolean documentoEnviado;

    @InfoCampoValorLogico(nomeCalculo = "documentoGerado")
    private boolean documentoGerado;

    @InfoCampoValorLogico(nomeCalculo = "formularioExecucao")
    private String formularioExecucao;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "formularioExecucao")
    private ItfAcaoFormulario acaoFormularioExecucao;

    @ManyToOne(targetEntity = EmailCrm.class, cascade = CascadeType.ALL)
    private EmailCrm emailVinculado;

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnvioEmailAtividade> emailsVinculados;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "documentoFoiEntregue")
    private AtividadeCRM atividadeMesmoTipoAbertaUsuarioLogado;

    private boolean sobrescreverAcaoAtual = false;

    @ManyToOne(targetEntity = TipoRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Relacionamento Gerado")
    @InfoCampoValorLogico(nomeCalculo = "Tipo Relacionamento Gerado")
    private TipoRelacionamento relacionamentoGerado;

    @ManyToOne(targetEntity = Orcamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "orcamentosDisponiveis")
    @InfoCampoValorLogico(nomeCalculo = "orçamento da atividade", atualizarSempreQueSalvar = false)
    private Orcamento orcamento;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @InfoCampoValorLogico(nomeCalculo = "Orçamentos disponíveis")
    private List<Orcamento> orcamentosDisponiveis;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "possiEtapasRestantes")
    private boolean possiEtapasRestantes;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "Demanda plugin finalizada")
    private boolean demandaPluginFinalizada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "Em estado de interação com plugin")
    @Transient
    private boolean noEstadoInteracaoPlugin;

    @Transient
    private List<ItfAcaoFormulario> etapasRestantes;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Tipo Bloqueio Atual")
    private FabTipoBloqueio tipoBloqueio;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "BloqueiosRestantes")
    private List<FabTipoBloqueio> listaBloqueiosRestantes;

    @OneToMany(mappedBy = "atividade")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @InfoGrupoCampo(camposDeclarados = {CPMensagemMktWhatsapp.datahora,
        CPMensagemMktWhatsapp.tipo, CPMensagemMktWhatsapp.linkpreview})
    private List<MensagemMktWhatsapp> envioMensagens;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = ChatBot.class)
    @InfoCampoValorLogico(nomeCalculo = "ChatBot")
    private ChatBot chatBot;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso
    @InfoCampoValorLogico(nomeCalculo = "Possui Coleta")
    private boolean posssuiColetaDado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampoValorLogico(nomeCalculo = "dadosRevisados")
    private boolean dadosRevisados;

    public AtividadeCRM() {
        super();
        etapasRestantes = null;
        tipoBloqueio = null;
        listaBloqueiosRestantes = null;

    }

    public List<EnvioEmailAtividade> getEmailsVinculados() {
        return emailsVinculados;
    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {TipoAtividadeCRM.class, Pessoa.class})
    public final void prepararNovoObjeto(Object... parametros) {
        try {
            super.prepararNovoObjeto(parametros);
            TipoAtividadeCRM pTipoAtividade = getParametroInicialEnviado(TipoAtividadeCRM.class, parametros);
            setTipoAtividade(pTipoAtividade);
            Pessoa prosp = getParametroInicialEnviado(Pessoa.class, parametros);
            setProspectoEmpresa(prosp);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro iniciando atividade", t);
        }

    }

    public AtividadeCRM(TipoAtividadeCRM pTipoAtividade) {
        super();
        prospectoEmpresa = new PessoaJuridica();
        prepararNovoObjeto(pTipoAtividade, prospectoEmpresa);
    }

    public AtividadeCRM(TipoAtividadeCRM pTipoAcao, Pessoa pProspecto) {
        super();
        prepararNovoObjeto(pTipoAcao, pProspecto);

    }

    /**
     *
     * Move os dados não Coeletados que são diferentes de nulo para o lugar
     * certo, e mantem os dados não coletados desta atividade na lista de dados
     * não coletados
     *
     *
     */
    public void ajustarColeta() {
        // compilar Dados coletados (Mover dados coletados para Lista correta)
        // Adicionar campos coletados nos valores corretos
        // compilar anotações em dados Coletados
        if (dadosColetados == null) {
            return;
        }
        for (DadoCRM dado : getDadosNaoColetados()) {

            if (dado.getValor() != null) {
                if (!"".equals(dado.getValor())) {

                    dadosColetados.add(dado);

                }
            }
        }

        getDadosNaoColetados().removeAll(getDadosColetados());

        //List<Integer> indexDadosRemoverNaoColetados = new ArrayList();
        //for (DadoCRM dadoNaoColetadoEncontrado : getDadosColetados()) {
        //  for (int i = 0; i < getDadosNaoColetados().size(); i++) {
        //     String nomeDadoNaoColetado = dadoNaoColetadoEncontrado.getNome();
        //    if (nomeDadoNaoColetado == null) {
        //        throw new UnsupportedOperationException("O nome do dado não foi definido" + dadoNaoColetadoEncontrado.getCampoPropriedades());
        //    }
        //  if (nomeDadoNaoColetado.equals(getDadosNaoColetados().get(i).getNome())) {
        //    indexDadosRemoverNaoColetados.add(i);
        //}
        //}
        //}
        //for (int i = indexDadosRemoverNaoColetados.size() - 1; i >= 0; i--) {


    ///  int indexARemover = indexDadosRemoverNaoColetados.get(i);
        //dadosNaoColetados.remove(indexARemover);
        //}
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHoraRealizacao() {
        return dataHoraRealizacao;
    }

    public void setDataHoraRealizacao(Date dataHoraRealizacao) {
        this.dataHoraRealizacao = dataHoraRealizacao;
    }

    @Override
    public Date getDataHoraPrevisaoExecucao() {
        return dataHoraPrevisaoExecucao;
    }

    public void setDataHoraPrevisaoExecucao(Date dataHoraPrevisaoExecucao) {
        this.dataHoraPrevisaoExecucao = dataHoraPrevisaoExecucao;
    }

    public List<DadoCRM> getDadosColetados() {
        return dadosColetados;
    }

    public void setDadosColetados(List<DadoCRM> dadosColetados) {
        this.dadosColetados = dadosColetados;
    }

    public Pessoa getProspectoEmpresa() {
        return prospectoEmpresa;
    }

    public final void setProspectoEmpresa(Pessoa prospectoEmpresa) {
        this.prospectoEmpresa = prospectoEmpresa;
    }

    public TipoAtividadeCRM getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividadeCRM tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public ComoUsuario getUsuarioAtividade() {
        return usuarioAtividade;
    }

    public void setUsuarioAtividade(UsuarioSB usuarioAtividade) {
        this.usuarioAtividade = usuarioAtividade;
    }

    public List<DadoCRM> getDadosNaoColetados() {

        if (getTipoAtividade().isGeraNovoRelacionamento()
                || !getTipoAtividade().getTiposDadosColetarNaAtividade().isEmpty()) {
            if (!dadosNaoColetadosAtualizados) {
                dadosNaoColetados = getProspectoEmpresa().getListaDadosNaoColetadosCRM(this);
                dadosNaoColetadosAtualizados = true;
            }
        } else {
            return new ArrayList();
        }

        return dadosNaoColetados;
    }

    public void setDadosNaoColetados(List<DadoCRM> dadosNaoColetados) {
        this.dadosNaoColetados = dadosNaoColetados;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    @InfoCampoValorLogico(nomeCalculo = "permitidoConcluir")
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @Transient
    private boolean permitidoConcluir;

    /**
     *
     * Verifica se os dados da atividade foram preenchidos para permitir ou não
     * o progresso
     *
     * @return
     */
    public boolean isPermitidoConcluir() {

        return permitidoConcluir;

    }

    public void setPermitidoConcluir(boolean permitidoConcluir) {
        this.permitidoConcluir = permitidoConcluir;
    }

    public boolean isFoiAtividadeFinalizada() {
        if (statusAtividade == null) {
            return false;

        } else {

            if (statusAtividade.equals(FabStatusAtividade.CONCLUIDA_COM_SUCESSSO.getRegistro())
                    || statusAtividade.equals(FabStatusAtividade.CANCELADA.getRegistro())) {
                return true;
            }
        }
        return false;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public String getNomeAtividade() {

        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public List<DocumentoAtividadeCRM> getDocumentosGerados() {
        return documentosGerados;
    }

    public void regerarDocumento() {

    }

    public void prepararParaSalvar() {

    }

    @Override
    public Date getDataHoraInicioAtividade() {
        return dataHoraInicioAtividade;
    }

    public void setDataHoraInicioAtividade(Date dataHoraInicioAtividade) {
        this.dataHoraInicioAtividade = dataHoraInicioAtividade;
    }

    @Override
    @PreUpdate
    protected void antesDeAtulizarRegistro() {
        super.antesDeAtulizarRegistro();
        getProspectoEmpresa().setDataHoraAlterouProspecto(new Date());

    }

    public TipoRelacionamento getRelacionamentoGerado() {
        return relacionamentoGerado;
    }

    public void setRelacionamentoGerado(TipoRelacionamento relacionamentoGerado) {
        this.relacionamentoGerado = relacionamentoGerado;
    }

    public boolean isSobrescreverAcaoAtual() {
        return sobrescreverAcaoAtual;
    }

    public void setSobrescreverAcaoAtual(boolean sobrescreverAcaoAtual) {
        this.sobrescreverAcaoAtual = sobrescreverAcaoAtual;
    }

    public String getFormularioExecucao() {
        return formularioExecucao;
    }

    public ItfAcaoFormulario getAcaoFormularioExecucao() {
        return acaoFormularioExecucao;
    }

    public void setAcaoFormularioExecucao(ItfAcaoFormulario acaoFormularioExecucao) {
        this.acaoFormularioExecucao = acaoFormularioExecucao;
    }

    public boolean isDocumentoEnviado() {

        return documentoEnviado;
    }

    public void setDocumentoEnviado(boolean documentoEnviado) {
        this.documentoEnviado = documentoEnviado;
    }

    public boolean isDocumentoGerado() {

        return documentoGerado;
    }

    public void setDocumentoGerado(boolean documentoGerado) {
        this.documentoGerado = documentoGerado;
    }

    public EmailCrm getEmailVinculado() {

        return emailVinculado;
    }

    public void setEmailVinculado(EmailCrm emailVinculado) {
        this.emailVinculado = emailVinculado;
    }

    @Deprecated
    public AtividadeCRM getAtividadeMesmoTipoAbertaUsuarioLogado() {

        return atividadeMesmoTipoAbertaUsuarioLogado;
    }

    public void setAtividadeMesmoTipoAbertaUsuarioLogado(AtividadeCRM atividadeMesmoTipoAbertaUsuarioLogado) {
        this.atividadeMesmoTipoAbertaUsuarioLogado = atividadeMesmoTipoAbertaUsuarioLogado;
    }

    public String getTextoAgendamento() {
        return getTipoAtividade().getNome() + " para " + getProspectoEmpresa().getNome() + "às " + UtilCRCDataHora.converteDataEmStringCorrida(getDataHoraPrevisaoExecucao());
    }

    public List<CodigoConviteAtividade> getCodigosConvite() {
        return codigosConvite;
    }

    public boolean isTemCodigoRemoto() {
        return !getCodigosConvite().isEmpty();
    }

    public CodigoConviteAtividade getConvitePrincipal() {
        if (isTemCodigoRemoto()) {
            return getCodigosConvite().get(0);
        } else {
            return null;
        }

    }

    public FabTipoBloqueio getTipoBloqueio() {
        return tipoBloqueio;
    }

    public String getTipoBloqueioTexto() {
        return getTipoBloqueio().gerarMensagemPorTipo(this);
    }

    public List<ItfAcaoFormulario> getEtapasRestantes() {

        return etapasRestantes;
    }

    public List<FabTipoBloqueio> getListaBloqueios() {

        return listaBloqueiosRestantes;
    }

    public void setListaBloqueiosRestantes(List<FabTipoBloqueio> listaBloqueiosRestantes) {
        this.listaBloqueiosRestantes = listaBloqueiosRestantes;
    }

    @Override
    public UsuarioCRM getUsuarioInsersao() {
        return (UsuarioCRM) super.getUsuarioInsersao(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DadoCRM> getDadosVinculoDireto() {
        return dadosVinculoDireto;
    }

    public void setDadosVinculoDireto(List<DadoCRM> dadosVinculoDireto) {
        this.dadosVinculoDireto = dadosVinculoDireto;
    }

    public boolean isDemandaPluginFinalizada() {
        return demandaPluginFinalizada;
    }

    public void setDemandaPluginFinalizada(boolean demandaPluginFinalizada) {
        this.demandaPluginFinalizada = demandaPluginFinalizada;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public List<Orcamento> getOrcamentosDisponiveis() {
        return orcamentosDisponiveis;
    }

    public void setOrcamentosDisponiveis(List<Orcamento> orcamentosDisponiveis) {
        this.orcamentosDisponiveis = orcamentosDisponiveis;
    }

    @Override
    public Date getDataHoraConclusao() {
        return getDataHoraRealizacao();
    }

    @Override
    public String getUrlAtividade() {

        return "";// MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro(), atividade.getTipoAtividade(), atividade.getProspectoEmpresa(), atividade
    }

    public void setFormularioExecucao(String formularioExecucao) {
        this.formularioExecucao = formularioExecucao;
    }

    public void setTipoBloqueio(FabTipoBloqueio tipoBloqueio) {
        this.tipoBloqueio = tipoBloqueio;
    }

    public boolean isPossiEtapasRestantes() {
        return possiEtapasRestantes;
    }

    public void setPossiEtapasRestantes(Boolean possiEtapasRestantes) {
        this.possiEtapasRestantes = possiEtapasRestantes;
    }

    public boolean isNoEstadoInteracaoPlugin() {
        return noEstadoInteracaoPlugin;
    }

    public void setNoEstadoInteracaoPlugin(boolean noEstadoInteracaoPlugin) {
        this.noEstadoInteracaoPlugin = noEstadoInteracaoPlugin;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public boolean isPosssuiColetaDado() {
        return posssuiColetaDado;
    }

    public void setPosssuiColetaDado(boolean posssuiColetaDado) {
        this.posssuiColetaDado = posssuiColetaDado;
    }

    public List<MensagemMktWhatsapp> getEnvioMensagens() {
        return envioMensagens;
    }

    public void setEnvioMensagens(List<MensagemMktWhatsapp> envioMensagens) {
        this.envioMensagens = envioMensagens;
    }

    public boolean isDadosRevisados() {
        return dadosRevisados;
    }

    public void setDadosRevisados(boolean dadosRevisados) {
        this.dadosRevisados = dadosRevisados;
    }

    public boolean isDadosNaoColetadosAtualizados() {
        return dadosNaoColetadosAtualizados;
    }

    public void setDadosNaoColetadosAtualizados(boolean dadosNaoColetadosAtualizados) {
        this.dadosNaoColetadosAtualizados = dadosNaoColetadosAtualizados;
    }

    public ChatBot getChatBot() {
        return chatBot;
    }

    public void setChatBot(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    public EnvioEmailAtividade getEmailComoEnvio() {
        return (EnvioEmailAtividade) emailVinculado;
    }

}
