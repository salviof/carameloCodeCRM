/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.telefone;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRealizada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRecebida;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

import javax.persistence.*;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Telefone", plural = "Telefones", icone = "fa fa-phone")
@Entity
public class Telefone extends EntidadeSimples {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TELEFONE_CELULAR, descricao = "Exemplo: +5531984178550")
    private String telefone;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String codigoApiWhatsapp;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = TipoAtvChamadaRecebida.class)
    private TipoAtvChamadaRecebida tipoChamadaRecebida;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = TipoAtvChamadaRealizada.class)
    private TipoAtvChamadaRealizada tipoChamadaRealizada;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodigoApiWhatsapp() {
        return codigoApiWhatsapp;
    }

    public void setCodigoApiWhatsapp(String codigoApiWhatsapp) {
        this.codigoApiWhatsapp = codigoApiWhatsapp;
    }

    public TipoAtvChamadaRecebida getTipoChamadaRecebida() {
        return tipoChamadaRecebida;
    }

    public void setTipoChamadaRecebida(TipoAtvChamadaRecebida tipoChamadaRecebida) {
        this.tipoChamadaRecebida = tipoChamadaRecebida;
    }

    public TipoAtvChamadaRealizada getTipoChamadaRealizada() {
        return tipoChamadaRealizada;
    }

    public void setTipoChamadaRealizada(TipoAtvChamadaRealizada tipoChamadaRealizada) {
        this.tipoChamadaRealizada = tipoChamadaRealizada;
    }

}
