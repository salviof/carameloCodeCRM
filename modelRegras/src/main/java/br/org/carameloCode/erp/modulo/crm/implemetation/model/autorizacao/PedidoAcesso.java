/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.implemetation.model.autorizacao;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Pedido de Acesso"}, plural = "Pedidos de Acessso", icone = "fa fa-handshake-o")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPedido")
@EntityListeners(ListenerEntidadePadrao.class)
public class PedidoAcesso extends EntidadeSimplesORM {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, somenteLeitura = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioSolicitante;

    @ManyToOne(targetEntity = StatusPedidoAcesso.class, cascade = {CascadeType.ALL})
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true, fabricaDeOpcoes = FabStatusPedidoAcesso.class)
    private StatusPedidoAcesso status;

    @ManyToOne(targetEntity = AcaoDoSistema.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private AcaoDoSistema acao;

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private UsuarioSB usuarioConcedente;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, somenteLeitura = true)
    private Date dataHoraCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO, somenteLeitura = true)
    private Date dataHoraAlteracao = new Date();

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoPedido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    @InfoCampoValorLogico(nomeCalculo = "Ativo")
    private boolean ativo;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioSB getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(UsuarioSB usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public AcaoDoSistema getAcao() {
        return acao;
    }

    public void setAcao(AcaoDoSistema acao) {
        this.acao = acao;
    }

    public UsuarioSB getUsuarioConcedente() {
        return usuarioConcedente;
    }

    public void setUsuarioConcedente(UsuarioSB usuarioConcedente) {
        this.usuarioConcedente = usuarioConcedente;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public StatusPedidoAcesso getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoAcesso status) {
        this.status = status;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Date getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(Date dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    public boolean isUmPedidoAcessoPessoa() {
        return false;
    }
}
