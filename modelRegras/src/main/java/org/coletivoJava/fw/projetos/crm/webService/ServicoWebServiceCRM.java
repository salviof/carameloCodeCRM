/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.crm.webService;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public class ServicoWebServiceCRM {

    public static void iniciarWebSErviceCRM() {
        List<ComoFabricaAcoes> acoesEndpointpublico
                = Lists.newArrayList(FabAcaoCRMAtendimento.PROSPECTO_CTR_SALVAR_MERGE);
        //SBCore.getConfigModulo(FabConfigModuloIntranet.class).getPropriedade(FabConfigModuloIntranet.)
        UtilServicoWebService.publicarServico(acoesEndpointpublico, 8666);
    }

}
