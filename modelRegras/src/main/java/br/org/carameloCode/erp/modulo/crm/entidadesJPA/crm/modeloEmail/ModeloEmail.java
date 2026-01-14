/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.modeloEmail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoModeloDocumento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(tags = {"Modelo de E-mail"}, plural = "Modelos de E-mail")
public class ModeloEmail extends EntidadeORMNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @Min(10)
    private String assunto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML_TEMPLATE)
    @InfoCampoModeloDocumento(classeModeloVinculado = PessoaJuridica.class, camposCadastrados = {"nome", "email"})
    @Column(length = 8000, columnDefinition = "TEXT")
    private String textoModelo;
    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private UsuarioSB usuariocriou;
    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    private UsuarioSB usuarioEditou;
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private Date dataHoraCriou;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEditou;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return assunto;
    }

    @Override
    public void setNome(String assunto) {
        this.assunto = assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getDescritivo() {
        return descricao;
    }

    public void setDescritivo(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioSB getUsuariocriou() {
        return usuariocriou;
    }

    public void setUsuariocriou(UsuarioSB usuariocriou) {
        this.usuariocriou = usuariocriou;
    }

    public UsuarioSB getUsuarioEditou() {
        return usuarioEditou;
    }

    public void setUsuarioEditou(UsuarioSB usuarioEditou) {
        this.usuarioEditou = usuarioEditou;
    }

    public Date getDataHoraCriou() {
        return dataHoraCriou;
    }

    public void setDataHoraCriou(Date dataHoraCriou) {
        this.dataHoraCriou = dataHoraCriou;
    }

    public Date getDataHoraEditou() {
        return dataHoraEditou;
    }

    public void setDataHoraEditou(Date dataHoraEditou) {
        this.dataHoraEditou = dataHoraEditou;
    }

    public String getTextoModelo() {
        return textoModelo;
    }

    public void setTextoModelo(String textoModelo) {
        this.textoModelo = textoModelo;
    }

}
