package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implemetation.model.formulario;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAplicacao.ModuloCRMAplicacao;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author salvio
 */
public class RespostaFormularioTest extends TesteJunitSBPersistencia {

    public RespostaFormularioTest() {
    }

    @Test
    public void testSomeMethod() {

        TipoDadoCRM observacao = new TipoDadoCRM();
        observacao.setId(1L);
        observacao.setNome("obs");
        observacao.setLabel("Observacao");
        observacao.setFabricaTipoAtributo(FabTipoAtributoObjeto.HTML);
        UtilSBPersistencia.mergeRegistro(observacao, getEMTeste());

        List<TipoDadoCRM> tipos = UtilSBPersistencia.getListaTodos(TipoDadoCRM.class, getEM());

        for (TipoDadoCRM tipo : tipos) {
            System.out.println(tipo.getNome());
        }
        ModuloCRMAplicacao.formularioSincronizar();

        /// verfica se o dado do cliente foi atualizado;
        renovarConexao();

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), false, true);
    }

}
