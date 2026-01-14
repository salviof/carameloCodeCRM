/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.integracoes.modelController.socialAutenticador;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;

/**
 *
 * @author SalvioF
 */
@InfoModulosCRM(modulo = FabModulosCRM.ATENDIMENTO_CRM)
public enum FabAcaoSocialAutenticador implements ComoFabricaDeAcoesPersistencia {
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Login Social", icone = "fa fa-key", entidade = TipoCredencialSocial.class)
    AUTENTICACAO_SOCIAL_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Login Social", icone = "fa fa-key")
    AUTENTICACAO_SOCIAL_FRM_LISTAR_OPCOES,
    @InfoTipoAcaoFormulario(nomeAcao = "Sucesso", icone = "fa fa-key")
    AUTENTICACAO_SOCIAL_FRM_AUTENTICADO_SUCESSO,
    @InfoTipoAcaoFormulario(nomeAcao = "Autenticação externa", icone = "fa fa-key")
    AUTENTICACAO_SOCIAL_FRM_AUTENTICAR_EXTERNO,
    @InfoTipoAcaoController(nomeAcao = "Atualizar Cadastro", icone = "fa fa-key")
    AUTENTICACAO_SOCIAL_CTR_ATUALIZAR_CADASTRO,
    @InfoTipoAcaoController(nomeAcao = "Criar usuário", icone = "fa fa-key")
    AUTENTICACAO_SOCIAL_CTR_CRIAR_CADASTRO,
    @InfoTipoAcaoController(nomeAcao = "Efetuar Login", icone = "fa fa-key")
    AUTENTICACAO_SOCIAL_CTR_logar;

}
