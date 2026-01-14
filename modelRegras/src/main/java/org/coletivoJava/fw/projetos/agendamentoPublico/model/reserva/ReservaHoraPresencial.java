/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localizacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Visita programada", plural = "Reservas de visitas programadas")
public class ReservaHoraPresencial extends ReservaHorario {

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private Localizacao localizacao;

    @InfoCampoValorLogico(nomeCalculo = "textoLocalizacao")
    private String textoLocalizacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso(iconePostivio = "fa fa-briefcase", textoNegativo = "O consultor vai ao Local", textoPositivo = "O consultor aguardara na sede", iconeNegativo = "fa fa-building")
    @InfoCampoValidadorLogico
    private boolean atendimentoOutSide;

    public ReservaHoraPresencial() {
        if (!SBCore.isEmModoProducao()) {
            System.out.println("");
        }
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public boolean isAtendimentoOutSide() {
        return atendimentoOutSide;
    }

    public void setAtendimentoOutSide(boolean atendimentoOutSide) {
        this.atendimentoOutSide = atendimentoOutSide;
    }

    public String getTextoLocalizacao() {
        return textoLocalizacao;
    }

    public void setTextoLocalizacao(String textoLocalizacao) {
        this.textoLocalizacao = textoLocalizacao;
    }

}
