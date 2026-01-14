/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(plural = "Estus de Envio", tags = "Status Envio", fabricaVinculada = FabStatusEnvioEmail.class)
public class StatusEnvioEmail extends EntidadeORMStatus {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    @InfoCampoValorLogico(nomeCalculo = "Icone status")
    private String iconeStatus;

    @InfoCampo(tipo = FabTipoAtributoObjeto.STATUS_ENUM)
    private FabStatusEnvioEmail statusFabrica;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIconeStatus() {
        return iconeStatus;
    }

    public void setIconeStatus(String iconeStatus) {
        this.iconeStatus = iconeStatus;
    }

    public FabStatusEnvioEmail getStatusFabrica() {
        return statusFabrica;
    }

    public void setStatusFabrica(FabStatusEnvioEmail statusFabrica) {
        this.statusFabrica = statusFabrica;
    }

}
