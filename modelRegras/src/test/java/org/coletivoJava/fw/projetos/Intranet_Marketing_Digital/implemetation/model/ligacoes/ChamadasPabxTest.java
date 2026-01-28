package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implemetation.model.ligacoes;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPersistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRealizada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.TipoAtvChamadaRecebida;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.telefone.Telefone;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

public class ChamadasPabxTest extends TesteJunitSBPersistencia {
    public ChamadasPabxTest() {
    }

    @Test
    public void testChamadaRecebida() {
        TipoAtvChamadaRecebida tipoChamadaRecebida = new TipoAtvChamadaRecebida();
        tipoChamadaRecebida.setId(1L);
        tipoChamadaRecebida.setNomeAtividade("Chamada Recebida");
        tipoChamadaRecebida.setNomeInicioAtivida("Chamada Recebida");
        tipoChamadaRecebida.setNomeFimAtividaed("Receber");
        UtilSBPersistencia.mergeRegistro(tipoChamadaRecebida, getEMTeste());

        TipoAtvChamadaRealizada tipoChamadaRealizada = new TipoAtvChamadaRealizada();
        tipoChamadaRealizada.setId(2L);
        tipoChamadaRealizada.setNomeAtividade("Chamada Realizada");
        tipoChamadaRealizada.setNomeInicioAtivida("Chamada Realizada");
        tipoChamadaRealizada.setNomeFimAtividaed("Realizar");
        UtilSBPersistencia.mergeRegistro(tipoChamadaRealizada, getEMTeste());

        Telefone telefonePabx = new Telefone();
        telefonePabx.setId(1L);
        telefonePabx.setTelefone(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional("3121159751"));
        telefonePabx.setCodigoApiWhatsapp("732627579934036");
        telefonePabx.setTipoChamadaRecebida(tipoChamadaRecebida);
        telefonePabx.setTipoChamadaRealizada(tipoChamadaRealizada);
        UtilSBPersistencia.mergeRegistro(telefonePabx, getEMTeste());

        Telefone telefonePabx2 = new Telefone();
        telefonePabx2.setId(2L);
        telefonePabx2.setTelefone(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional("3121159755"));
        telefonePabx.setCodigoApiWhatsapp("103007756220088");
        telefonePabx2.setTipoChamadaRecebida(tipoChamadaRecebida);
        telefonePabx2.setTipoChamadaRealizada(tipoChamadaRealizada);
        UtilSBPersistencia.mergeRegistro(telefonePabx2, getEMTeste());

        Pessoa prospecto = new Pessoa();
        prospecto.setNome("Teste");
        UtilSBPersistencia.mergeRegistro(prospecto, getEMTeste());

        ContatoProspecto contatoProspecto = new ContatoProspecto();
        contatoProspecto.setAtivo(true);
        contatoProspecto.setNome("Teste");

        contatoProspecto.setCelularFormatoInternacional(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional("031986831481"));
        contatoProspecto.setProspecto(prospecto);
        UtilSBPersistencia.mergeRegistro(contatoProspecto, getEMTeste());

        Pessoa prospecto2 = new Pessoa();
        prospecto2.setNome("Teste");
        prospecto2.setEmail("samuelfilipevianadois@gmail.com");
        prospecto2.setTelefonePrincipal("31986831481");
        UtilSBPersistencia.mergeRegistro(prospecto2, getEMTeste());

        ContatoProspecto contatoProspecto2 = new ContatoProspecto();
        contatoProspecto2.setCelularFormatoInternacional(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional("31986831481"));
        contatoProspecto2.setCelular("31986831481");
        contatoProspecto2.getMensagens();
        contatoProspecto2.setAtivo(true);
        contatoProspecto2.setNome("Teste2");



        contatoProspecto2.setCelularFormatoInternacional(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional("31986831481"));
        contatoProspecto2.setProspecto(prospecto2);

        contatoProspecto2.getAcoesDisponiveis();


        UtilSBPersistencia.mergeRegistro(contatoProspecto2, getEMTeste());

        ModuloCRMAplicacao.sincronizarChamadasPabx();
        renovarConexao();

    }


    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaCrmCarameloCode(), true, true);
    }
}
