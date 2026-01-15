/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 *
 *
 * @author salviofurbino
 * @since 17/05/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(tags = {"Tag de Atendimento"}, plural = "Tags de Atendimento")
@EntityListeners(ListenerEntidadePadrao.class)
public class TagAtendimento extends EntidadeORMNormal {

    @Id
    @GeneratedValue
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, obrigatorio = true)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.COR, obrigatorio = true)
    private String cor;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date criadoEm;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date editadoEm;
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM criadoPor;
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM editadoPor;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescritivo() {
        return descricao;
    }

    public void setDescritivo(String descricao) {
        this.descricao = descricao;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Date getEditadoEm() {
        return editadoEm;
    }

    public void setEditadoEm(Date editadoEm) {
        this.editadoEm = editadoEm;
    }

    public UsuarioCRM getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(UsuarioCRM criadoPor) {
        this.criadoPor = criadoPor;
    }

    public UsuarioCRM getEditadoPor() {
        return editadoPor;
    }

    public void setEditadoPor(UsuarioCRM editadoPor) {
        this.editadoPor = editadoPor;
    }

}
