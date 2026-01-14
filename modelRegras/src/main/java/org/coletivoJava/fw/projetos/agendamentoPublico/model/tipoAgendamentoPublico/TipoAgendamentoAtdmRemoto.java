/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico;

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
