/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAdmin;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.FabAcaoCrmAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.InfoAcaoCRMAdmin;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAdmin.ModuloCRMAdmin;
import br.org.coletivoJava.integracoes.restMautic.implementacao.GestaoTokenRestMautic;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.StatusIntegracao;

import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabConfigModuloMautic;
import com.super_bits.Super_Bits.mktMauticIntegracao.regras_de_negocio_e_controller.FabMauticContatoRest;
import com.super_bits.marketing.Util.navegador.FabMetodoLocalicacaoInformacaoEmCodigo;
import com.super_bits.marketing.Util.navegador.RoboInterpredadorProspecto;

import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoComGestaoERegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModuloBean;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.MapaTokensGerenciados;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
@InfoPagina(nomeCurto = "MTC", tags = {"Mautic"})
@InfoAcaoCRMAdmin(acao = FabAcaoCrmAdmin.MAUTIC_MB_INTEGRACAO)
@Named
@ViewScoped
public class PgMautic extends MB_PaginaConversation {

    private ConfigModuloBean configuModuloMautic;

    private String urlCadastroIndustrial;
    private int inicio;
    private int fim;
    private OrigemProspecto origemSelecionada;
    private List<OrigemProspecto> origensDisponiveis;
    private StatusIntegracao status = new StatusIntegracao(FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO);

    public StatusIntegracao getStatus() {
        return status;
    }

    @Override
    public void executarAcaoSelecionada() {
        FabAcaoCrmAdmin acao = (FabAcaoCrmAdmin) getEnumAcaoAtual();
        switch (acao) {
            case MAUTIC_CTR_SALVAR_CONFIGURACOES:
                ModuloCRMAdmin.malticSalvarConfiguracoes(configuModuloMautic);
                break;
            case MAUTIC_CTR_EXPORTAR_DADOS:

                ItfTokenGestaoOauth conexao = MapaTokensGerenciados.getAutenticadorSistema(FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO).getComoGestaoOauth();
                ItfTokenGestao gestao = FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO.getGestaoToken();
                System.out.println(gestao.getToken());
                System.out.println(gestao.isTemTokemAtivo());
                switch (conexao.getStatusToken()) {
                    case SEM_TOKEN:
                        getPaginaUtil().irParaURL(conexao.getUrlObterCodigoSolicitacao());

                    case EXPIRADO:
                        //  if (!conexao.getStatusToken().equals(FabStatusToken.EXPIRADO)) {
                        getPaginaUtil().irParaURL(conexao.getUrlObterCodigoSolicitacao());
                        //  }
                        break;
                    case ATIVO:
                        ModuloCRMAdmin.malticExportarProspectos().dispararMensagens();
                        break;
                    case SOLICITACAO_TOKEN_EM_ANDAMENTO:
                        conexao.gerarNovoToken();
                        break;
                    default:
                        throw new AssertionError(conexao.getStatusToken().name());

                }

                break;

            default:
                super.executarAcaoSelecionada();
        }

    }

    public void conectarMautic() {
        GestaoTokenRestMautic conexao = (GestaoTokenRestMautic) MapaTokensGerenciados.getAutenticadorSistema(FabMauticContatoRest.CONTATO_LISTAR_COM_FILTRO).getComoGestaoOauth();
        if (conexao.isTemTokemAtivo()) {
            conexao.validarToken();
        }
        if (!conexao.isPossuiTokenValido()) {
            if (!conexao.isCodigoSolicitacaoRegistrado()) {
                getPaginaUtil().irParaURL(conexao.getUrlObterCodigoSolicitacao());
            } else {
                conexao.gerarNovoToken();
                if (conexao.isTemTokemAtivo()) {
                    MapaTokensGerenciados.registrarAutenticadorRestfullTipoApp(conexao, null);
                }
            }
        }

    }

    @PostConstruct
    public void inicio() {
        configuModuloMautic = new ConfigModuloBean(SBCore.getConfigModulo(FabConfigModuloMautic.class));
        executaAcaoSelecionadaPorEnum(FabAcaoCrmAdmin.MAUTIC_FRM_SELECIONAR_OPCOES);
        origensDisponiveis = UtilSBPersistencia.getListaTodos(OrigemProspecto.class, getEMPagina());
    }

    @Override
    public ComoEntidadeSimples getBeanSelecionado() {
        return configuModuloMautic;
    }

    @Override
    public void setBeanSelecionado(ComoEntidadeSimples pBeanSimples) {

    }

    public ConfigModuloBean getConfiguModuloMautic() {
        return configuModuloMautic;
    }

    public void importarCadastroIndustrial() {

        List<String> urlsListagens = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            urlsListagens.add("http://www.cadastroindustrialmg.com.br/industria/resultadobusca?K=a&Page=" + i + "&Filters=Cidade:Belo%20Horizonte");
        }

        boolean valendo = true;
        if (valendo) {
            RoboInterpredadorProspecto roboProsp = new RoboInterpredadorProspecto("/industria/index/", urlsListagens);
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.TEXTO.getLocalizador("nome", ".nome"));
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.TEXTO.getLocalizador("telefonePrincipal", "div.contato").setApenasNumeros());//Telefone principal
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.PRIMEIRO_TEXTO.getLocalizador("observacao", ".info p"));//Atividade Principal codigo
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.SEGUNDO_TEXTO.getLocalizador("observacao", ".info p"));//Atividade Principal codigo
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.ULTIMO_TEXTO.getLocalizador("observacao", ".info p"));//Numero de funcionarios
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.TEXTO.getLocalizador("endereco", ".mapa div"));//Endereço
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.PRIMEIRO_TEXTO.getLocalizador("site", ".links a "));
            roboProsp.adicionarInformacaoLocalizada(FabMetodoLocalicacaoInformacaoEmCodigo.ULTIMO_TEXTO.getLocalizador("email", ".links a "));

            List<PessoaJuridica> prosps = roboProsp.getTodosProspectos();
            System.out.println("Encontrados " + prosps.size() + " Prospectos");
            TipoRelacionamento relacionamento = origemSelecionada.getRelacionamentoPadrao();
            prosps.forEach((prosp) -> {
                try {
                    new ExecucaoComGestaoERegraDeNegocioPadrao() {
                        @Override
                        public void regraDeNegocio() {
                            try {
                                OrigemProspecto origem = UtilSBPersistencia.loadEntidade(origemSelecionada, getEMPagina());
                                prosp.setOrigem(origem);
                                TipoRelacionamento tipoRelacionamento = UtilSBPersistencia.loadEntidade(relacionamento, getEm());
                                prosp.prepararNovoObjeto(tipoRelacionamento);
                                atualizarEntidade(prosp);
                            } catch (ErroEmBancoDeDados t) {
                                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMensagemUsuario(), t);
                            }
                        }
                    };
                    System.out.println(prosp.getNome() + " Registrado com sucesso!");
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMessage(), t);
                }

            }
            );
        }
    }

    public String getUrlCadastroIndustrial() {
        return urlCadastroIndustrial;
    }

    public void setUrlCadastroIndustrial(String urlCadastroIndustrial) {
        this.urlCadastroIndustrial = urlCadastroIndustrial;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public OrigemProspecto getOrigemSelecionada() {
        return origemSelecionada;
    }

    public void setOrigemSelecionada(OrigemProspecto origemSelecionada) {
        this.origemSelecionada = origemSelecionada;
    }

    public List<OrigemProspecto> getOrigensDisponiveis() {
        return origensDisponiveis;
    }

}
