/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@Named
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.TIPO_ATIVIDADE_MB_GERENCIAR)
@InfoPagina(nomeCurto = "TA", tags = {"Tipo Atividade"})
@ViewScoped
public class PgTipoAtividade extends MB_paginaCadastroEntidades<TipoAtividadeCRM> {

    @InfoParametroURL(nome = "Parametro tipo de atividade", tipoEntidade = TipoAtividadeCRM.class,
            tipoParametro = TIPO_PARTE_URL.ENTIDADE, obrigatorio = false, representaEntidadePrincipalMB = true)
    private ParametroURL parametroTipoAtividade;
    private List<AtividadeCRM> atividades;

    private TipoAtividadeCRM atividadeDestino;

    public List<AtividadeCRM> getAtividadesVinculadas() {
        if (atividades == null || atividades.isEmpty()) {
            ConsultaDinamicaDeEntidade consulta = UtilSBPersistencia.gerarConsultaDeEntidade(AtividadeCRM.class, getEMPagina());
            consulta.addCondicaoManyToOneIgualA(getEntidadeSelecionada());
            atividades = consulta.resultadoRegistros();
        }
        return atividades;

    }

    public PgTipoAtividade() {
        super();

        removerAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_VINCULAR_EMAIL.getRegistro());
        removerAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_DESVINCULAR_EMAIL.getRegistro());
        removerAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_REMOVER.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_REMOVER.getRegistro());
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_DADOS_COLETAR.getRegistro());

    }

    @PostConstruct
    public void inicio() {
        listarDados();
        adicionarAcaoDeEntidade(FabAcaoCrmAdmin.TIPO_ATIVIDADE_FRM_FORMATAR_EMAIL_VINCULADO.getRegistro());

    }

    @Override
    public void executarAcao(TipoAtividadeCRM pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada); //To change body of generated methods, choose Tools | Templates.
        if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.TIPO_ATIVIDADE_CTR_VINCULAR_EMAIL)) {
            renovarEMPagina();
            setEntidadeSelecionada(UtilSBPersistencia.loadEntidade(getEntidadeSelecionada(), getEMPagina()));
        }
    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
    }

    public TipoAtividadeCRM getAtividadeDestino() {
        return atividadeDestino;
    }

    public void setAtividadeDestino(TipoAtividadeCRM atividadeDestino) {
        this.atividadeDestino = atividadeDestino;
    }

    public void moverAtividade() {

    }

}
