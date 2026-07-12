/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.IntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ComoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ValorLogicoLinkLinkSimplesTest extends TesteCRMCarameloCode {

    public ValorLogicoLinkLinkSimplesTest() {
    }

    /**
     * Test of getValor method, of class ValorLogicoLinkLinkSimples.
     */
    @Test
    public void testGetValor() {

        Pessoa pesoa = UtilSBPersistencia.getRegistroByID(Pessoa.class, 1l, getEM());

        List<TipoDadoCrmLinkIntegracao> tiposLink = UtilSBPersistencia
                .getListaTodos(TipoDadoCrmLinkIntegracao.class, getEM());
        TipoDadoCrmLinkIntegracao tipoLink = tiposLink.get(0);
        IntegracaoLink novaIntegracao = new IntegracaoLink();

        IntegracaoLink integrador = new IntegracaoLink();
        try {
            integrador.prepararNovoObjeto(pesoa, tipoLink);
        } catch (ErroPreparandoObjeto ex) {
            Logger.getLogger(ValorLogicoLinkLinkSimplesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ItfCampoInstanciado campoinstanciadoDado = integrador.getDadoCRM().getCampoInstanciado();

        String valorTextoEmbendes = integrador.getDadoCRM().getValorEnpacotado();
        String valorTexto = (String) campoinstanciadoDado.getValor();
        ComoValorLogicoAtributoObjeto estrategia = integrador.getDadoCRM().getCampoInstanciado().getValorLogicaEstrategia();
        String valor = (String) estrategia.getValor();
    }

}
