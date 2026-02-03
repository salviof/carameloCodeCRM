/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.model.dadocrm.CPDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.coletivoJava.fw.api.erp.ia.escopo.ERP_IA;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ValorLogicoDDIATest extends TesteCRMCarameloCode {

    /**
     * Test of getValor method, of class ValorLogicoDDIA.
     */
    @Test
    public void testGetValor() {

        MetaRelacionamento meta = new MetaRelacionamento();
        meta.setNome("Meta teste");
        meta.setPeso(1);
        meta = (MetaRelacionamento) ModuloCRMAdmin.metaRelacionamentoSalvar(meta).getRetorno();

        TipoRelacionamento tipoRelacionamento = new TipoRelacionamento();
        tipoRelacionamento.setNome("Relacionamento Teste");
        tipoRelacionamento.setMetaRelacionamento(meta);
        tipoRelacionamento.setPeso(0);
        tipoRelacionamento.setCor("#ffffff");

        tipoRelacionamento = (TipoRelacionamento) ModuloCRMAdmin.tipoRelacionamentoSalvar(tipoRelacionamento).getRetorno();
        tipoRelacionamento = UtilSBPersistencia.loadEntidade(tipoRelacionamento, getEM());

        TipoDadoIA tipoDadoIa = new TipoDadoIA();
        tipoDadoIa.setImplementacaoPadrao(ERP_IA.OLHAMA);
        tipoDadoIa.setLabel("Teste");
        tipoDadoIa.setConteudoPerguntaIA("Quanto é 2 + 2, e qual é o nome do lead?");
        tipoDadoIa = (TipoDadoIA) ModuloCRMAdmin.tipoDadoDinamicoLogico(tipoDadoIa).getRetorno();

        tipoDadoIa = UtilSBPersistencia.loadEntidade(tipoDadoIa, getEM());

        PessoaJuridica p = new PessoaJuridica();

        p.setNome("João da silva");
        p = (PessoaJuridica) ModuloCRMAtendimento.prospectoSalvar(p).getRetorno();
        p = UtilSBPersistencia.loadEntidade(p, getEM());
        DadoCRM dado = FabDadoCRM.getDadoNovoSeNaoExistir(p, tipoDadoIa);
        dado.getCampoInstanciado().getValor();

        System.out.println(dado.getValor());

    }

}
