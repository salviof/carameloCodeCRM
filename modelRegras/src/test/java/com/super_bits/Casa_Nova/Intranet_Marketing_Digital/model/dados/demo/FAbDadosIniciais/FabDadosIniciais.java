/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.porteEmpresa.FabPorteProspectoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.porteEmpresa.Porte;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.FabTipoRelacionamentoMarketingDigital;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.FabUsuarioPadraoMarketingParaWeb;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public enum FabDadosIniciais implements ComoFabrica {

    PROSPECTO1,
    PROSPECTO2,
    PROSPECTO3,;

    @Override
    public Object getRegistro() {

        EntityManager em = UtilSBPersistencia.getNovoEM();

        switch (this) {

            case PROSPECTO1:

                PessoaJuridica prospecto1 = new PessoaJuridica();
                //    prospecto1.prepararNovoObjeto();
                prospecto1.setOrigem((OrigemProspecto) FabTipoOrigemDemo.LEADS_VIA_SITE.getRegistro());
                prospecto1.setId(1l);
                prospecto1.setNome("prospecto teste 1"); // obrigatorio
                prospecto1.setEmail("salviof@gmail.com");
                prospecto1.setSite("casanovadigital.com.br");
                prospecto1.setResponsavel("Joao da silva");
                //      prospecto1.setTipoEmpresa(FabTipoEmpresa.COMERCIO.getRegistro());
                prospecto1.setRelacionamento(FabTipoRelacionamentoMarketingDigital.QUALIFICADO.getRegistro());// obrigatorio

                prospecto1.setPorte((Porte) UtilSBPersistencia.loadEntidade(FabPorteProspectoEmpresa.MEDIA.getRegistro(), em));//obrigatorio

                prospecto1.getUsuariosResponsaveis().add((UsuarioCRM) FabUsuarioPadraoMarketingParaWeb.ATENDIMENTO.getRegistro());

                return prospecto1;

            case PROSPECTO2:

                PessoaJuridica prospecto2 = new PessoaJuridica();
                //     prospecto2.prepararNovoObjeto();
                prospecto2.setId(2l);
                prospecto2.setNome("prospecto teste 2");
                prospecto2.setEmail("prospecto2@teste.com");
                prospecto2.setTipoEmpresa(FabTipoEmpresa.INDRUSTRIA.getRegistro());
                //    prospecto2.setOrigem((OrigemProspecto) FabTipoOrigemDemo.LEADS_VIA_SITE.getRegistro());
                prospecto2.setRelacionamento(FabTipoRelacionamentoMarketingDigital.QUALIFICADO.getRegistro());// obrigatorio

                prospecto2.setPorte((Porte) UtilSBPersistencia.loadEntidade(FabPorteProspectoEmpresa.MEDIA.getRegistro(), em));//obrigatorio

                prospecto2.getUsuariosResponsaveis().add((UsuarioCRM) FabUsuarioPadraoMarketingParaWeb.ATENDIMENTO.getRegistro());

                //prospecto2.getAtividadesRealizadas().add((AtividadeCRM) UtilSBPersistencia.loadEntidade(FabTipoAtividadeCRM.CRIAR_PROSPECTO.gerarNovaAtividadeCRM(prospecto2), em));
                return prospecto2;

            case PROSPECTO3:

                PessoaJuridica prospecto3 = new PessoaJuridica();
                //        prospecto3.prepararNovoObjeto();
                prospecto3.setId(3l);
                prospecto3.setNome("prospecto teste 3");
                prospecto3.setEmail("prospecto3@teste.com");

                prospecto3.setRelacionamento(FabTipoRelacionamentoMarketingDigital.QUALIFICADO.getRegistro());// obrigatorio

                prospecto3.getUsuariosResponsaveis().add((UsuarioCRM) FabUsuarioPadraoMarketingParaWeb.ATENDIMENTO.getRegistro());

                //prospecto3.getAtividadesRealizadas().add((AtividadeCRM) UtilSBPersistencia.loadEntidade(FabTipoAtividadeCRM.CRIAR_PROSPECTO.gerarNovaAtividadeCRM(prospecto3), em));
                return prospecto3;

            default:

                throw new AssertionError(this.name());

        }

    }

}
