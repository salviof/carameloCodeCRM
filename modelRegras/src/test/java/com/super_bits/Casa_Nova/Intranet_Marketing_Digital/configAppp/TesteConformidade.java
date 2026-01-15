
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoFormLead.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.FabConfigModuloIntranet;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class TesteConformidade extends TesteCRMCarameloCode {

    @Test
    public void testePesquisa() {
        try {
            MapaObjetosProjetoAtual.adcionarObjeto(ContatoAnonimoDadoTansitorio.class);
            MapaObjetosProjetoAtual.adcionarObjeto(MetadadoUsuarioCliente.class);
            MapaObjetosProjetoAtual.adcionarObjeto(PesquisaAtividade.class);
            //MapaObjetosProjetoAtual.adcionarObjeto(IntegracaoLink.class);
            gerarCodigoModelProjeto();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "falha executando teste do projeto ataul", t);
            throw t;
        }
        //  gerarCodigoConvercaoObjetosIntegracaoAplicacao();
    }

    public void teste() {
        try {

            //SBCore.getConfigModulo(FabConfigModuloJiraRequisitos.class);
            SBCore.getConfigModulo(FabConfigModuloIntranet.class);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
