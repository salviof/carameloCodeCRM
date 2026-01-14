/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmCliente;

import br.org.carameloCode.erp.crm.config.TesteWPCrmCarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.mapeamentoAgenda.MapaHorariosDisponiveis;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.HorarioDisponivelAtendimentoPublico;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class PgReservasClienteTest extends TesteWPCrmCarameloCode {

    public PgReservasClienteTest() {
    }

    /**
     * Test of getXhtmlAcaoAtual method, of class PgReservasCliente.
     */
    @Test
    public void testeREservas() {
        MapaHorariosDisponiveis.loadReservasEDisponibilidadesPersistidos();
        SBCore.getServicoSessao().logarEmailESenha("salviof@gmail.com", "123321");
        PgReservasCliente reservaCliente = new PgReservasCliente();
        Map<String, String> prmetros = new HashMap<>();

        reservaCliente.aplicaValoresDeParametrosModoDesenvolvimento(prmetros);

        reservaCliente.inicio();
        reservaCliente.setUsrAtendenteSelecionado(reservaCliente.getUsrRepComercial());
        reservaCliente.defineTipoDeReserva(reservaCliente.getTiposDisponiveis().get(0));
        List<HorarioDisponivelAtendimentoPublico> horarios = reservaCliente.getAgendaDisponivel().getHorariosDisponiveis();

        for (HorarioDisponivelAtendimentoPublico hr : horarios) {
            System.out.println(UtilCRCDataHora.getDataSTRFormatoUsuario(hr.getDataHoraIicialAtendente()));
        }

    }

}
