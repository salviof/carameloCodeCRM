package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.disponibilidade.DisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.FabAcaoAdminAgenda;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.adminAgenda.InfoAgendaAdmin;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.CPDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
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
@InfoAgendaAdmin(acao = FabAcaoAdminAgenda.AGENDA_ADMIN_DIPONIBILIDADES_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Agenda de Colaboradoers", tags = {"AGCOLABS"})
public class PgAgendaDispColaboradores extends MB_paginaCadastroEntidades<DisponibilidadeAtdmtPublico> {

    @InfoParametroURL(nome = "PrColaborador", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = UsuarioSB.class, obrigatorio = false)
    private ParametroURL prColaborador;

    @InfoParametroURL(nome = "prEntidade", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = DisponibilidadeAtdmtPublico.class, obrigatorio = false, representaEntidadePrincipalMB = true)
    private ParametroURL prDisponibilidade;

    @PostConstruct
    public void inicio() {

    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(DisponibilidadeAtdmtPublico.class, getEMPagina());

        if (getParametroInstanciado(prColaborador).isValorDoParametroFoiConfigurado()) {
            novaConsulta.addCondicaoManyToOneIgualA(CPDisponibilidadeAtdmtPublico.usuarioresponsavel, (ComoEntidadeSimples) getParametroInstanciado(prColaborador).getValor());
        }
        setEntidadesListadas(novaConsulta.gerarResultados());
    }

    @Override
    public void executarAcao(DisponibilidadeAtdmtPublico pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
