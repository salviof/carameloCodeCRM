/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import java.util.List;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;

/**
 *
 * @author sfurbino
 */
public class LimitesAtendimentoPublico {

    private boolean limitarPorAtendido;
    private List<TipoAgendamentoAtdmPublico> tipoAtendimento;
    private List<UsuarioSB> usuariosAtendentes;
    private List<UsuarioSB> usuariosAtendido;
    private List<GrupoUsuarioSB> gruposAtendentes;
    private List<GrupoUsuarioSB> gruposAtendido;
    private int quantidade;
}
