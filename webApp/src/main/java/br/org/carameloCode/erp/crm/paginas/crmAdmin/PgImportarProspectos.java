/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCOutputs;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.io.File;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author SalvioF
 */
@Named
@ViewScoped
@InfoPagina(nomeCurto = "Importador", tags = {"Importar banco de dados"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_MB)
public class PgImportarProspectos extends MB_PaginaConversation {

    private String caminhoArquhivoImportacao;
    private String nomeArquivoEnviado;
    private String tamanhoArquivoEnviado;
    private boolean arquivoCarregado;
    private List<PessoaJuridica> prospectosImportados;
    private boolean prospectoImportado;

    private OrigemProspecto origem;
    private List<OrigemProspecto> origens;

    public PgImportarProspectos() {

    }

    @PostConstruct
    public void inicio() {

        executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_FRM_IMPOTAR);
        origens = UtilSBPersistencia.getListaTodos(OrigemProspecto.class, getEMPagina());
    }

    @Override
    public void executarAcaoSelecionada() {
        super.executarAcaoSelecionada(); //To change body of generated methods, choose Tools | Templates.
        caminhoArquhivoImportacao = SBCore.getControleDeSessao().getSessaoAtual().getPastaTempDeSessao() + "/importacaoExel.xls";

        if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_FRM_IMPOTAR)) {
            nomeArquivoEnviado = "";
            caminhoArquhivoImportacao = SBCore.getControleDeSessao().getSessaoAtual().getPastaTempDeSessao() + "/importacaoExel.xls";
            tamanhoArquivoEnviado = "";
            prospectoImportado = false;
        }

        if (isAcaoSelecionadaIgualA(FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_CTR_IMPORTAR)) {
            if (!new File(caminhoArquhivoImportacao).exists()) {
                SBCore.enviarMensagemUsuario("Envie um arquivo para importacao", FabMensagens.ALERTA);
                return;

            } else {

                ItfResposta resp = ModuloCRMAdmin.carregarExcel(caminhoArquhivoImportacao, origem);
                if (resp.isSucesso()) {
                    prospectoImportado = true;
                    prospectosImportados = (List<PessoaJuridica>) resp.getRetorno();
                    int i = 0;
                    for (PessoaJuridica prosp : prospectosImportados) {
                        prospectosImportados.set(i, UtilSBPersistencia.loadEntidade(prosp, getEMPagina()));
                        i++;
                    }
                    executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.IMPORTADOR_PROSPECTO_FRM_RELATORIO);
                }

            }
        }
    }

    public String getCaminhoArquhivoImportacao() {
        return caminhoArquhivoImportacao;
    }

    public String getNomeArquivoEnviado() {
        return nomeArquivoEnviado;
    }

    public String getTamanhoArquivoEnviado() {
        return tamanhoArquivoEnviado;
    }

    public void enviarArquivo(FileUploadEvent eventoEnvio) {

        try {
            if (UtilCRCOutputs.salvarArquivoInput(eventoEnvio.getFile().getInputStream(), caminhoArquhivoImportacao)) {
                nomeArquivoEnviado = eventoEnvio.getFile().getFileName();
                tamanhoArquivoEnviado = String.valueOf(eventoEnvio.getFile().getSize());
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando arquivo por categoria", t);
            SBCore.enviarMensagemUsuario("Erro não esperado enviando:" + eventoEnvio.getFile().getFileName(), FabMensagens.ERRO);
        }
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return null;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

    public List<PessoaJuridica> getProspectosImportado() {
        return prospectosImportados;
    }

    public OrigemProspecto getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemProspecto origem) {
        this.origem = origem;
    }

    public List<OrigemProspecto> getOrigens() {
        return origens;
    }

}
