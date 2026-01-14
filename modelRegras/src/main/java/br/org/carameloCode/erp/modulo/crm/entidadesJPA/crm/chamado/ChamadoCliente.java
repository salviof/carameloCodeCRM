/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@Entity
@InfoObjetoSB(tags = "Chamado do cliente", plural = "Chamados de cliente", icone = "fa fa-life-ring", modulo = ERPCrm.NOME_MODULO_ERP)
public class ChamadoCliente extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, label = "Código")
    private Long id;

    @InfoCampoValorLogico(nomeCalculo = "titulo")
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String titulo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, fabricaDeOpcoes = FabSastisfacaoClienteResolucao.class)
    @ManyToOne(targetEntity = SatisfacaoChamado.class)
    private SatisfacaoChamado satisfacao;

    @ManyToOne(targetEntity = StatusChamado.class, optional = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private StatusChamado status;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, camposExibidosEmLista = "usuarioDisponiveis")
    private UsuarioCRM atendenteResponsavel;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean notificarViaSMS = true;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean notificarViaEmail = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chamado_convidados_atend",
            joinColumns = @JoinColumn(name = "chamado_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<UsuarioCRM> atendentesConvidados;

    @InfoCampo()
    @InfoCampoValorLogico(nomeCalculo = "usuariosDisponiveis")
    @Transient()
    private List<UsuarioCRM> usuarioDisponiveis;

    @ManyToOne(targetEntity = UsuarioCrmCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private UsuarioCrmCliente usuarioCliente;

    @ManyToOne(targetEntity = TipoChamado.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private TipoChamado tipoChamado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, label = "Descrição do chamado", valorMinimo = 30)
    @Column(length = 8000)
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "resumo descricao")
    private String resumoDescricao;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataHoraCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraProgramada;

    @InfoCampo()
    @InfoCampoValorLogico(nomeCalculo = "salaRocketChat")
    private String salaMatrix;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    @InfoCampoValorLogico(nomeCalculo = "chamadoAtivo")
    private boolean ativo;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "chamado")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<NotificacaoResponsaveisChamado> notificacoes;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "chamado")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @OrderBy("id DESC")
    private List<EventoChamado> eventosDoChamado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    @InfoCampoValorLogico(nomeCalculo = "Pesssoa")
    @ManyToOne(targetEntity = Pessoa.class, optional = false)
    private Pessoa pessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuarioCriou;

    @InfoCampoValorLogico(nomeCalculo = "Link Chamado Cliente")
    private String linkUrlAcessoCliente;

    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraUltimoLinkAcesso;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraPrimeiroAtendimento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraUltimaInteracao;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = UsuarioCrmCliente.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros); //To change body of generated methods, choose Tools | Templates.
        UsuarioCrmCliente usuario = getParametroInicialEnviado(UsuarioCrmCliente.class, parametros);
        usuarioCliente = usuario;
        status = FabStatusChamado.RASCUNHO.getRegistro();
        setPessoa(usuario.getRepresentanteLegal());

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public TipoChamado getTipoChamado() {
        return tipoChamado;
    }

    public void setTipoChamado(TipoChamado tipoChamado) {
        this.tipoChamado = tipoChamado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Date getDataHoraProgramada() {
        return dataHoraProgramada;
    }

    public void setDataHoraProgramada(Date dataHoraProgramada) {
        this.dataHoraProgramada = dataHoraProgramada;
    }

    public String getSalaMatrix() {
        return salaMatrix;
    }

    public void setSalaMatrix(String salaMatrix) {
        this.salaMatrix = salaMatrix;
    }

    public UsuarioCrmCliente getCliente() {
        return usuarioCliente;
    }

    public void setCliente(UsuarioCrmCliente cliente) {
        this.usuarioCliente = cliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UsuarioCrmCliente getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(UsuarioCrmCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String getNome() {
        return titulo;
    }

    @Override
    public void setNome(String pNome) {
        titulo = pNome;
    }

    @Override
    public String getNomeCurto() {
        return super.getNomeCurto(); //To change body of generated methods, choose Tools | Templates.
    }

    public UsuarioCRM getAtendenteResponsavel() {
        return atendenteResponsavel;
    }

    public void setAtendenteResponsavel(UsuarioCRM atendenteResponsavel) {
        this.atendenteResponsavel = atendenteResponsavel;
    }

    public List<UsuarioCRM> getAtendentesConvidados() {
        return atendentesConvidados;
    }

    public void setAtendentesConvidados(List<UsuarioCRM> atendentesConvidados) {
        this.atendentesConvidados = atendentesConvidados;
    }

    public List<UsuarioCRM> getUsuarioDisponiveis() {
        return usuarioDisponiveis;
    }

    public void setUsuarioDisponiveis(List<UsuarioCRM> usuarioDisponiveis) {
        this.usuarioDisponiveis = usuarioDisponiveis;
    }

    public boolean isNotificarViaSMS() {
        return notificarViaSMS;
    }

    public void setNotificarViaSMS(boolean notificarViaSMS) {
        this.notificarViaSMS = notificarViaSMS;
    }

    public boolean isNotificarViaEmail() {
        return notificarViaEmail;
    }

    public void setNotificarViaEmail(boolean notificarViaEmail) {
        this.notificarViaEmail = notificarViaEmail;
    }

    public SatisfacaoChamado getSatisfacao() {
        return satisfacao;
    }

    public void setSatisfacao(SatisfacaoChamado satisfacao) {
        this.satisfacao = satisfacao;
    }

    public String getResumoDescricao() {
        return resumoDescricao;
    }

    public void setResumoDescricao(String resumoDescricao) {
        this.resumoDescricao = resumoDescricao;
    }

    public List<NotificacaoResponsaveisChamado> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<NotificacaoResponsaveisChamado> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public List<EventoChamado> getEventosDoChamado() {
        return eventosDoChamado;
    }

    public void setEventosDoChamado(List<EventoChamado> eventosDoChamado) {
        this.eventosDoChamado = eventosDoChamado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Deprecated
    public boolean isChamadoEmEstadoAtendimento() {
        return isEmEstadoAtendimento();
    }

    public boolean isEmEstadoAtendimento() {
        if (getStatus() == null) {
            return false;
        }
        return getStatus().equals(FabStatusChamado.EM_ATENDIMENTO.getRegistro());
    }

    public boolean isEmEstadoAguardandoAtendimento() {
        if (getStatus() == null) {
            return false;
        }
        return getStatus().equals(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());
    }

    public boolean isEmEstadoFinalizado() {
        if (getStatus() == null) {
            return false;
        }
        return getStatus().equals(FabStatusChamado.FINALIZADO.getRegistro());
    }

    public boolean isEmEstadoAtrazado() {
        if (getStatus() == null) {
            return false;
        }
        return getStatus().equals(FabStatusChamado.ATRAZADO.getRegistro());
    }

    @Deprecated
    public boolean isFoiFinalizado() {
        if (getStatus() == null) {
            return false;
        }
        return getStatus().equals(FabStatusChamado.FINALIZADO.getRegistro());
    }

    public UsuarioCRM getUsuarioCriou() {
        return usuarioCriou;
    }

    public void setUsuarioCriou(UsuarioCRM usuarioCriou) {
        this.usuarioCriou = usuarioCriou;
    }

    public String getLinkUrlAcessoCliente() {
        return linkUrlAcessoCliente;
    }

    public void setLinkUrlAcessoCliente(String linkUrlAcessoCliente) {
        this.linkUrlAcessoCliente = linkUrlAcessoCliente;
    }

    public Date getDataHoraUltimoLinkAcesso() {
        return dataHoraUltimoLinkAcesso;
    }

    public void setDataHoraUltimoLinkAcesso(Date dataHoraUltimoLinkAcesso) {
        this.dataHoraUltimoLinkAcesso = dataHoraUltimoLinkAcesso;
    }

    public Date getDataHoraPrimeiroAtendimento() {
        return dataHoraPrimeiroAtendimento;
    }

    public void setDataHoraPrimeiroAtendimento(Date dataHoraPrimeiroAtendimento) {
        this.dataHoraPrimeiroAtendimento = dataHoraPrimeiroAtendimento;
    }

    public Date getDataHoraUltimaInteracao() {
        return dataHoraUltimaInteracao;
    }

    public void setDataHoraUltimaInteracao(Date dataHoraUltimaInteracao) {
        this.dataHoraUltimaInteracao = dataHoraUltimaInteracao;
    }

}
