package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.DestinatarioTransiente;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabStatusComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDestinatario;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
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
import org.coletivojava.fw.api.objetoNativo.comunicacao.RespostaComunicacao;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação", plural = "Solicitacoes", icone = "fa fa-hand-paper-o")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntitySoliciatacao")
public class Solicitacao extends EntidadeSimplesORM implements ItfDialogo {

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
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, obrigatorio = true, somenteLeitura = true)
    private UsuarioCRM usuarioSolicitante;

    @ManyToOne(targetEntity = UsuarioCRM.class, optional = false)
    @InfoCampo(obrigatorio = true, somenteLeitura = true)
    private UsuarioCRM usuarioSolicitado;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEntitySoliciatacao;

    @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private Pessoa pessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL)
    @InfoCampoValorLogico(nomeCalculo = "Link convite")
    private String linkConvite;

    @InfoCampo(label = "Observação", obrigatorio = true)
    private String obeservacao;

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

    public String getObeservacao() {
        return obeservacao;
    }

    public void setObeservacao(String obeservacao) {
        this.obeservacao = obeservacao;
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

    @Override
    public ComoUsuario getUsuarioRemetente() {
        return getUsuarioSolicitante();
    }

    @Override
    public ItfTipoComunicacao getTipoComunicacao() {
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

    private ItfTipoRespostaComunicacao tipoREsposta() {
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
        return obeservacao;
    }

    @Override
    public void setAssunto(String pAssunto) {
        obeservacao = pAssunto;
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
        return String.valueOf(getSlugIdentificador().hashCode());
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
    public void setCodigoSelo(String codigoSelo) {

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

}
