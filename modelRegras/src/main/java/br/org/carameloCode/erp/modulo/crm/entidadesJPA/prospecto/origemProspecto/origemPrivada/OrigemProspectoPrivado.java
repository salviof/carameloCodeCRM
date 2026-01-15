/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Origem privada"}, plural = "Origens privadas", icone = "fa fa-dot-circle-o", generoFeminino = true)
public class OrigemProspectoPrivado extends OrigemProspecto {

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private UsuarioCRM usuarioDono;

    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL, label = "Porcentagem ", descricao = "Você pode compartilhar esta origem com seu amigo, e "
            + "já deixar uma sujestão porcentagem à ser dividida com você quando o negócio for concretizado")
    private double porcentagemAoCompartilhar;

    public UsuarioCRM getUsuarioDono() {
        return usuarioDono;
    }

    public void setUsuarioDono(UsuarioCRM usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    public double getPorcentagemAoCompartilhar() {
        return porcentagemAoCompartilhar;
    }

    public void setPorcentagemAoCompartilhar(double porcentagemAoCompartilhar) {
        this.porcentagemAoCompartilhar = porcentagemAoCompartilhar;
    }

}
