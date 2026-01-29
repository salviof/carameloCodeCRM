/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.chat;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroRegraDeNEgocioChat;
import br.org.coletivoJava.fw.api.erp.chat.model.ComoUsuarioChat;
import br.org.coletivoJava.fw.erp.implementacao.chat.ChatMatrixOrgimpl;
import br.org.coletivoJava.integracoes.matrixChat.config.FabConfigApiMatrixChat;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class UtilCRMChatTest extends TesteCRMCarameloCode {

    public UtilCRMChatTest() {
    }
    public static final ChatMatrixOrgimpl SERVICO_MATRIX = (ChatMatrixOrgimpl) ERPChat.MATRIX_ORG.getImplementacaoDoContexto();

    /**
     * Test of isTeveAlteracoesUsuarioChat method, of class UtilCRMChat.
     */
    @Test
    public void testIsTeveAlteracoesUsuarioChat() {

        try {

            UsuarioCRM usuarioCrm = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail("salvio@casanovadigital.com.br");
            ComoUsuarioChat usuario = UtilCRMChat.gerarUsuarioAtendimento(usuarioCrm);
            System.out.println(usuario.getEmailPrincipal());
            System.out.println(usuario.getCodigoUsuario());
            assertTrue("Falha definindo usuario atendimento", isUmUsuarioAtendimento(usuario));

            usuario = SERVICO_MATRIX.getUsuarioByCodigo("@salvio_furbino930:casanovadigital.com.br");
            isUmUsuarioAtendimento(usuario);

        } catch (ErroConexaoServicoChat | ErroRegraDeNEgocioChat ex) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Falha verificando usuario de atendimento", ex);
            fail(ex.getMessage());
        }
    }

    public boolean isUmUsuarioAtendimento(ComoUsuarioChat pUsuarioAtendimento) {
        if (pUsuarioAtendimento == null) {
            return false;
        }
        if (pUsuarioAtendimento.getEmail() != null && pUsuarioAtendimento.getEmail().contains("@")) {

            if (pUsuarioAtendimento.getEmail().split("@")[1].endsWith(FabConfigApiMatrixChat.DOMINIO_FEDERADO.getValorParametroSistema())) {
                return true;
            }
        }
        return isUmUsuarioAtendimento(pUsuarioAtendimento.getCodigoUsuario());
    }

    private boolean isUmUsuarioAtendimento(String pCodigo) {
        if (pCodigo == null) {
            return false;
        }
        return pCodigo.contains(".at:");
    }
}
