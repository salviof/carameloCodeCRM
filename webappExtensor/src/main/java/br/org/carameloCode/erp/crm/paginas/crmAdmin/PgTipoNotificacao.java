/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tipoNotificacao.TiponotificacaoCRM;

/**
 *
 * @author salvio
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "tipoNotificacao", tags = {"Tipo Notificação"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_NOTIFICACAO_MB)
public class PgTipoNotificacao extends MB_paginaCadastroEntidades<TiponotificacaoCRM> {

}
