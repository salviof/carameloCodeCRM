/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda;

import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.mapeamentoAgenda.MapaHorariosDisponiveis;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.DisponibilidadeAtdmtPublico;

/**
 *
 * @author salvio
 */
public class ModuloAgendaAdmin extends ControllerAbstratoSBPersistencia {

    public static ItfRespostaAcaoDoSistema disponibilidadeAtendimentoMerge(DisponibilidadeAtdmtPublico pDisponibilidade) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pDisponibilidade), pDisponibilidade) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                atualizarEntidadeSetRetorno(pDisponibilidade);
                MapaHorariosDisponiveis.adicionarDisponibilidade((DisponibilidadeAtdmtPublico) getRetorno());
            }
        }.getResposta();

    }
}
