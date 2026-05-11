/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp;

import br.org.carameloCode.erp.modulo.agenda.api.ERPAgenda;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.apache.logging.log4j.LogManager;
import org.coletivojava.fw.api.objetoNativo.log.LogPadraoSB;
import org.junit.Test;
import testes.geradorCodigo.erp.GeradorERPImplementacaoContexto;
import testes.geradorCodigo.erp.dto.GeradorDTOInterface;
import testes.geradorCodigo.erp.dto.GeradorDTOPojo;
import testes.geradorCodigo.erp.dto.GeradorDTOProcessador;
import testesFW.ConfigCoreJunitPadraoDevAcaoPermissao;

/**
 *
 * @author salvio
 */
public class CriarImplementacao {

    @Test
    public void inicio() {
        try {
            SBCore.configurar(new ConfigCoreJunitPadraoDevAcaoPermissao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
            new GeradorERPImplementacaoContexto(ERPAgenda.CRM_CARAMELO).salvarEmDiretorioPadraCASO_NAO_EXISTA();

            for (Class entidade : ERPAgenda.CRM_CARAMELO.getInterfacesDeEntidade()) {
                GeradorDTOInterface geradorInterface = new GeradorDTOInterface(ERPAgenda.CRM_CARAMELO, entidade);
                geradorInterface.salvarEmDiretorioPadraoSubstituindoAnterior();
                GeradorDTOPojo geradorPojo = new GeradorDTOPojo(ERPAgenda.CRM_CARAMELO, entidade);
                geradorPojo.salvarEmDiretorioPadraCASO_NAO_EXISTA();
                GeradorDTOProcessador geradorProcessador = new GeradorDTOProcessador(ERPAgenda.CRM_CARAMELO, entidade);
                geradorProcessador.salvarEmDiretorioPadraCASO_NAO_EXISTA();

            }
        } catch (Throwable t) {
            LogManager.getLogger(LogPadraoSB.class).error("Erro Criando anotações", t);
        }
    }

}
