/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.codigoAcesso;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(tags = {"Código Convite"}, plural = "Códigos de convite")
@EntityListeners(ListenerEntidadePadrao.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoCodigo")
public class CodigoConvite extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GenericGenerator(
            name = "geradorIdCodigoConvite",
            strategy = "br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.geradorCodigo.GeradorIdCodigoConvite"
    )
    @GeneratedValue(generator = "geradorIdCodigoConvite")
    private int codigo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String assunto;
    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, obrigatorio = true)
    @Column(length = 2000, columnDefinition = "Text")
    private String conteudo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "atividade.prospectoEmpresa.contatosProspecto", obrigatorio = true)
    @ManyToOne(targetEntity = ContatoProspecto.class)
    private ContatoProspecto contato;
    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoCodigo;

    public CodigoConvite() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ContatoProspecto getContato() {
        return contato;
    }

    public void setContato(ContatoProspecto contato) {
        this.contato = contato;
    }

    public boolean isConviteFoiEnviado() {
        return false;

    }

}
