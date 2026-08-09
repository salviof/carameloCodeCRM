/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPresencial;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Tipo reserva local", plural = "Agendamentos de visita")
public class TipoReservaCRMLocal extends TipoAgendamentoAtdmPresencial {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "filtro_tipoAgenda_meta",
            joinColumns = @JoinColumn(name = "tipochamado_id"),
            inverseJoinColumns = @JoinColumn(name = "meta_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Dados do chamado")
    private List<MetaRelacionamento> metaFiltroDisp;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "filtro_tipoAgenda_relacionamento",
            joinColumns = @JoinColumn(name = "tipochamado_id"),
            inverseJoinColumns = @JoinColumn(name = "relacionamento_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Dados do chamado")
    private List<TipoRelacionamento> relacionemantoFiltroDisp;

    public List<MetaRelacionamento> getMetaFiltroDisp() {
        return metaFiltroDisp;
    }

    public void setMetaFiltroDisp(List<MetaRelacionamento> metaFiltroDisp) {
        this.metaFiltroDisp = metaFiltroDisp;
    }

    public List<TipoRelacionamento> getRelacionemantoFiltroDisp() {
        return relacionemantoFiltroDisp;
    }

    public void setRelacionemantoFiltroDisp(List<TipoRelacionamento> relacionemantoFiltroDisp) {
        this.relacionemantoFiltroDisp = relacionemantoFiltroDisp;
    }

}
