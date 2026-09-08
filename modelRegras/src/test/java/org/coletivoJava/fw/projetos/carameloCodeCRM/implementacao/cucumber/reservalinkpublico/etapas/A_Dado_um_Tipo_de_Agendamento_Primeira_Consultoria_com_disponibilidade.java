package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.ModuloAgendamentoPublico;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.ModuloCrmAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgendaPublica.ModuloCRMAgendaPublica;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico.EtapasReservaLinkPublico;
import cucumber.api.java.pt.Dado;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.FluxoAgendaPublicaReserva;
import org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico.FabTipoAgendamentoPublicoCrm;
import org.junit.Assert;

public class A_Dado_um_Tipo_de_Agendamento_Primeira_Consultoria_com_disponibilidade {

    @Dado(EtapasReservaLinkPublico.DADO_UM_TIPO_DE_AGENDAMENTO_PRIMEIRA_CONSULTORIA_COM_DISPONIBILIDADE)
    public void implementacaoEtapa() {

        List<TipoAgendamentoAtdmPublico> tipos = UtilSBPersistencia.getListaTodos(TipoAgendamentoAtdmPublico.class, FluxoAgendaPublicaReserva.getEM());
        TipoAgendamentoAtdmPublico agendaPorLink = FabTipoAgendamentoPublicoCrm.PRIMEIRA_CONSULTORIA_REMOTO.getRegistro();

        FluxoAgendaPublicaReserva.escopoPulico = new EscopoPesqHorarioPublicado();
        List<UsuarioSB> atendentes = new ArrayList<>();
        atendentes.add((UsuarioSB) CarameloCode.getServicoPermissao().getUsuarioByEmail(FluxoAgendaPublicaReserva.EMAIL_ATENDENTE));
        FluxoAgendaPublicaReserva.escopoPulico.setAtendentes(atendentes);
        List<TipoAgendamentoAtdmPublico> tipoAgendamento = new ArrayList<>();
        tipoAgendamento.add(agendaPorLink);
        FluxoAgendaPublicaReserva.escopoPulico.setDiasDaSemana("0111100");
        FluxoAgendaPublicaReserva.escopoPulico.setTiposAgendamentosDisponiveis(tipoAgendamento);
        FluxoAgendaPublicaReserva.escopoPulico.setQtdMaximoReservas(3);
        FluxoAgendaPublicaReserva.escopoPulico.setNumeroOpcoes(9);
        FluxoAgendaPublicaReserva.escopoPulico.setDataInicial(new Date());
        FluxoAgendaPublicaReserva.escopoPulico.setHorarioinicio(Time.valueOf(LocalTime.of(8, 0)));
        FluxoAgendaPublicaReserva.escopoPulico.setHorarioFinal(Time.valueOf(LocalTime.of(22, 0)));
        FluxoAgendaPublicaReserva.escopoPulico.setDataHoraTokenPublicoExpira(UtilCRCDataHora.incrementaDias(new Date(), 20));
        //Definindo outros campos do escopo

        ItfRespostaAcaoDoSistema respostaSalvarEScopo = ModuloAgendamentoPublico.escopoPublicoSalvarMerge(FluxoAgendaPublicaReserva.escopoPulico);
        Assert.assertTrue("Falha salvarndo escopo", respostaSalvarEScopo.isSucesso());
        ItfRespostaAcaoDoSistema respostaAtivandoEscopoPublico = ModuloCRMAgendaPublica.escopoPublicoAtivar((EscopoPesqHorarioPublicado) respostaSalvarEScopo.getRetorno());

        Assert.assertTrue("Falha ativando escopo", respostaAtivandoEscopoPublico.isSucesso());
        FluxoAgendaPublicaReserva.escopoPulico = (EscopoPesqHorarioPublicado) respostaAtivandoEscopoPublico.getRetorno();
        FluxoAgendaPublicaReserva.atualizarEntidadesDeclaradas();
        List<DisponibilidadeAtdmtPublico> disponibilidades = UtilSBPersistencia.getListaTodos(DisponibilidadeAtdmtPublico.class,
                FluxoAgendaPublicaReserva.getEM());

        UsuarioSB usuario = (UsuarioSB) CarameloCode.getServicoPermissao().getUsuarioByEmail(FluxoAgendaPublicaReserva.EMAIL_ATENDENTE);
        DisponibilidadeAtdmtPublico disp = disponibilidades.stream()
                .filter(dispo -> dispo.getUsuarioResponsavel().equals(usuario)).findFirst().get();
        CarameloCode.getServicoSessao().logarEmailESenha(FluxoAgendaPublicaReserva.EMAIL_ATENDENTE, FluxoAgendaPublicaReserva.SENHA_ATENDIMENTO);
        disp.adicionarTipoAgendamentoDisponivel(agendaPorLink);

        ModuloAgendamentoPublico.disponibilidadeAtendimentoMerge(disp);

    }
}
