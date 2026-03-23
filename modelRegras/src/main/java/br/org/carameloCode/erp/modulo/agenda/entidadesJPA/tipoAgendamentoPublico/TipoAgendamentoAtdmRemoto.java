/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Agendamento de conferência", plural = "Conferências por vídeo programadas")
public class TipoAgendamentoAtdmRemoto extends TipoAgendamentoAtdmPublico {

}
