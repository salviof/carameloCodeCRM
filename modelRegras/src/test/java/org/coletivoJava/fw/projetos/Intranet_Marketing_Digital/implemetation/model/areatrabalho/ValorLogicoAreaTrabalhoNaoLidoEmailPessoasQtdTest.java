/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implemetation.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.implemetation.model.areatrabalho.ValorLogicoAreaTrabalhoNaoLidoEmailPessoasQtd;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.areatrabalho.CPAreaTrabalho;
import org.junit.Test;
import static testesFW.TesteJunitSBPersistencia.getEM;

/**
 *
 * @author sfurbino
 */
public class ValorLogicoAreaTrabalhoNaoLidoEmailPessoasQtdTest extends TesteCRMCarameloCode {

    @Override
    public void configAmbienteDesevolvimento() {

        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), true, false);

    }

    @Test
    public void testGetAreaTrabalho() {
        SBCore.getServicoSessao().logarEmailESenha("salviof@gmail.com", "123");
        UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEM());
        AreaTrabalho area = usuario.getAreatrabalhoPadrao();
        ItfCampoInstanciado campo = area.getCPinst(CPAreaTrabalho.naolidoemailpessoasqtd);
        ValorLogicoAreaTrabalhoNaoLidoEmailPessoasQtd valor = new ValorLogicoAreaTrabalhoNaoLidoEmailPessoasQtd(campo);
        int quantidade = (int) valor.getValor();
        System.out.println(quantidade);
    }

}
