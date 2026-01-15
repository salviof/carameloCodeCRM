package br.org.carameloCode.erp.crm.paginas.crmConvidado;

import br.org.carameloCode.erp.crm.paginas.crmAtendimento.ServicosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.DocsEquipeDaCategoria;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.FabAcaoCRMConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.convidado.InfoAcaoCRMConvidado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCBytes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.TIPO_PARTE_URL;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.servletes.urls.parametrosURL.ParametroURL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;

@Named
@ViewScoped
@InfoPagina(nomeCurto = "PST_CV", tags = {"Pastas de equipe do convidado"})
@InfoAcaoCRMConvidado(acao = FabAcaoCRMConvidado.PASTAS_DO_CLIENTE_MB_GESTAO)
public class PgPastasdeEquipeDoConvidado extends MB_paginaCadastroEntidades<CategoriaArquivoEquipe> {

    @InfoParametroURL(nome = "Categoria", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = CategoriaArquivoEquipe.class, representaEntidadePrincipalMB = true, obrigatorio = false)
    private ParametroURL prCategoriaSelecionada;

    @InfoParametroURL(nome = "Pessoa", tipoParametro = TIPO_PARTE_URL.ENTIDADE, tipoEntidade = Pessoa.class)
    private ParametroURL prPessoaSelecionado;

    private Pessoa pessoa;

    @PostConstruct
    public void inicio() {

        pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoaSelecionado).getValor(), getEMPagina());
    }

    public Pessoa getPessoa() {
        pessoa = UtilSBPersistencia.loadEntidade((ComoEntidadeSimplesSomenteLeitura) getParametroInstanciado(prPessoaSelecionado).getValor(), getEMPagina());
        return pessoa;
    }

    @Override
    protected void listarDados(boolean mostrarInativos) {
        List<CategoriaArquivoEquipe> categorias = UtilSBPersistencia.getListaTodos(CategoriaArquivoEquipe.class, getEMPagina());
        List<CategoriaArquivoEquipe> nova = new ArrayList<>();
        categorias.stream().filter(ct -> ct.isCompartilharComConvidados()).forEach(nova::add);
        setEntidadesListadas(nova);
    }

    public List<DocsEquipeDaCategoria> getPastasDaEquipe() {
        List<DocsEquipeDaCategoria> nova = new ArrayList<>();
        List<DocsEquipeDaCategoria> todos = (List<DocsEquipeDaCategoria>) getPessoa().getCampoInstanciadoByNomeOuAnotacao("docEquipeCategorias").getValor();

        todos.stream().filter(cat -> cat.getCategoria().isCompartilharComConvidados()).forEach(nova::add);

        return nova;
    }

    public DocsEquipeDaCategoria getCatSelecionada() {
        if (getEntidadeSelecionada() == null) {
            return null;
        }
        List<DocsEquipeDaCategoria> todos = (List<DocsEquipeDaCategoria>) getPessoa()
                .getCampoInstanciadoByNomeOuAnotacao("docEquipeCategorias").getValor();
        Optional<DocsEquipeDaCategoria> categorias = todos.stream().filter(de -> de.getCategoria().equals(getEntidadeSelecionada()))
                .findFirst();

        if (!categorias.isPresent()) {
            return null;
        }
        return categorias.get();
    }

    public void enviarArquivosEquipePessoa(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            ArquivoAnexado arquivo = new ArquivoAnexado();

            try {

                Pessoa prospecto = (Pessoa) event.getComponent().getAttributes().get("pessoa");

                CategoriaArquivoEquipe categoria = (CategoriaArquivoEquipe) event.getComponent().getAttributes().get("categoriaEquipe");

                arquivo.setProspecto(prospecto);
                if (prospecto == null) {
                    SBCore.enviarMensagemUsuario("Pessoa não encontrada", FabMensagens.ALERTA);
                    return;
                }
                if (categoria == null) {
                    SBCore.enviarMensagemUsuario("Categoria não encontrada", FabMensagens.ALERTA);
                    return;
                }
                arquivo.setCategoriaArqEquipe(categoria);
                arquivo.getCPinst("arquivo").getComoArquivoDeEntidade().uploadArquivo(file.getFileName(), UtilCRCBytes.gerarBytePorInputstream(file.getInputStream()));

                ItfRespostaAcaoDoSistema resposta = ModuloCRMAtendimento.arquivoEquipeSalvar(arquivo);
                if (!resposta.isSucesso()) {
                    resposta.dispararMensagens();

                }

            } catch (IOException ex) {
                Logger.getLogger(ServicosCRM.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
