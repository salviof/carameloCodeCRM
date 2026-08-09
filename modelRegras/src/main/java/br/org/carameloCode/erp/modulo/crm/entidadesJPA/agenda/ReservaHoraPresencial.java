/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ComoReservaPresencial;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localizacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocalPostagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocalidade;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Visita programada", plural = "Reservas de visitas programadas")
public class ReservaHoraPresencial extends ReservaHorarioCRM implements ComoReservaPresencial {

    @Override
    public void setTipoAgendamento(TipoAgendamentoAtdmPublico tipoAgendamento) {
        super.setTipoAgendamento((TipoReservaCRMLocal) tipoAgendamento); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public TipoReservaCRMLocal getTipoAgendamento() {
        return (TipoReservaCRMLocal) super.getTipoAgendamento(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private Localizacao localizacao;

    @InfoCampoValorLogico(nomeCalculo = "textoLocalizacao")
    private String textoLocalizacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso(iconePostivio = "fa fa-briefcase", textoNegativo = "O consultor vai ao Local", textoPositivo = "O consultor aguardara na sede", iconeNegativo = "fa fa-building")
    @InfoCampoValidadorLogico
    private boolean atendimentoOutSide;

    @Override
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros);

    }

    public ReservaHoraPresencial() {
        if (!SBCore.isEmModoProducao()) {
            System.out.println("");
        }
    }

    @Override
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    @Override
    public void setLocalizacao(ComoLocal localizacao) {
        this.localizacao = (Localizacao) localizacao;
    }

    @Override
    public boolean isAtendimentoOutSide() {
        return atendimentoOutSide;
    }

    @Override
    public void setAtendimentoOutSide(boolean atendimentoOutSide) {
        this.atendimentoOutSide = atendimentoOutSide;
    }

    @Override
    public String getTextoLocalizacao() {
        return textoLocalizacao;
    }

    public void setTextoLocalizacao(String textoLocalizacao) {
        this.textoLocalizacao = textoLocalizacao;
    }

}
