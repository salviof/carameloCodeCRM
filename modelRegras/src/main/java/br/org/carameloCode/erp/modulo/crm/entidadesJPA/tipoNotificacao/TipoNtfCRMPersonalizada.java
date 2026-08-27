/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Tipo de notificação Personalizada", plural = "Tipos de notificação personalizada ")
@Entity
public class TipoNtfCRMPersonalizada extends TiponotificacaoCRM {

    @Override
    public String nomeSequenciaIdentificacao() {
        return TipoNtfCRMPersonalizada.class.getSimpleName();
    }

    @Override
    public Long getIdSequenciaInicial() {
        return 1l;
    }

}
