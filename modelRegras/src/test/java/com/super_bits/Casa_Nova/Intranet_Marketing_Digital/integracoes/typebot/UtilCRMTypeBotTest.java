/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.typebot;

import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.CPTipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.resposta.RespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.google.api.client.util.Lists;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCodeSemLimparBanco;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class UtilCRMTypeBotTest extends TesteCRMCarameloCodeSemLimparBanco {

    public UtilCRMTypeBotTest() {
    }

    private final static String EXEMPLO1 = "{ \"id\": \"y2gzil5krkyvagvepi0zvvwc\", \"createdAt\": \"2026-03-20T16:25:33.057Z\", \"typebotId\": \"cmemuavrf000jfp4tqcnnscdf\", \"variables\": [ { \"id\": \"vlzysi29dkqk8s6ileyhkq54t\", \"name\": \"PORTE\", \"isSessionVariable\": false, \"value\": \"1\" }, { \"id\": \"vvazhkz1pe3gg42xm39of04gp\", \"name\": \"REFERENCIA\", \"isSessionVariable\": false, \"value\": \"camila\" }, { \"id\": \"vk2meeqkeiuj073pm9ba0m7by\", \"name\": \"NOME\", \"isSessionVariable\": false, \"value\": \"Cristiane\" }, { \"id\": \"vuabpkqorc5qf0gxlzl5oi8dn\", \"name\": \"EMPRESA\", \"isSessionVariable\": false, \"value\": \"La Fontana Massas\" }, { \"id\": \"vmork88m0l7ah894va955sonn\", \"name\": \"TELEFONE\", \"isSessionVariable\": false, \"value\": \"+5531994679114\" }, { \"id\": \"vt7444ccpp6ebyyp0m4zrevwy\", \"name\": \"EMAIL\", \"isSessionVariable\": false, \"value\": \"cristianelopes200981@gmail.com\" } ], \"isCompleted\": true, \"hasStarted\": true, \"isArchived\": false, \"lastChatSessionId\": \"a5ej5iyisja43qhkyfyik4v8\", \"answers\": [ { \"blockId\": \"fbx3tlamcfv65vylhsxll4im\", \"content\": \"Vamos lá!\" }, { \"blockId\": \"qy2lo32lvih5w0yo1858l25v\", \"content\": \"Cristiane\" }, { \"blockId\": \"m6c3n9ltwbgp8gcs98nf1069\", \"content\": \"📱 Gestão de Redes Sociais, 💸 Tráfego Pago na Internet\" }, { \"blockId\": \"z499mb13fe0b5thnfc69pypz\", \"content\": \"La Fontana Massas\" }, { \"blockId\": \"whh2lhg3vl34hyw3p0pnxorp\", \"content\": \"+5531994679114\" }, { \"blockId\": \"xov0qjdj7mdaal2jokpx92v3\", \"content\": \"cristianelopes200981@gmail.com\" } ] }";

    public TipoFormulario getFormulario() {
        ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(TipoFormulario.class, getEM());
        novaConsulta.addCondicaoPositivo(CPTipoFormulario.integrardados);
        List<TipoFormulario> tipos = novaConsulta.gerarResultados();
        return tipos.get(tipos.size() - 1);
    }

    /**
     * Test of getReferencias method, of class UtilCRMTypeBot.
     */
    @Test
    public void testGetReferencias() {
        JsonObject resposta = UtilCRCJson.getJsonObjectByTexto("{ \"id\": \"ermnryjxcksesec7pkzxkyb4\", \"createdAt\": \"2026-03-17T16:39:38.314Z\", \"typebotId\": \"cmemuavrf000jfp4tqcnnscdf\", \"variables\": [ { \"id\": \"vlzysi29dkqk8s6ileyhkq54t\", \"name\": \"PORTE\", \"isSessionVariable\": false, \"value\": \"1\" }, { \"id\": \"vvazhkz1pe3gg42xm39of04gp\", \"name\": \"REFERENCIA\", \"isSessionVariable\": false, \"value\": \"camila\" }, { \"id\": \"vk2meeqkeiuj073pm9ba0m7by\", \"name\": \"NOME\", \"isSessionVariable\": false, \"value\": \"Rosi\" }, { \"id\": \"vuabpkqorc5qf0gxlzl5oi8dn\", \"name\": \"EMPRESA\", \"isSessionVariable\": false, \"value\": \"@rosicuida\" }, { \"id\": \"vmork88m0l7ah894va955sonn\", \"name\": \"TELEFONE\", \"isSessionVariable\": false, \"value\": \"+5531995352116\" }, { \"id\": \"vt7444ccpp6ebyyp0m4zrevwy\", \"name\": \"EMAIL\", \"isSessionVariable\": false, \"value\": \"frose9641@gmail.com\" } ], \"isCompleted\": true, \"hasStarted\": true, \"isArchived\": false, \"lastChatSessionId\": \"noogpifquxxmto03o83okpfj\", \"answers\": [ { \"blockId\": \"fbx3tlamcfv65vylhsxll4im\", \"content\": \"Vamos lá!\" }, { \"blockId\": \"qy2lo32lvih5w0yo1858l25v\", \"content\": \"Rosi\" }, { \"blockId\": \"m6c3n9ltwbgp8gcs98nf1069\", \"content\": \"🎯 Consultoria em Marketing\" }, { \"blockId\": \"z499mb13fe0b5thnfc69pypz\", \"content\": \"@rosicuida\" }, { \"blockId\": \"whh2lhg3vl34hyw3p0pnxorp\", \"content\": \"+5531995352116\" }, { \"blockId\": \"xov0qjdj7mdaal2jokpx92v3\", \"content\": \"frose9641@gmail.com\" } ] }");
        try {
            RespostaFormulario resp = UtilCRMTypeBot.gerarRespostaByJson(
                    resposta,
                    getFormulario(), true);
            System.out.println(resp.isAtendenteNotificado());
        } catch (ErroRegraDeNegocio ex) {
            Logger.getLogger(UtilCRMTypeBotTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroValidacao ex) {
            Logger.getLogger(UtilCRMTypeBotTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
