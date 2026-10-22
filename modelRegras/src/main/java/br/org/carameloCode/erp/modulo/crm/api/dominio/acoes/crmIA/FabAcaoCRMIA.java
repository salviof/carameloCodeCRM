/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailAtividade;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.ExecucaoIAModeloEmailMkt;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;

/**
 *
 *
 * @author salvio
 */
@InfoModulosCRM(modulo = FabModulosCRM.ADMIN_CRM)
public enum FabAcaoCRMIA implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = TipoDadoIA.class)
    TIPO_DADO_IA_MB_GESTAO,
    @InfoTipoAcaoFormulario()
    TIPO_DADO_IA_FRM_LISTAR,
    @InfoTipoAcaoFormulario()
    TIPO_DADO_IA_FRM_EDITAR,
    @InfoTipoAcaoFormulario()
    TIPO_DADO_IA_FRM_VISUALIZAR,
    @InfoTipoAcaoController()
    TIPO_DADO_IA_CTR_SALVAR_MERG,
    @InfoTipoAcaoGestaoEntidade(entidade = ExecucaoIA.class)
    EXECUCAO_IA_MB_GESTAO,
    @InfoTipoAcaoController(entidade = ExecucaoIAModeloEmailMkt.class)
    EXECUCAO_IA_CTR_ATUALIZAR_MODELO_DOCUMENTO,
    @InfoTipoAcaoController(entidade = ExecucaoIAAtividade.class)
    EXECUCAO_IA_CTR_CRIAR_DIALOGO_ATIVIDADE,
    @InfoTipoAcaoController(entidade = ExecucaoIAAtividade.class)
    EXECUCAO_IA_CTR_ATUALIZAR_EMAIL_ATIVIDADE,
    @InfoTipoAcaoController(entidade = ExecucaoIAModeloEmailMkt.class)
    EXECUCAO_IA_CTR_CRIAR_DIALOGO_MODELO_EMAIL_MARKETING,
    @InfoTipoAcaoController(entidade = ExecucaoIAModeloEmailAtividade.class)
    EXECUCAO_IA_CTR_CRIAR_DIALOGO_MODELO_EMAIL,
    @InfoTipoAcaoController(entidade = ExecucaoIAModeloEmailAtividade.class)
    EXECUCAO_IA_CTR_ATUALIZAR_MODELO_EMAIL_TRANSITORIO;

}
