/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoFormLead;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;

/**
 *
 * @author sfurbino
 */
@InfoObjetoSB(plural = "Dados do contato", tags = "Dados do contato")
public class ContatoAnonimoDadoTansitorio extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Nome da empresa")
    private String nomeEmpresa;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String site;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, obrigatorio = true)
    private String nomeUsuario;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TELEFONE_CELULAR, obrigatorio = true)
    private String celular;
    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL, obrigatorio = true)
    @InfoCampoValidadorLogico()
    private String email;
    private HorarioDisponivelAtendimentoPublico horarioDaReserva;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HorarioDisponivelAtendimentoPublico getHorarioDaReserva() {
        return horarioDaReserva;
    }

    public void setHorarioDaReserva(HorarioDisponivelAtendimentoPublico horarioDaReserva) {
        this.horarioDaReserva = horarioDaReserva;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
