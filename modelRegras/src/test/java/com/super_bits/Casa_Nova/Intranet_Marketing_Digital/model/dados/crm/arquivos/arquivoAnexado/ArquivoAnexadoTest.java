/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.crm.arquivos.arquivoAnexado;

import br.org.coletivojava.fw.utils.servico.ServicoRepositorioDeArquivos.model.ArquivoDeEntidadeComHash;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.List;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author sfurbino
 */
public class ArquivoAnexadoTest extends TesteJunitSBPersistencia {

    public ArquivoAnexadoTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of getId method, of class ArquivoAnexado.
     */
    @Test
    public void testeQuery() {
        try {

            Query query = getEMTeste().createNativeQuery("select ae.id,ae.nome,ae.arquivo, "
                    + " he.hashCalculado as identificacaoHash"
                    + " FROM ArquivoAnexado ae "
                    + " right join  HashsDeArquivoDeEntidade as he "
                    + " on idEntidade=ae.id = ae.id and entidade='ArquivoAnexado'", "QrArquivoDeEntidadeComHash");

            List<ArquivoDeEntidadeComHash> arquivos = query.getResultList();
            for (ArquivoDeEntidadeComHash arquivo : arquivos) {
                System.out.println(arquivo);
            }

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), false, false);
    }

}
