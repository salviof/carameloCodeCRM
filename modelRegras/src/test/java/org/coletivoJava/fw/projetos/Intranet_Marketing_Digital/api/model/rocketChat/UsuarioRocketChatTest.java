/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.rocketChat;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ItfErpChatService;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import br.org.coletivoJava.fw.erp.implementacao.chat.model.model.FabTipoSalaMatrix;
import br.org.coletivoJava.fw.erp.implementacao.chat.model.model.SalaMatrxOrg;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class UsuarioRocketChatTest extends TesteCRMCarameloCode {

    public UsuarioRocketChatTest() {
    }

    @Test
    public void testInicio() {

        ItfErpChatService servicoChat = ERPChat.MATRIX_ORG.getImplementacaoDoContexto();
        List<ComoUsuarioChat> usuariosIntranet = new ArrayList<>();
        List<ComoUsuarioChat> usuariosExternos = new ArrayList<>();
        ComoUsuarioChat usuarioDOno = null;

        PessoaJuridica casanovadigital = UtilSBPersistencia.getRegistroByID(PessoaJuridica.class, 2031l, getEMTeste());
        try {
            usuarioDOno = servicoChat.getUsuarioByEmail("salvio@casanovadigital.com.br");
        } catch (ErroConexaoServicoChat ex) {
            Logger.getLogger(UsuarioRocketChatTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (FabTipoSalaMatrix tipoSala : FabTipoSalaMatrix.values()) {
            try {
                switch (tipoSala) {
                    case WTZAP_ATENDIMENTO:
                    case WTZAP_VENDAS:
                        break;

                    case WTZAP_ATENDIMENTO_GRUPO_CLIENTE:
                        break;
                    case MATRIX_CHAT_VENDAS:
                        //cv
                        SalaMatrxOrg salaVendas = tipoSala.getSalaMatrix(casanovadigital, usuarioDOno, new ArrayList<ComoUsuarioChat>(), new ArrayList<ComoUsuarioChat>());
                        Assert.assertEquals("pessoa_juridica_2031_cv", salaVendas.getApelido());
                        break;
                    case MATRIX_CHAT_ATENDIMENTO:
                        SalaMatrxOrg salaGestaoSucesso = tipoSala.getSalaMatrix(casanovadigital, usuarioDOno, new ArrayList<ComoUsuarioChat>(), new ArrayList<ComoUsuarioChat>());
                        Assert.assertEquals("satisfacaoCliente_2031_ca", salaGestaoSucesso.getApelido());
                        break;
                    case MATRIX_CHAT_ATENDIMENTO_CHAMADO:
                        SalaMatrxOrg salaChamado = tipoSala.getSalaMatrix(casanovadigital, usuarioDOno, new ArrayList<ComoUsuarioChat>(), new ArrayList<ComoUsuarioChat>());
                        Assert.assertEquals("chamado_cliente_2031_ct", salaChamado.getApelido());
                        break;
                    case MATRIX_CHAT_DEBATE_INTERNO_LEAD_CLIENTE:
                        SalaMatrxOrg sala = tipoSala.getSalaMatrix(casanovadigital, usuarioDOno, new ArrayList<ComoUsuarioChat>(), new ArrayList<ComoUsuarioChat>());
                        Assert.assertEquals("pessoa_juridica_2031_ca", sala.getApelido());
                        break;
                    default:
                        throw new AssertionError();
                }
            } catch (ErroPreparandoObjeto ex) {
                Logger.getLogger(UsuarioRocketChatTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
