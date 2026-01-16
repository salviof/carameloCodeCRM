/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmIA;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;

/**
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
    TIPO_DADO_IA_CTR_SALVAR_MERG;

}
