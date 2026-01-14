/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.LocalizacaoPostavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Agendamento de visita", plural = "Agendamentos de visita")
public class TipoAgendamentoAtdmPresencial extends TipoAgendamentoAtdmPublico {

    @ManyToOne(targetEntity = LocalizacaoPostavel.class)
    private LocalizacaoPostavel localPadraoReuniao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Localização")
    @InfoCampoValorLogico(nomeCalculo = "TextoPadraoLocalizacaoReuniaoInside")
    private String textoLocalizacaoReuniaoInSide = "Av. Afonso Pena, 2881 - Funcionários\n"
            + "Belo Horizonte MG 30130-011, Espaço guaja.cc, tel. 31 3500-2942";

    public LocalizacaoPostavel getLocalPadraoReuniao() {
        return localPadraoReuniao;
    }

    public void setLocalPadraoReuniao(LocalizacaoPostavel localPadraoReuniao) {
        this.localPadraoReuniao = localPadraoReuniao;
    }

    public String getTextoLocalizacaoReuniaoInSide() {
        return textoLocalizacaoReuniaoInSide;
    }

    public void setTextoLocalizacaoReuniaoInSide(String textoLocalizacaoReuniaoInSide) {
        this.textoLocalizacaoReuniaoInSide = textoLocalizacaoReuniaoInSide;
    }

}
