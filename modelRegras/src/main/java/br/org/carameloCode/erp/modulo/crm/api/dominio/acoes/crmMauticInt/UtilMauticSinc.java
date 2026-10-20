/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmMauticInt;

import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.CPTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author salvio
 */
public class UtilMauticSinc {

    /**
     * Alias do campo no Mautic para o tipo de dado, usado também para enviar os
     * valores dos dados dinâmicos
     */
    public static String gerarAlias(TipoDadoCRM pTipoDado) {
        return pTipoDado.getNome().trim().toLowerCase().replaceAll("[^a-z0-9_]", "_");
    }

    public static MapaSubstituicao getPlaceHolders() {
        MapaSubstituicao mapaSubstuicao = new MapaSubstituicao();
        final String contatoPrincipal = "contatoPrincipal";
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            EstruturaDeEntidade estruturaEmpresa = MapaObjetosProjetoAtual.getEstruturaObjeto(Pessoa.class);

            EstruturaDeEntidade estruturaContato = MapaObjetosProjetoAtual.getEstruturaObjeto(ContatoProspecto.class);

            //Empresa: no CRM o placeholder é [campo], no Mautic os campos da empresa principal do contato
            for (ItfEstruturaCampoEntidade campoEmpresa : estruturaEmpresa.getCampos()) {
                switch (campoEmpresa.getNomeDeclarado()) {
                    case CPPessoa.nome:
                        mapaSubstuicao.adicionarPalavraChave("[" + CPPessoa.nome + "]", "{companyfield=companyname}");
                        break;
                    case CPPessoa.email:
                        mapaSubstuicao.adicionarPalavraChave("[" + CPPessoa.email + "]", "{companyfield=companyemail}");
                        break;
                    case CPPessoa.telefoneprincipal:
                        mapaSubstuicao.adicionarPalavraChave("[" + CPPessoa.telefoneprincipal + "]", "{companyfield=companyphone}");
                        break;
                    case CPPessoa.observacao:
                        //a observação é enviada como descrição da empresa no MauticSincPessoa
                        mapaSubstuicao.adicionarPalavraChave("[" + CPPessoa.observacao + "]", "{companyfield=companydescription}");
                        break;
                    default:
                        //campo sem equivalente no Mautic
                        break;
                }
            }

            //Contato principal: no CRM o placeholder é [contatoPrincipal.campo]
            for (ItfEstruturaCampoEntidade campoContato : estruturaContato.getCampos()) {
                switch (campoContato.getNomeDeclarado()) {
                    case CPContatoProspecto.nome:
                        //o MauticSincSalvarContato divide o nome em firstname e lastname
                        mapaSubstuicao.adicionarPalavraChave("[" + contatoPrincipal + "." + CPContatoProspecto.nome + "]", "{contactfield=firstname} {contactfield=lastname}");
                        break;
                    case CPContatoProspecto.email:
                        mapaSubstuicao.adicionarPalavraChave("[" + contatoPrincipal + "." + CPContatoProspecto.email + "]", "{contactfield=email}");
                        break;
                    case CPContatoProspecto.celular:
                        mapaSubstuicao.adicionarPalavraChave("[" + contatoPrincipal + "." + CPContatoProspecto.celular + "]", "{contactfield=mobile}");
                        break;
                    case CPContatoProspecto.telefonealternativo:
                        mapaSubstuicao.adicionarPalavraChave("[" + contatoPrincipal + "." + CPContatoProspecto.telefonealternativo + "]", "{contactfield=phone}");
                        break;
                    case CPContatoProspecto.cargo:
                        mapaSubstuicao.adicionarPalavraChave("[" + contatoPrincipal + "." + CPContatoProspecto.cargo + "]", "{contactfield=position}");
                        break;
                    default:
                        //campo sem equivalente no Mautic
                        break;
                }
            }

            //Dados dinâmicos são campos personalizados da empresa (MauticSincCampoDinamico)
            List<TipoDadoCRM> tiposDados = UtilSBPersistencia.getListaTodos(TipoDadoCRM.class, em);
            for (TipoDadoCRM tipoDAdo : tiposDados) {
                mapaSubstuicao.adicionarPalavraChave("[dados." + tipoDAdo.getCPinst(CPTipoDadoCRM.nome).getValor() + "]", "{companyfield=" + gerarAlias(tipoDAdo) + "}");
            }

        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

        return mapaSubstuicao;
    }

}
