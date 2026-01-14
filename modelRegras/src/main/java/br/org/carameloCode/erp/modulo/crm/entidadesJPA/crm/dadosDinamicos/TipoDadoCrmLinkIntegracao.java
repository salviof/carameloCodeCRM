/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Link integração", plural = "Links Integração")
public class TipoDadoCrmLinkIntegracao extends TipoDadoCRM {

    @InfoCampo(tipo = FabTipoAtributoObjeto.IMG_PEQUENA)
    private String imagem;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, obrigatorio = true)
    @InfoCampoValidadorLogico()
    private String nomeClasseLogica = "LinkSimples";

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean mostrarParaCliente;

    public TipoDadoCrmLinkIntegracao() {
        setFabricaTipoAtributo(FabTipoAtributoObjeto.URL);
    }

    @Override
    public void setNome(String pNome) {
        super.setNome(pNome);

    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNomeClasseLogica() {
        return nomeClasseLogica;
    }

    public void setNomeClasseLogica(String nomeClasseLogica) {
        this.nomeClasseLogica = nomeClasseLogica;
    }

    public boolean isMostrarParaCliente() {
        return mostrarParaCliente;
    }

    public void setMostrarParaCliente(boolean mostrarParaCliente) {
        this.mostrarParaCliente = mostrarParaCliente;
    }

}
