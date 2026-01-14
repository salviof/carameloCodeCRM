/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.CPDisponibilidadeAtdmtPublico;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.FabAcaoAgendaMentoPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.controller.disponibilidades.InfoAcaoAgendamentoPublico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author sfurbino
 */
@InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_MB_GESTAO)
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Minhas disponibilidades", tags = {"minhadisp"})
public class PgMinhasDisponibilidadesPublicas extends MB_paginaCadastroEntidades<DisponibilidadeAtdmtPublico> {

    @Override
    protected void autoexecEntidadeNova() {
        super.autoexecEntidadeNova(); //To change body of generated methods, choose Tools | Templates.
        getEntidadeSelecionada().setUsuarioResponsavel(UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina()));
        getEntidadeSelecionada().setAtendentesDisponiveis(new ArrayList<>());
        getEntidadeSelecionada().getAtendentesDisponiveis().add(UtilSBPersistencia.loadEntidade(SBCore.getUsuarioLogado(), getEMPagina()));
    }

    public void editarEntidade() {
        setAcaoSelecionada(FabAcaoAgendaMentoPublico.MINHA_DISPONIBILIDADE_FRM_EDITAR.getRegistro());
        executarAcaoSelecionada();
    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        ConsultaDinamicaDeEntidade consultaMinhasDisponibilidades = new ConsultaDinamicaDeEntidade(DisponibilidadeAtdmtPublico.class, getEMPagina()).addCondicaoManyToOneIgualA(CPDisponibilidadeAtdmtPublico.usuarioresponsavel, SBCore.getUsuarioLogado());
        List<DisponibilidadeAtdmtPublico> disponibilidades = consultaMinhasDisponibilidades.resultadoRegistros();
        setEntidadesListadas(disponibilidades);
    }

    @Override
    public void setAcaoSelecionada(ComoAcaoDoSistema acaoSelecionada) {
        super.setAcaoSelecionada(acaoSelecionada); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void executarAcao(DisponibilidadeAtdmtPublico pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); //To change body of generated methods, choose Tools | Templates.
    }

}
