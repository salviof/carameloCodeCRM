/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.agenda.entidadesJPA;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import java.util.List;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;

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
