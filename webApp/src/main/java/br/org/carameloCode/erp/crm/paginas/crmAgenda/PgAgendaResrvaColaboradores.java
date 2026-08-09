/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.AgendaDisponibilidade;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.FabAcaoAdminAgenda;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.InfoAgendaAdmin;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.CPUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@InfoAgendaAdmin(acao = FabAcaoAdminAgenda.RESERVAS_ADMIN_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Reservas de Colaboradores", tags = {"COLAB_RESERVA"})
public class PgAgendaResrvaColaboradores extends MB_paginaCadastroEntidades<ReservaHorario> {

    @InfoParametroURL(nome = "PrColaborador", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prColaborador;

    private UsuarioCRM usuarioAtendimentoAgenda;
    private AgendaDisponibilidade agendaDisponivel;
    private TipoAgendamentoAtdmPublico tipoAtendimento;
    private List<TipoAgendamentoAtdmPublico> tiposDisponiveis;

    @PostConstruct
    public void inicio() {

        if (getParametroInstanciado(prColaborador).isValorDoParametroFoiConfigurado()) {
            usuarioAtendimentoAgenda = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prColaborador).getValor(), getEMPagina());
        }

        EscopoPesquisaMelhorHorario escopoSelecionado = UtilSBPersistencia.loadEntidade((EscopoPesquisaMelhorHorario) usuarioAtendimentoAgenda.getCPinst(CPUsuarioCRM.escopoagendaclientes).getValor(), getEMPagina());
        if (agendaDisponivel == null) {
            agendaDisponivel = new AgendaDisponibilidade(escopoSelecionado);

        }
        if (agendaDisponivel != null && agendaDisponivel.getEscopo() != null) {
            if (getTiposDisponiveis().size() == 1) {
                tipoAtendimento = getTiposDisponiveis().get(0);
                agendaDisponivel.setTipoAgendamento(tipoAtendimento);
            } else {
                if (agendaDisponivel.getEscopo() != null) {
                    executaAcaoSelecionadaPorEnum(FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_TIPOS_RESERVAS);
                }
            }

        }
    }

    public List<TipoAgendamentoAtdmPublico> getTiposDisponiveis() {
        if (usuarioAtendimentoAgenda != null && tiposDisponiveis == null || tiposDisponiveis.isEmpty()) {
            tiposDisponiveis = new ArrayList<>();
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(DisponibilidadeAtdmtPublico.class, getEMPagina());
            consulta.addCondicaoManyToManyContendoObjeto("atendentesDisponiveis", usuarioAtendimentoAgenda);
            List<DisponibilidadeAtdmtPublico> disponibilidades = consulta.gerarResultados();
            disponibilidades.stream().forEach(disp -> {
                disp.getTiposAgendamentosPublicos().stream().forEach(tipoAgenda -> {
                    if (!tiposDisponiveis.contains(tipoAgenda)) {
                        tiposDisponiveis.add(tipoAgenda);
                    }
                });
            });
        }
        return tiposDisponiveis;
    }

    public AgendaDisponibilidade getAgendaDisponivel() {
        return agendaDisponivel;
    }

    public UsuarioCRM getUsuarioAtendimentoAgenda() {
        return usuarioAtendimentoAgenda;
    }

    public void atualizarVisaoAgenda() {
        if (agendaDisponivel == null) {
            executaAcaoSelecionadaPorEnum(FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_ATENDENTES);
        }
        if (agendaDisponivel.getTipoAgendamento() == null) {
            executaAcaoSelecionadaPorEnum(FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_TIPOS_RESERVAS);
        }
        if (agendaDisponivel.getHorariosDisponiveis() != null && !agendaDisponivel.getHorariosDisponiveis().isEmpty()) {
            executaAcaoSelecionadaPorEnum(FabAcaoAdminAgenda.RESERVAS_ADMIN_FRM_LISTAR_RESERVAS_DISPONIVEIS);
        }

    }
}
