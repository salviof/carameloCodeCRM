/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

/**
 *
 * @author salvio
 */
public class ValorLogicoLinkCRM extends ValorLogicoLinkPadrao {

    private final static String URLFATURA_INICIO = "https://crm.casanovadigital.com.br/Prospectos";
    private final static String URLFATURA_FIM = "/ac-Infografico_da_pessoa.html";

    public ValorLogicoLinkCRM(ItfCampoInstanciado pCampoInstnciado) {
        super("CRM", pCampoInstnciado);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        StringBuilder valor = new StringBuilder();
        if (getDadoLinkIntDinamico().getProspectoEmpresa() == null) {
            return getDadoLinkIntDinamico().getValorEnpacotado();
        }
        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO,
                getDadoLinkIntDinamico().getProspectoEmpresa());

        getDadoLinkIntDinamico().setValorEmpacotado(url);

        return getDadoLinkIntDinamico().getValorEnpacotado();
    }
}
