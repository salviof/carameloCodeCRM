package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.Atividade.tiposEspeciais;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMEmail;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class AtividadeCRMEmailRecepcaoTest extends TesteCRMCarameloCode {

    public AtividadeCRMEmailRecepcaoTest() {
    }

    /**
     * Test of getCodigoImap method, of class AtividadeCRMEmailRecepcao.
     */
    @Test
    public void testGetCodigoImap() {

        UsuarioCRM salvio = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail("salvio@casanovadigital.com.br");
        UsuarioCrmCliente usrCli = (UsuarioCrmCliente) SBCore.getServicoPermissao().getUsuarioByEmail("salviof@gmail.com");
        usrCli = UtilSBPersistencia.loadEntidade(usrCli, getEM());
        //SBPersistencia.criarRegistrosIniciais();

        ModuloCRMEmail.receberEmail(salvio);

    }

}
