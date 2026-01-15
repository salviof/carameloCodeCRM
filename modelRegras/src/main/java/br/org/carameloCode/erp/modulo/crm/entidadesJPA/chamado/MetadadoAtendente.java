/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

/**
 *
 * @author sfurbino
 */
@InfoObjetoSB(tags = "Metadado de chamado", plural = "Metadados de chamados", modulo = ERPCrm.NOME_MODULO_ERP)
public class MetadadoAtendente extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private UsuarioCRM usuario;

    @InfoCampoValorLogico(nomeCalculo = "Reservas em aberto")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private long chamadosEmAtendimento;

    @InfoCampoValorLogico(nomeCalculo = "Reservas em aberto")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private long chamadosAguardandoAtendimento;

    @InfoCampoValorLogico(nomeCalculo = "Reservas em aberto")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private long reservasAtivas;

    @InfoCampoValorLogico(nomeCalculo = "Reservas em aberto")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private long clientesSatisfeitos;

    @InfoCampoValorLogico(nomeCalculo = "Reservas em aberto")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private long clientesMuitoSatisfeitos;

    @InfoCampoValorLogico(nomeCalculo = "Reservas em aberto")
    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private long clientesInsatisfeitos;

    public UsuarioCRM getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCRM usuario) {
        this.usuario = usuario;
    }

    public long getChamadosAbertos() {
        return chamadosEmAtendimento;
    }

    public void setChamadosAbertos(long chamadosAbertos) {
        this.chamadosEmAtendimento = chamadosAbertos;
    }

    public long getChamadosAguardandoAtendimento() {
        return chamadosAguardandoAtendimento;
    }

    public void setChamadosAguardandoAtendimento(long chamadosAguardandoAtendimento) {
        this.chamadosAguardandoAtendimento = chamadosAguardandoAtendimento;
    }

    public long getReservasAtivas() {
        return reservasAtivas;
    }

    public void setReservasAtivas(long reservasAtivas) {
        this.reservasAtivas = reservasAtivas;
    }

    public long getClientesSatisfeitos() {
        return clientesSatisfeitos;
    }

    public void setClientesSatisfeitos(long clientesSatisfeitos) {
        this.clientesSatisfeitos = clientesSatisfeitos;
    }

    public long getClientesMuitoSatisfeitos() {
        return clientesMuitoSatisfeitos;
    }

    public void setClientesMuitoSatisfeitos(long clientesMuitoSatisfeitos) {
        this.clientesMuitoSatisfeitos = clientesMuitoSatisfeitos;
    }

    public long getClientesInsatisfeitos() {
        return clientesInsatisfeitos;
    }

    public void setClientesInsatisfeitos(long clientesInsatisfeitos) {
        this.clientesInsatisfeitos = clientesInsatisfeitos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getChamadosEmAtendimento() {
        return chamadosEmAtendimento;
    }

    public void setChamadosEmAtendimento(long chamadosEmAtendimento) {
        this.chamadosEmAtendimento = chamadosEmAtendimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
