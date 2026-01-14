/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

/**
 * @author sf urbino
 */
@InfoObjetoSB(tags = "Dados do usuário", plural = "Dados do usuário cliente")
public class MetadadoUsuarioCliente extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private UsuarioCrmCliente usuario;

    @InfoCampoValorLogico(nomeCalculo = "chamados abertos")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int chamadosAbertos;

    @InfoCampoValorLogico(nomeCalculo = "chamados resolvidos")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int chamdosResolvidos;

    @InfoCampoValorLogico(nomeCalculo = "texto reserva")
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String textoProximaReserva;

    @InfoCampoValorLogico(nomeCalculo = "Reservas em aberto")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int reservasAtivas;

    public UsuarioCrmCliente getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCrmCliente usuario) {
        this.usuario = usuario;
    }

    public int getChamadosAbertos() {
        return chamadosAbertos;
    }

    public void setChamadosAbertos(int chamadosAbertos) {
        this.chamadosAbertos = chamadosAbertos;
    }

    public int getChamdosResolvidos() {
        return chamdosResolvidos;
    }

    public void setChamdosResolvidos(int chamdosResolvidos) {
        this.chamdosResolvidos = chamdosResolvidos;
    }

    public int getReuniaoAgendada() {
        return reservasAtivas;
    }

    public void setReuniaoAgendada(int reuniaoAgendada) {
        this.reservasAtivas = reuniaoAgendada;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getReservasAtivas() {
        return reservasAtivas;
    }

    public void setReservasAtivas(int reservasAtivas) {
        this.reservasAtivas = reservasAtivas;
    }

    public String getTextoProximaReserva() {
        return textoProximaReserva;
    }

    public void setTextoProximaReserva(String textoProximaReserva) {
        this.textoProximaReserva = textoProximaReserva;
    }

}
