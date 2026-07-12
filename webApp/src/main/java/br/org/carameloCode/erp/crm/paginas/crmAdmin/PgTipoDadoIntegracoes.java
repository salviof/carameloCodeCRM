/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCrmLinkIntegracao;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.util.List;
import javax.annotation.PostConstruct;
import org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao.InfoValorLogicoLinkIntegracao;
import org.coletivojava.fw.utilCoreBase.UtilCRCReflexaoSimples;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "GTDDI", tags = {"Gestao de link integação"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_DADO_DINAMICO_INTEGRACAO_MB_GERENCIAR)
public class PgTipoDadoIntegracoes extends MB_paginaCadastroEntidades<TipoDadoCrmLinkIntegracao> {

    @PostConstruct
    public void inicio() {
        List<Class> classes = UtilCRCReflexaoSimples.getClassesComEstaAnotacao(InfoValorLogicoLinkIntegracao.class, "org.coletivoJava.fw.projetos.crm.plugin.valorLogicoLinkIntegracao", false);

        List<TipoDadoCrmLinkIntegracao> linksExistentes = UtilSBPersistencia.getListaTodos(TipoDadoCrmLinkIntegracao.class, getEMPagina());

        for (TipoDadoCrmLinkIntegracao tipo : linksExistentes) {
            System.out.println(tipo.getNomeClasseLogica());
        }

        for (Class linkEncontrado : classes) {
            final String nome = linkEncontrado.getSimpleName();
            final String nomeSemantico = nome.replace("ValorLogicoLink", "");
            if (linksExistentes.stream().filter(tpl -> tpl.getNomeClasseLogica().contains(nomeSemantico)).findAny().isPresent()) {
                System.out.println("Criando TipoLink:" + nome);
            } else {
                System.out.println(nome + " Foi Encontrado");
            }
        }

    }

}
