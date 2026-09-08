package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.etapas;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.agendaconsultordefiniragenda.EtapasAgendaConsultorDefinirAgenda;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.agendaconsultordefiniragenda.FluxoAgendaDoConsultor;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.HorarioDisponivelAtendimentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmContato.ModuloCRMContatos;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda.ReservaHoraRemotoVideo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.junit.Assert;

public class K_Quando__uma_reserva_e_realizada_para_segunda_as_8_horas {

    @Quando(EtapasAgendaConsultorDefinirAgenda.QUANDO_UMA_RESERVA_E_REALIZADA_PARA_SEGUNDA_AS_8_HORAS)
    public void implementacaoEtapa() {
        // limparPesquisa() descarta só a agenda calculada. limparAtendenteDefinido() anularia o
        // atendente e faria setUsuarioAtendente trocar o escopo pelo escopoAgendaClientes do usuário,
        // descartando o escopo configurado nos passos B/C.
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.limparPesquisa();
        FluxoAgendaDoConsultor.escopoAtendimentoRemoto.setUsuarioAtendente((UsuarioSB) SBCore.getUsuarioLogado());
        List<HorarioDisponivelAtendimentoPublico> horariosDisponiveis = (List) FluxoAgendaDoConsultor.escopoAtendimentoRemoto.getHorariosDisponiveis();
        System.out.println(UtilCRCDataHora.getDataHoraString(horariosDisponiveis.get(0).getDataHoraIicialAtendente(), UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
        ReservaHoraRemotoVideo novaReserva = new ReservaHoraRemotoVideo();
        List<Pessoa> pessoas = UtilSBPersistencia.getListaTodos(Pessoa.class, FluxoAgendaDoConsultor.getEM());
        Pessoa pessoa = pessoas.get(0);
        ContatoProspecto contato = new ContatoProspecto();
        try {
            contato.prepararNovoObjeto(pessoa);
        } catch (ErroPreparandoObjeto ex) {
            Logger.getLogger(K_Quando__uma_reserva_e_realizada_para_segunda_as_8_horas.class.getName()).log(Level.SEVERE, null, ex);
        }
        contato.setNome("Jão");
        contato.setCelular("3193430392");
        contato.setEmail("jao@gmail.com");
        FluxoAgendaDoConsultor.pessoa = pessoa;
        ModuloCRMContatos.contatoSalvarMerge(contato);
        FluxoAgendaDoConsultor.atualizarEntidadesDeclaradas();
        try {
            novaReserva.prepararNovoObjeto(horariosDisponiveis.get(0));
            ContatoProspecto contatoprincipal = ((ContatoProspecto) FluxoAgendaDoConsultor.pessoa.getCPinst("contatoPrincipal").getValor());
            UsuarioCrmCliente usuario
                    = (UsuarioCrmCliente) contatoprincipal.getCPinst("usuarioVinculado")
                            .getValor();
            novaReserva.setAtendidoResponsavel(usuario);
        } catch (ErroPreparandoObjeto ex) {
            throw new UnsupportedOperationException("Falha criando reserva de horário");
        }
        ItfResposta resp = ModuloCRMCliente.reservaHorario(novaReserva);
        Assert.assertTrue("Falha criando reserva", resp.isSucesso());

        FluxoAgendaDoConsultor.reservaHorario = (ReservaHorario) resp.getRetorno();
    }
}
