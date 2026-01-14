/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implemetation.model.areatrabalho;

import br.org.carameloCode.erp.modulo.crm.implemetation.model.areatrabalho.ValorLogicoAreaTrabalhoNaoLidoEmaiDesconhecidolQtd;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.ConfiguradorCoreCRM_CRC;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class ValorLogicoAreaTrabalhoNaoLidoEmaiDesconhecidolQtdTest extends TesteCRMCarameloCode {

    @Override
    public void configAmbienteDesevolvimento() {

        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), true, false);

    }

    /**
     * Test of getAreaTrabalho method, of class
     * ValorLogicoAreaTrabalhoNaoLidoEmaiDesconhecidolQtd.
     */
    @Test
    public void testGetAreaTrabalho() {
        SBCore.getServicoSessao().logarEmailESenha("salviof@gmail.com", "123");
        UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEM());
        AreaTrabalho area = usuario.getAreatrabalhoPadrao();
        ItfCampoInstanciado campo = area.getCPinst("naoLidoEmaiDesconhecidolQtd");
        ValorLogicoAreaTrabalhoNaoLidoEmaiDesconhecidolQtd valor = new ValorLogicoAreaTrabalhoNaoLidoEmaiDesconhecidolQtd(campo);
        int quantidade = (int) valor.getValor();
    }

}
