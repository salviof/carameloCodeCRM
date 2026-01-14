/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.crm.plugin.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;

/**
 *
 * @author sfurbino
 */
@InfoModulosCRM(modulo = FabModulosCRM.PLUGIN)
public enum FabAcaoOrcamento implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = Orcamento.class)
    ORCAMENTO_ATV_MB_GESTAO,
    @InfoTipoAcaoFormulario()
    ORCAMENTO_ATV_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar orçamento da Atividade")
    ORCAMENTO_ATV_FRM_EDITAR,
    @InfoTipoAcaoFormulario()
    ORCAMENTO_ATV_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_OK)
    ORCAMENTO_ATV_FRM_APROVAR_REPROVAR,
    @InfoTipoAcaoController(iconeFonteAnsowame = FabIconeFontAwesome.COMUNICACAO_OK)
    ORCAMENTO_ATV_CTR_APROVAR,
    @InfoTipoAcaoController(icone = "fa fa-ban")
    ORCAMENTO_ATV_CTR_REPROVAR,
    @InfoTipoAcaoController(iconeFonteAnsowame = FabIconeFontAwesome.REG_NOVO)
    ORCAMENTO_ATV_CTR_NOVO,
    @InfoTipoAcaoController(iconeFonteAnsowame = FabIconeFontAwesome.REG_ATUALIZAR)
    ORCAMENTO_ATV_CTR_SALVAR_MERGE,

}
