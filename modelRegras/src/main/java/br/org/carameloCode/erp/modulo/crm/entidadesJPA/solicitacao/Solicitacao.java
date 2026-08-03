package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.notificacao.entidadesJPA.notificacao.NotificacaoSB;
import com.google.common.collect.Lists;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.DestinatarioTransiente;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabStatusComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDestinatario;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta.RespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoComunicacao;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação", plural = "Solicitacoes", icone = "fa fa-hand-paper-o")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntitySoliciatacao")
public class Solicitacao extends EntidadeSimplesORM implements ComoDialogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @InfoCampoValorLogico(nomeCalculo = "")
    private String nome;

    @ManyToOne(targetEntity = TipoSolicitacao.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoSolicitacao tipoSolicitacao;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataHoraSolicitacao = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, obrigatorio = true)
    private Date dataHoraDataProgramada;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataUltimaResposta;

    @ManyToOne(targetEntity = UsuarioCRM.class, optional = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, obrigatorio = true, somenteLeitura = true, label = "Usuário solicitante")
    private UsuarioCRM usuarioSolicitante;

    @ManyToOne(targetEntity = UsuarioCRM.class, optional = false)
    @InfoCampo(obrigatorio = true, somenteLeitura = true, label = "Usuário Solicitado")
    private UsuarioCRM usuarioSolicitado;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEntitySoliciatacao;

    @ManyToOne(targetEntity = StatusSolicitacao.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "Status solicitação")
    private StatusSolicitacao status;

    @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private Pessoa pessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL)
    @InfoCampoValorLogico(nomeCalculo = "Link convite")
    private String linkConvite;

    @InfoCampo(label = "Observação", obrigatorio = true)
    private String observacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean foiFinalizada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean foiAtendida;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean foiReagedado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean foiRecebida;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    @InfoCampoValorLogico(nomeCalculo = "Está atrazada?")
    private boolean emAtraso;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String codigoSelo;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "Notificação Vinculada", atualizarSempreQueSalvar = false)
    private NotificacaoSB notificacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(Date dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    public Date getDataHoraDataProgramada() {
        return dataHoraDataProgramada;
    }

    public void setDataHoraDataProgramada(Date dataHoraDataProgramada) {
        this.dataHoraDataProgramada = dataHoraDataProgramada;
    }

    public UsuarioCRM getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(UsuarioCRM usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public UsuarioCRM getUsuarioSolicitado() {
        return usuarioSolicitado;
    }

    public void setUsuarioSolicitado(UsuarioCRM usuarioSolicitado) {
        this.usuarioSolicitado = usuarioSolicitado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoSolicitacao getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public Date getDataUltimaResposta() {
        return dataUltimaResposta;
    }

    public void setDataUltimaResposta(Date dataUltimaResposta) {
        this.dataUltimaResposta = dataUltimaResposta;
    }

    public String getTipoEntitySoliciatacao() {
        return tipoEntitySoliciatacao;
    }

    public void setTipoEntitySoliciatacao(String tipoEntitySoliciatacao) {
        this.tipoEntitySoliciatacao = tipoEntitySoliciatacao;
    }

    public String getLinkConvite() {
        return linkConvite;
    }

    public void setLinkConvite(String linkConvite) {
        this.linkConvite = linkConvite;
    }

    public boolean isFoiFinalizada() {
        return foiFinalizada;
    }

    public void setFoiFinalizada(boolean foiFinalizada) {
        this.foiFinalizada = foiFinalizada;
    }

    @Transient
    private DestinatarioTransiente destinatario;

    @Override
    public ItfDestinatario getDestinatario() {
        if (destinatario == null) {
            destinatario = new DestinatarioTransiente(getUsuarioSolicitado());
        }

        return destinatario;
    }

    public ComoUsuario getUsuarioRemetente() {
        return getUsuarioSolicitante();
    }

    @Override
    public ComoTipoComunicacao getTipoComunicacao() {
        if (this instanceof SolicitacaoAcessoCard) {
            return FabTipoComunicacao.SOLICITAR_AUTORIZACAO.getRegistro();
        }
        if (this instanceof SolicitacaoConfirmacaoCliente || this instanceof SolicitacaoConfirmacaoEquipe) {
            return FabTipoComunicacao.CONFIRMAR_CANCELAR.getRegistro();
        }
        return FabTipoComunicacao.ENVIAR_INFORMACOES_CANCELAR.getRegistro();
    }
    @Transient
    private ItfRespostaComunicacao resposta;

    private ComoTipoRespostaComunicacao tipoREsposta() {
        if (!foiFinalizada) {
            return null;
        }
        if (foiReagedado) {
            return FabTipoRespostaComunicacao.AQUARDE_A_RESPOSTA.getRegistro();
        }
        if (!foiAtendida) {
            return null;
        }

        switch (getTipoComunicacao().getFabTipoComunicacao()) {
            case SOLICITAR_AUTORIZACAO:
                if (foiAtendida) {
                    return FabTipoRespostaComunicacao.AUTORIZADO.getRegistro();
                } else {
                    return FabTipoRespostaComunicacao.NAO_AUTORIZADO.getRegistro();
                }
            case CONFIRMAR_CANCELAR:
                if (foiAtendida) {
                    return FabTipoRespostaComunicacao.ENTENDIDO.getRegistro();
                } else {
                    return FabTipoRespostaComunicacao.CANCELAR.getRegistro();
                }

            default:
                if (foiAtendida) {
                    return FabTipoRespostaComunicacao.OK.getRegistro();
                } else {
                    return FabTipoRespostaComunicacao.CANCELAR.getRegistro();
                }
        }
    }

    @Override
    public ItfRespostaComunicacao getRespostaEscolhida() {

        if (resposta != null) {
            return resposta;
        }
        if (!foiFinalizada) {
            return null;
        }

        if (foiAtendida) {
            resposta = new RespostaComunicacao(this, FabTipoRespostaComunicacao.SIM.getRegistro());

        } else {
            resposta = new RespostaComunicacao(this, FabTipoRespostaComunicacao.NAO.getRegistro());
        }
        return resposta;
    }

    @Override
    public void setRespostaEscolhida(ItfRespostaComunicacao pResposta) {
        if (pResposta.getTipoResposta().isRespostasPosiva()) {
            foiAtendida = pResposta.getTipoResposta().isRespostasPosiva();
            foiFinalizada = true;
        } else {
            foiAtendida = pResposta.getTipoResposta().isRespostasPosiva();
            foiFinalizada = true;
        }
    }

    @Transient
    protected List<ItfRespostaComunicacao> respostas;

    @Override
    public List<ItfRespostaComunicacao> getRepostasPossiveis() {
        if (respostas == null || respostas.isEmpty()) {
            respostas = new ArrayList<>();
            switch (getTipoComunicacao().getFabTipoComunicacao()) {
                case SOLICITAR_AUTORIZACAO:

                    respostas.add(new RespostaComunicacao(this, FabTipoRespostaComunicacao.AUTORIZADO.getRegistro()));
                    respostas.add(new RespostaComunicacao(this, FabTipoRespostaComunicacao.NAO_AUTORIZADO.getRegistro()));

                    return respostas;

                case CONFIRMAR_CANCELAR:

                    respostas.add(new RespostaComunicacao(this, FabTipoRespostaComunicacao.ENTENDIDO.getRegistro()));
                    respostas.add(new RespostaComunicacao(this, FabTipoRespostaComunicacao.CANCELAR.getRegistro()));
                    respostas.add(new RespostaComunicacao(this, FabTipoRespostaComunicacao.AQUARDE_A_RESPOSTA.getRegistro()));
                    return respostas;

                default:
                    respostas.add(new RespostaComunicacao(this, FabTipoRespostaComunicacao.OK.getRegistro()));

                    return respostas;

            }

        }
        return respostas;
    }

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<ItfTipoCanalComunicacao> transportes;

    @Override
    public String getAssunto() {
        return observacao;
    }

    @Override
    public void setAssunto(String pAssunto) {
        observacao = pAssunto;
    }

    @Override
    public String getMensagem() {
        return nome;
    }

    @Override
    public void setMensagem(String pMensagem) {
        nome = pMensagem;
    }

    @Override
    public boolean isFoiSelado() {
        return true;
    }

    @Override
    public String getCodigoSelo() {
        return codigoSelo;
    }

    @Override
    public long getTempoAceitavelResposta() {
        long intervalor = UtilCRCDataHora.intervaloTempoMinutos(new Date(), dataHoraDataProgramada);
        if (intervalor <= 0) {
            return 0;
        }
        return intervalor;
    }

    @Override
    public void setTempoAceitavelResposta(long pTempo) {

    }

    @Override
    public FabStatusComunicacao getStatusComunicacao() {
        if (foiFinalizada) {
            return FabStatusComunicacao.RESPONDIDO;
        }

        if (foiRecebida) {
            return FabStatusComunicacao.RECEBIDO;
        }
        return FabStatusComunicacao.ENVIADO;

    }

    @Override
    public void setStatusComunicacao(FabStatusComunicacao pStatus) {

    }

    @Override
    public Date getDataHoraDisparo() {
        return dataHoraSolicitacao;
    }

    @Override
    public Date getDataHoraResposta() {
        return dataUltimaResposta;
    }

    @Override
    public void setCodigoSelo(String pCodigoSelo) {
        codigoSelo = pCodigoSelo;
    }

    public boolean isFoiAtendida() {
        return foiAtendida;
    }

    public void setFoiAtendida(boolean foiAtendida) {
        this.foiAtendida = foiAtendida;
    }

    public boolean isFoiRecebida() {
        return foiRecebida;
    }

    public void setFoiRecebida(boolean foiRecebida) {
        this.foiRecebida = foiRecebida;
    }

    public SolicitacaoArquivoCliente getCoSolicitacaoArquivoCliente() {
        return (SolicitacaoArquivoCliente) this;
    }

    public SolicitacaoArquivoEquipe getComoSolicitacaoArquivoEquipe() {
        return (SolicitacaoArquivoEquipe) this;
    }

    @Override
    public List<ERPTipoCanalComunicacao> getCanais() {
        return Lists.newArrayList(ERPTipoCanalComunicacao.INTRANET_MENU, ERPTipoCanalComunicacao.MATRIX);
    }

    @Override
    public void setCanais(List<ERPTipoCanalComunicacao> pCanais) {

    }

    @Override
    public String getPaginaInstanciaID() {
        return null;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public boolean isEmAtraso() {
        return emAtraso;
    }

    public void setEmAtraso(boolean emAtraso) {
        this.emAtraso = emAtraso;
    }

    public NotificacaoSB getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(NotificacaoSB notificacao) {
        this.notificacao = notificacao;
    }

    private boolean umaComunicacaoPersonalizada = true;

    private String urlRespostaPersonalizada;

    public boolean isUmaComunicacaoPersonalizada() {
        return umaComunicacaoPersonalizada;
    }

    public void setUmaComunicacaoPersonalizada(boolean umaComunicacaoPersonalizada) {
        this.umaComunicacaoPersonalizada = umaComunicacaoPersonalizada;
    }

    public String getUrlRespostaPersonalizada() {
        return urlRespostaPersonalizada;
    }

    public void setUrlRespostaPersonalizada(String urlRespostaPersonalizada) {
        this.urlRespostaPersonalizada = urlRespostaPersonalizada;
    }

    public SolicitArqAtualizacaoEqp getComoSolicitacaoAtualizacao() {
        return (SolicitArqAtualizacaoEqp) this;
    }

}
