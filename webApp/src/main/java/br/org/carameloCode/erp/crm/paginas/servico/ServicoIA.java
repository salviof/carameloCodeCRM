package br.org.carameloCode.erp.crm.paginas.servico;

import br.org.carameloCode.erp.modulo.crm.api.model.execucaoia.CPExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.controller.ModuloExecucaoAcao;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ErroChamadaController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoFabricaExecucaoIA;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 *
 * @author salvio
 */
@ViewScoped
@Named
public class ServicoIA implements Serializable {

    private ExecucaoIA dialogoIA;

    private String nomeEntidade;
    private Long codigoEntidade;

    @Inject
    private ItfPaginaAtual paginaAtual;

    public synchronized ExecucaoIA getDialogoIA() {
        ComoEntidadeSimples entidadeSelecionade = ((ComoEntidadeSimples) paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada());
        if (dialogoIA != null) {
            if (codigoEntidade != null && entidadeSelecionade != null && codigoEntidade.toString().equals(entidadeSelecionade.getId().toString())) {
                return dialogoIA;
            }
        }
        if (entidadeSelecionade == null) {
            return null;
        }

        if (entidadeSelecionade.getId() == null) {
            return null;
        }
        if (nomeEntidade == null) {
            nomeEntidade = UtilCRCReflexaoObjeto.getClassExtraindoProxy(entidadeSelecionade.getClass().getSimpleName()).getSimpleName();
        }
        if (dialogoIA != null) {
            if (codigoEntidade == ((ComoEntidadeSimples) paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada()).getId()) {
                return dialogoIA;
            }
        }
        if (codigoEntidade == null || !codigoEntidade.toString().equals(entidadeSelecionade.getId().toString())) {
            codigoEntidade = entidadeSelecionade.getId();
        }
        ConsultaDinamicaDeEntidade novaConsulta = new ConsultaDinamicaDeEntidade(ExecucaoIA.class, paginaAtual.getInfoPagina().getComoPaginaComEntityManager().getEMPagina());
        novaConsulta.addcondicaoCampoIgualA(CPExecucaoIA.entidadealvonome, nomeEntidade);
        novaConsulta.addcondicaoCampoIgualA(CPExecucaoIA.entidadealvocodigo, entidadeSelecionade.getId().toString());
        dialogoIA = novaConsulta.getPrimeiroRegistro();
        if (dialogoIA != null) {
            dialogoIA.getInstrucoes().size();
            dialogoIA.getPersona().getInstrucoesCompartilhadas().size();
        }
        return dialogoIA;
    }

    public boolean isTemDialogo() {
        if (getDialogoIA() == null) {
            return false;
        }
        return true;
    }

    public void atualizarDialogoIAParaEntidade(ComoAcaoDoSistema pAcaoAtualizar) {
        atualizarDialogoIAParaEntidade(pAcaoAtualizar, true);
    }

    /**
     *
     * @param pAcaoAtualizar ação que executa o diálogo e aplica a resposta na
     * entidade
     * @param pRecarregarEntidade false para ações transitórias, que alteram a
     * entidade em edição sem gravar (recarregar descartaria a alteração)
     */
    public void atualizarDialogoIAParaEntidade(ComoAcaoDoSistema pAcaoAtualizar, boolean pRecarregarEntidade) {

        ComoEntidadeSimples entidadeSelecionade = ((ComoEntidadeSimples) paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada());
        //instância em edição, usada pelas ações transitórias
        dialogoIA.setEntidadeVinculada(entidadeSelecionade);
        try {
            ItfRespostaAcaoDoSistema resposta = CarameloCode.getServicoController().getResposta(pAcaoAtualizar.getEnumAcaoDoSistema(), dialogoIA);
            if (resposta.isSucesso() && pRecarregarEntidade) {
                paginaAtual.getInfoPagina().getComoPaginaEntidade().atualizarEntidadeSelecionada();
                dialogoIA = UtilSBPersistencia.loadEntidade(dialogoIA, paginaAtual.getInfoPagina().getComoPaginaComEntityManager().getEMPagina());
                dialogoIA.getInstrucoes().size();
            }
            if (!resposta.isSucesso()) {
                resposta.dispararMensagens();
            }
        } catch (ErroChamadaController ex) {
            CarameloCode.getServicoMensagemFireForget().enviarMsgAlertaAoUsuario("Impossível iniciar a execução de " + pAcaoAtualizar.getNomeAcao());
            return;
        }

    }

    public void criarDialogoIAParaEntidade(ComoAcaoDoSistema pAcaoCriarDialogo) {
        ItfRespostaAcaoDoSistema resposta = null;

        ComoEntidadeSimples entidade = ((ComoEntidadeSimples) paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada());
        ComoFabricaExecucaoIA fabrica = SBCore.getServicoIA().getFabricaExecucaoPorEntidade(entidade);
        ExecucaoIA execucao;
        try {
            execucao = fabrica == null ? null : (ExecucaoIA) fabrica.getExecucaoIAByEntidade(entidade);
            if (execucao == null) {
                CarameloCode.getServicoMensagemFireForget().enviarMsgAlertaAoUsuario("Não foi possível preparar o diálogo de IA para este registro");
                return;
            }
        } catch (ErroPreparandoObjeto ex) {
            CarameloCode.getServicoMensagemFireForget().enviarMsgAlertaAoUsuario(ex.getMessage());
            return;
        }

        try {
            resposta = CarameloCode.getServicoController().getResposta(pAcaoCriarDialogo.getEnumAcaoDoSistema(), execucao);
        } catch (ErroChamadaController ex) {
            CarameloCode.getServicoMensagemFireForget().enviarMsgAlertaAoUsuario("Impossível iniciar a execução de " + pAcaoCriarDialogo.getNomeAcao());
            return;
        }

        if (!resposta.isSucesso()) {
            resposta.dispararMensagens();
        }
        if (dialogoIA != null) {
            dialogoIA = UtilSBPersistencia.loadEntidade(dialogoIA, paginaAtual.getInfoPagina().getComoPaginaComEntityManager().getEMPagina());
        }

    }

    public String getInstrucoesIA() {
        String md = getDialogoIA().getCPinst(CPExecucaoIA.systempromptmd).getValor().toString();
        List<Extension> ext = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(ext).build();
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(ext)
                .escapeHtml(true) // escapa HTML cru dentro do .md
                .build();

        String html = renderer.render(parser.parse(md));
        return html;
    }

}
