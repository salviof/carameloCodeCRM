/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabTipoUtilizacao;

/**
 *
 * @author sfurbino
 */
public class UtilCRMSistema {

    public static final FabTipoUtilizacao TIPO_UTILIZACAO_PADRAO = FabTipoUtilizacao.getTipoAtualizacaoByString(FabConfigModuloIntranet.TIPO_APRESENTACAO.getValorParametroSistema());
}
