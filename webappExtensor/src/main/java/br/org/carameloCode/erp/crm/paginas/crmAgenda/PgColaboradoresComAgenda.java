/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.FabAcaoAdminAgenda;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.InfoAgendaAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.coletivoJava.fw.projetos.fw.api.model.usuariosb.CPUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvio
 */
@InfoAgendaAdmin(acao = FabAcaoAdminAgenda.COLABORADOR_COM_AGENDA_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Colaboradores com agenda", tags = {"ColabCAG"})
public class PgColaboradoresComAgenda extends MB_paginaCadastroEntidades<DisponibilidadeAtdmtPublico> {

    @InfoParametroURL(tipoParametro = TIPO_PARTE_URL.ENTIDADE, nome = "grupo", obrigatorio = false, tipoEntidade = GrupoUsuarioSB.class)
    private ParametroURL prGrupo;

    @Override
    protected void listarDados(boolean mostrarInativos) {
        ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(UsuarioCRM.class)
                .addCondicaoPositivo(CPUsuarioSB.ativo)
                .addcondicaoCampoIgualA(CPUsuarioSB.tipousuario, UsuarioCRM.class.getSimpleName());
        setEntidadesListadas(novaConsulta.gerarResultados());

    }

    @PostConstruct
    public void inicio() {
        getAcoesRegistros().clear();
        adicionarAcaoDeEntidade(FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_FRM_LISTAR_DISPONIBILIDADES);
        adicionarAcaoDeEntidade(FabAcaoAdminAgenda.RESERVAS_ADMIN_MB_GESTAO);
        adicionarAcaoDeEntidade(FabAcaoCrmAtendimentoAgenda.MINHA_AGENDA_FRM_ESCOPO_RESERVA_CLIENTE);
    }

}
