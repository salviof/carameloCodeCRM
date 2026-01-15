/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.transitorio.DadosPesquisaGooglePlace;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SalvioF
 */
@InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_MB_GERENCIAR)
@Named
@SessionScoped
@InfoPagina(nomeCurto = "Pesquisa prospecto", tags = {"Encontrar prospectos"})
public class PgPesquisaProspecto extends MB_PaginaConversation {

    private DadosPesquisaGooglePlace dadosPesquisa;
    private List<OrigemProspecto> origens;
    private OrigemProspecto origemSelecionada;

    private OrigemProspecto novaOrigem;

    @InfoParametroURL(nome = "Origem padrao", obrigatorio = false, tipoEntidade = OrigemProspecto.class, tipoParametro = TIPO_PARTE_URL.ENTIDADE, representaEntidadePrincipalMB = false)
    private ParametroURL prOrigem;

    @InfoParametroURL(nome = "Origem padrao", obrigatorio = false, tipoEntidade = OrigemProspectoPrivado.class, tipoParametro = TIPO_PARTE_URL.ENTIDADE, representaEntidadePrincipalMB = false)
    private ParametroURL prOrigemPrivada;
    private List<DadosPesquisaGooglePlace> historicoPesquisas = new ArrayList<>();
    private static final int MAX_HISTORICO = 3; // Limita o histórico a 10 pesquisas

    @PostConstruct
    public void inicio() {
        dadosPesquisa = new DadosPesquisaGooglePlace();
        dadosPesquisa.setLocal("Belo Horizonte");
        historicoPesquisas = new ArrayList<>();
        origens = UtilSBPersistencia.getListaTodos(OrigemProspecto.class, getEMPagina());
        novaOrigem = new OrigemProspecto();

        executaAcaoSelecionadaPorEnum(FabAcaoCRMAtendimento.DESCOBRIDOR_PROSPECTO_FRM_LISTAR_DESCOBERTAS);

        if (getParametroInstanciado(prOrigem).isValorDoParametroFoiConfigurado()) {
            origemSelecionada = (OrigemProspecto) getParametroInstanciado(prOrigem).getValor();
        }
        if (getParametroInstanciado(prOrigemPrivada).isValorDoParametroFoiConfigurado()) {
            origemSelecionada = (OrigemProspecto) getParametroInstanciado(prOrigem).getValor();
        }

    }

    public void pesquisar() {
        if (dadosPesquisa != null && !dadosPesquisa.isTemPageToken()) {
            salvarPesquisaNoHistorico();
        }
        ModuloCRMAtendimento.descobrirProspectos(dadosPesquisa);
    }

    public void salvarPesquisaNoHistorico() {
        if (dadosPesquisa.getTermo() == null || dadosPesquisa.getTermo().trim().isEmpty()
                || dadosPesquisa.getLocal() == null || dadosPesquisa.getLocal().trim().isEmpty()) {
            return;
        }
        DadosPesquisaGooglePlace copiaPesquisa = new DadosPesquisaGooglePlace();
        copiaPesquisa.setTermo(dadosPesquisa.getTermo());
        copiaPesquisa.setLocal(dadosPesquisa.getLocal());
        copiaPesquisa.setPageToken(null); // Nunca salva pageToken no histórico
        historicoPesquisas.add(copiaPesquisa);
        if (historicoPesquisas.size() > MAX_HISTORICO) {
            historicoPesquisas.remove(0); // Remove a pesquisa mais antiga
        }
    }

    public void voltarPesquisaAnterior() {
        if (!historicoPesquisas.isEmpty()) {
            int ultimoIndice = historicoPesquisas.size() - 1;
            DadosPesquisaGooglePlace pesquisaAnterior = historicoPesquisas.remove(ultimoIndice);
            this.dadosPesquisa.setTermo(pesquisaAnterior.getTermo());
            this.dadosPesquisa.setLocal(pesquisaAnterior.getLocal());
            this.dadosPesquisa.setPageToken(null);
            this.dadosPesquisa.getDadosEncontrados().clear();
            pesquisar();
        }

    }

    public void limparBusca() {
        if (this.dadosPesquisa != null) {
            this.dadosPesquisa.setTermo(null);
            this.dadosPesquisa.setPageToken(null);
            this.dadosPesquisa.isTemPageToken();
            this.dadosPesquisa.getDadosEncontrados().clear();
            // NÃO limpa o local, mantém o valor padrão
        }
    }

    public void limparHistoricoCompleto() {
        historicoPesquisas.clear();
    }

    public boolean isExibirBotaoVoltar() {
        return !historicoPesquisas.isEmpty();
    }

    public List<DadosPesquisaGooglePlace> getHistoricoPesquisas() {
        return new ArrayList<>(historicoPesquisas); // Retorna cópia para segurança
    }

    public void resetarPesquisa() {
        this.dadosPesquisa.setPageToken(null);
        this.dadosPesquisa.isTemPageToken();
        this.dadosPesquisa.getDadosEncontrados().clear();
    }

    public void salvarProspectos() {

    }

    public void salvarProsPecto(PessoaJuridica pProspecto) {
        if (origemSelecionada == null) {
            SBCore.enviarMensagemUsuario("Você precisa selecionadr uma origem", FabMensagens.ALERTA);
        } else {
            pProspecto.setOrigem(origemSelecionada);
            ModuloCRMAtendimento.prospectoSalvar(pProspecto).dispararMensagens().dispararMensagens();

        }

    }

    public void salvarNovaOrigem() {

        ItfResposta resp = ModuloCRMAdmin.origemProspectoSalvar(origemSelecionada);
        if (resp.isSucesso()) {
            novaOrigem = (OrigemProspecto) resp.getRetorno();

            renovarEMPagina();
            origens = UtilSBPersistencia.getListaTodos(OrigemProspecto.class, getEMPagina());
            for (OrigemProspecto ori : origens) {
                if (ori.getId().equals(novaOrigem.getId())) {
                    origemSelecionada = ori;
                }
            }
            novaOrigem = new OrigemProspecto();
        }
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return dadosPesquisa;
    }

    public OrigemProspecto getNovaOrigem() {
        return novaOrigem;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

    public DadosPesquisaGooglePlace getDadosPesquisa() {
        return dadosPesquisa;
    }

    public void setDadosPesquisa(DadosPesquisaGooglePlace dadosPesquisa) {
        this.dadosPesquisa = dadosPesquisa;
    }

    public List<OrigemProspecto> getOrigens() {
        return origens;
    }

    public void setOrigens(List<OrigemProspecto> origens) {
        this.origens = origens;
    }

    public OrigemProspecto getOrigemSelecionada() {
        return origemSelecionada;
    }

    public void setOrigemSelecionada(OrigemProspecto origemSelecionada) {
        this.origemSelecionada = origemSelecionada;
    }

}
