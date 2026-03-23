/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.FabAcaoAdminAgenda;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.InfoAgendaAdmin;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@InfoAgendaAdmin(acao = FabAcaoAdminAgenda.RESERVAS_ADMIN_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Reservas de Colaboradores", tags = {"COLAB_RESERVA"})
public class PgAgendaResrvaColaboradores extends MB_paginaCadastroEntidades<ReservaHorario> {

    @InfoParametroURL(nome = "PrColaborador", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioCRM.class, obrigatorio = false)
    private ParametroURL prColaborador;

}
