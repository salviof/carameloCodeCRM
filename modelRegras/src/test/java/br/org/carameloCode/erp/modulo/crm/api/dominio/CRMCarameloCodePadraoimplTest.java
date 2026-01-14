/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.api.ItfErpCrm;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidorEmailAvancado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoArquivo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.comunicacao.ComoEmailSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.contato.ComoContatoSimples;
import jakarta.json.JsonObject;
import java.util.List;
import java.util.Map;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ComoLead;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ItfTDadoDinamicoCRM;
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
public class CRMCarameloCodePadraoimplTest extends TesteCRMCarameloCode {

    public CRMCarameloCodePadraoimplTest() {
    }

    /**
     * Test of getEMailServer method, of class CRMCarameloCodePadraoimpl.
     */
    @Test
    public void testGetEMailServer() throws Exception {
        ItfErpCrm erp = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto();
        System.out.println("ok");
        ItfErpCrm erp2 = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto();
        erp.getMedataDadosLead();
    }

}
