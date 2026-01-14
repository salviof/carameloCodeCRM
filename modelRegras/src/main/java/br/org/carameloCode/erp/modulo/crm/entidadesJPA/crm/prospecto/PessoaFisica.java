/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa.FabPorteProspectoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa.Porte;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Pessoa Física", plural = "Pessoas")
@EntityListeners(ListenerEntidadePessoa.class)
public class PessoaFisica extends Pessoa {

    @InfoCampo(tipo = FabTipoAtributoObjeto.CPF)
    private String cpf;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Tipo Serviço", obrigatorio = true, somenteLeitura = false)
    @ManyToOne(targetEntity = TipoEmpresa.class)
    private TipoEmpresa tipoEmpresa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Porte", obrigatorio = true, somenteLeitura = true)
    @ManyToOne(targetEntity = Porte.class)
    private Porte porte = FabPorteProspectoEmpresa.PEQUENA.getRegistro();

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Site", obrigatorio = false)
    @Column(unique = true)
    @InfoCampoValidadorLogico()
    @InfoCampoValorLogico(nomeCalculo = "Site", somenteLeitura = false)
    private String site;

    @Override
    public void prepararNovoObjeto(Object... parametros) {
        super.prepararNovoObjeto(parametros);

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean isUmaPessoaFisica() {

        return super.isUmaPessoaFisica(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public Porte getPorte() {
        return porte;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
