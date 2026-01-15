/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.calendario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;

/**
 *
 * @author SalvioF
 */
@InfoModulosCRM(modulo = FabModulosCRM.ATENDIMENTO_CRM)
public enum FabAcaoGoogleCalendario implements ComoFabricaDeAcoesPersistencia {
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Calendário", icone = "fa fa-calendar", entidade = AtividadeCRM.class)
    CALENDARIO_GOOGLE_MB,
    @InfoTipoAcaoFormulario(icone = "fa fa-key", nomeAcao = "Autenticar")
    CALENDARIO_GOOGLE_FRM_AUTENTICAR,
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_OK)
    CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_SUCESSO,
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.COMNUNICACAO_NAO_ESTA_OK, icone = "fa fa-thumbs-o-down")
    CALENDARIO_GOOGLE_FRM_AUTENTICADO_COM_FALHA,
    @InfoTipoAcaoFormulario(icone = "fa calendar-plus", nomeAcao = "Sincronização Agenda")
    CALENDARIO_GOOGLE_FRM_SINCRONIZACAO,
    @InfoTipoAcaoController(icone = "fa fa-exchange", nomeAcao = "Sincronizar com Google Server")
    CALENDARIO_GOOGLE_CTR_SINCRONIZAR_GOOGLE_SERVER

}
