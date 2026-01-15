/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = {"Tipo atividade CRM autogestão "}, plural = "Tipos de Atividade CRM autogestão", icone = "fa fa-paint-brush", modulo = ERPCrm.NOME_MODULO_ERP)
public class TipoAtvCRMPadrao extends TipoAtividadeCRM {

}
