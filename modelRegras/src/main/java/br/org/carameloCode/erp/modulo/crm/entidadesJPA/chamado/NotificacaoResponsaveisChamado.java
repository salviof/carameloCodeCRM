/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Notificações", tags = "Notificação", icone = "fa fa-bell-o", modulo = ERPCrm.NOME_MODULO_ERP)
public class NotificacaoResponsaveisChamado extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ChamadoCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ChamadoCliente chamado;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraNotificacao;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ChamadoCliente getChamado() {
        return chamado;
    }

    public void setChamado(ChamadoCliente chamado) {
        this.chamado = chamado;
    }

    public Date getDataHoraNotificacao() {
        return dataHoraNotificacao;
    }

    public void setDataHoraNotificacao(Date dataHoraNotificacao) {
        this.dataHoraNotificacao = dataHoraNotificacao;
    }

}
