/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Gatilho de atividade crm"}, plural = "Gatilhos de atividades crm", fabricaVinculada = FabAtividadeCRMAutoexecucao.class, modulo = ERPCrm.NOME_MODULO_ERP)
public class TipoAtividadeCRMAutoExecucao extends TipoAtividadeCRM {

}
