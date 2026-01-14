/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.apis.fatura;

import br.org.coletivoJava.fw.api.erp.erpintegracao.contextos.ERPIntegracaoSistemasApi;
import br.org.coletivoJava.fw.api.erp.erpintegracao.model.ItfSistemaERPLocal;
import br.org.coletivoJava.fw.api.erp.erpintegracao.servico.ItfIntegracaoERP;
import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import br.org.coletivoJava.integracoes.restInterprestfull.api.FabIntApiRestIntegracaoERPRestfull;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabDadosIniciais;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCWebBrowser;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.junit.Test;
import testes.spark.ServidorOauthRecepcaoSpark;
import static testesFW.TesteJunitSBPersistencia.getEM;

/**
 *
 * @author sfurbino
 */
public class TesteComunicacaoFatura extends TesteCRMCarameloCode {

    @Test
    public void testeEnvioOrcamento() {
        Orcamento orcamento = new Orcamento();
        PessoaJuridica prospect = (PessoaJuridica) FabDadosIniciais.PROSPECTO1.getRegistro();

        try {
            orcamento.prepararNovoObjeto(prospect);
        } catch (ErroPreparandoObjeto ex) {

        }

        ItfSistemaERPLocal sistemaAtual = ERPIntegracaoSistemasApi.RESTFUL.getImplementacaoDoContexto().getSistemaAtual();
        System.out.println(sistemaAtual.getHashChavePublica());
        System.out.println("Chave púiblica=");
        System.out.println(sistemaAtual.getChavePublica());

        SistemaERPConfiavel sistemaFaturamento = UtilSBPersistencia.getListaTodos(SistemaERPConfiavel.class, getEM()).
                stream().filter(sis -> sis.getDominio().startsWith("localhost")).findFirst().get();
        System.out.println("Chave publica do fatura=");
        System.out.println(sistemaFaturamento.getHashChavePublica());

        System.out.println("Validação chave Pública");
        String chavePublicaFtaura = "MIICHzANBgkqhkiG9w0BAQEFAAOCAgwAMIICBwKCAf4AjY0+i1BaDFKrlqJ5zb9/oWfPbatBr66wplv+vHJIz+7CETu8OdI+kOFCkwRXOrkUFQ5yXePfKOg83YVDUxjF0Ur0Hvxe0kIsQnwzrSwco1Bl4/PzzQkxZvEB0kF944qHS/XISU4/7a7UO5pg3UoAO9uBuAEjB8FY9V6sk8xFbmjvkdP8b3DNbCn2QhreLcCiWc8Z3MnNKwgzqUymMKFCPk3HzWs21IuEEYz796BSRcXMGrFKlq48ckmOcbHAhXP99twanLG3FDe4e8/rtmde2W/zZTfPO1VPjJ6Tbt6oon+neOn4kNcdmQLQwwkcl9TMqFGJv5rbfL+C05snAqOLaGFMvgw/62KAYk5gX4I2s6SnCO9wI/nl3qp19bJgTWSrpW6IyzfulccnUgRXErj/mgezMGMaBgs9Be/LmUWfOlMQansC39i5ZtkXOVxL1pFjthArYIRdRqU9Hkq3bbpqPXpR6qzL3/wZdkMDPMfixGz4ngoOOaHa9XhFwVvhlaiqZofi3orE8veSmEE1lyveJscqbr+wmWJ4Eu/X/Hvz9dhLD7hMprq7SZHZJ8aqezbQU9PmQYP0rbF9AJwdNPbrKI1ehj2jp3D0CiVrlINxXqELKww4LRN/u5d5Hr4zTzl4YGimZIkgC4+FcFKHnrcbEtfI8VZvGMiXT61cQokCAwEAAQ==";
        System.out.println(chavePublicaFtaura.hashCode());

        SBCore.getServicoSessao().logarEmailESenha("marketing@casanovadigital.com.br", "123");
        ItfTokenGestaoOauth token = (ItfTokenGestaoOauth) FabIntApiRestIntegracaoERPRestfull.ACOES_EXECUTAR_CONTROLLER.getGestaoToken(sistemaFaturamento);
        ERPIntegracaoSistemasApi erpIntegracao = ERPIntegracaoSistemasApi.RESTFUL;
        ItfIntegracaoERP erp = erpIntegracao.getImplementacaoDoContexto();

        ServidorOauthRecepcaoSpark servicoRecepcao = new ServidorOauthRecepcaoSpark(token, 8086, "/solicitacaoAuth2Recept/code/Usuario/GestaoTokenRestInterprestfull");
        if (!token.isPossuiAutenticacaoDeUsuario()) {
            UtilCRCWebBrowser.abrirLinkEmBrownser(token.getUrlObterCodigoSolicitacao());
        }
        servicoRecepcao.start();
        int i = 0;
        boolean autenticado = false;
        while ((i < 100000 && !autenticado)) {
            try {
                Thread.sleep(1000);
                ItfTokenGestaoOauth tokenTeste = (ItfTokenGestaoOauth) FabIntApiRestIntegracaoERPRestfull.ACOES_EXECUTAR_CONTROLLER.getGestaoToken(sistemaFaturamento);
                autenticado = tokenTeste.isTemTokemAtivo();
            } catch (InterruptedException ex) {
                Logger.getLogger(TesteComunicacaoFatura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //http://localhost:8086/solicitacaoAuth2Recept/GestaoTokenRestInterprestfull/code/USUARIO/
        ItfIntegracaoERP integracao = ERPIntegracaoSistemasApi.RESTFUL.getImplementacaoDoContexto();

        ItfResposta resposta = integracao.getResposta(sistemaFaturamento, "FabAcaoMktFaturamentoAtendimento.ORCAMENTO_CTR_SALVAR_MERGE",
                orcamento);
        resposta.getRetorno();
        System.out.println(resposta.getRetorno());
        resposta.dispararMensagens();
    }

}
