/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.agendamentoPublico.model;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.Date;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabContextoDeReserva;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;

/**
 *
 * @author sfurbino
 */
@InfoObjetoSB(tags = "Horário disponível", plural = "Horários disponíveis")
public class HorarioDisponivelAtendimentoPublico extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descritivoCurto;

    private Date dataHoraIicialAtendimento;
    private Date datahoraFinalAtendimento;
    private FabContextoDeReserva contextoAgendamento;
    private TipoAgendamentoAtdmPublico tipoAgendamento;

    private UsuarioCRM usuarioResponsavel;

    private EscopoPesquisaMelhorHorario escopo;

    public HorarioDisponivelAtendimentoPublico() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHoraIicialAtendente() {
        return dataHoraIicialAtendimento;
    }

    public void setDataHoraIicialAtendente(Date dataHoraIicialAtendente) {
        this.dataHoraIicialAtendimento = dataHoraIicialAtendente;
    }

    public Date getDatahoraFinalAtendente() {
        return datahoraFinalAtendimento;
    }

    public void setDatahoraFinalAtendente(Date datahoraFinalAtendente) {
        this.datahoraFinalAtendimento = datahoraFinalAtendente;
    }

    public TipoAgendamentoAtdmPublico getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(TipoAgendamentoAtdmPublico tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public EscopoPesquisaMelhorHorario getEscopo() {

        return escopo;
    }

    public void setEscopo(EscopoPesquisaMelhorHorario pEscopo) {
        setTipoAgendamento(pEscopo.getTipoAgendamento());
        this.escopo = pEscopo;
    }

    public UsuarioCRM getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(UsuarioCRM usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    @Override
    public String toString() {
        StringBuilder textoHorario = new StringBuilder();
        if (getEscopo() != null) {
            textoHorario.append(getEscopo().getId());

        }
        if (dataHoraIicialAtendimento != null) {
            textoHorario.append(UtilCRCDataHora.getDataHoraString(dataHoraIicialAtendimento, UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
        }
        textoHorario.append(" às ");
        if (datahoraFinalAtendimento != null) {
            textoHorario.append(UtilCRCDataHora.getDataHoraString(datahoraFinalAtendimento, UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_USUARIO));
        }
        return textoHorario.toString();
    }

}
