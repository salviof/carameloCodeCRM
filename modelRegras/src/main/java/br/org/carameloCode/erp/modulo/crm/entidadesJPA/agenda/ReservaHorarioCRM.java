/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.agenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Reserva de Horário", plural = "Reservas de Horarios")
@EntityListeners(ListenerReservaCRM.class)
public class ReservaHorarioCRM extends ReservaHorario {

    @ManyToOne(targetEntity = Pessoa.class, cascade = CascadeType.REFRESH)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = false)
    @InfoCampoValidadorLogico()
    private Pessoa pessoaRelacionada;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contatosAtendidos_reservas",
            joinColumns = @JoinColumn(name = "reservaHorario_id"),
            inverseJoinColumns = @JoinColumn(name = "contatoatendido_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "pessoaRelacionada.contatosProspecto")
    private List<ContatoProspecto> contatosAtendidos;

    @Override
    public UsuarioCrmCliente getAtendidoResponsavel() {
        return (UsuarioCrmCliente) super.getAtendidoResponsavel();

    }

    public Pessoa getPessoaRelacionada() {
        return pessoaRelacionada;
    }

    public void setPessoaRelacionada(Pessoa pessoaRelacionada) {
        this.pessoaRelacionada = pessoaRelacionada;
    }

    public List<ContatoProspecto> getContatosAtendidos() {
        return contatosAtendidos;
    }

    public void setContatosAtendidos(List<ContatoProspecto> contatosAtendidos) {
        this.contatosAtendidos = contatosAtendidos;
    }

}
