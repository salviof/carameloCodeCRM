package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import br.org.coletivoJava.fw.api.erp.chat.model.ComoEntidadeVinculadoChat;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.FabStatusAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.SatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.ServicoOferecido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServico;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.ListaProspectos;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.ListasProspectos;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estilo.EstiloVisualizacaoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioConvidado.UsuarioConvidado;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeContato;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.LocalizacaoPostavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocalPostagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.hibernate.annotations.Where;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.model.ComoLead;

/**
 *
 *
 *
 * @author sfurbino
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPessoa")
@Table(name = "ProspectoEmpresa")
@Entity(name = "Pessoa")
@EntityListeners(ListenerEntidadePessoa.class)
@InfoObjetoSB(tags = "Representante Legal", plural = "Pessoas Fisico e Juridico")
public class Pessoa extends EntidadeContato implements ComoEntidadeVinculadoChat, ComoLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Nome Fantasia", obrigatorio = true)
    @InfoCampoValorLogico(nomeCalculo = "Nome ", somenteLeitura = false)
    private String nome;

    @OneToMany(mappedBy = "prospectoEmpresa", cascade = CascadeType.MERGE)
    @Where(clause = "valor is not null and valor != '' ")
    private List<DadoCRM> dadosColetados;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoPessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "documento")
    private String documento;

    @OneToMany(mappedBy = "prospectoEmpresa", cascade = CascadeType.MERGE)
    private List<DadoCRM> todosdadosColetados;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL, label = "Tel. fixo Principal")
    @Column(unique = true)
    private String telefonePrincipal;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Telefone Alternativo")
    private String outrosTelefones;

    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL, label = "Email Principal")
    @Column(unique = true)
    @InfoCampoValorLogico(nomeCalculo = "Email principal", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    private String email;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Atendente tem Acesso", atualizarSempreQueSalvar = false)
    private boolean atendenteLogadoTemAcesso;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Atendente é o responsável", atualizarSempreQueSalvar = false)
    private boolean atendenteLogadoResponsavelPrincipal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Privado?")
    @InfoCampoVerdadeiroOuFalso()
    private boolean umPerfilPrivado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Perfil publico? ", descricao = "Um perfil publico permite que qualquer empresa")
    @InfoCampoValorLogico(nomeCalculo = "umaEmpresaPublica")
    private boolean umPerfilPublico;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Associação compartilhada?")
    private boolean umPerfilCompartilhado;

    @ManyToOne(targetEntity = SatisfacaoCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, fabricaDeOpcoes = FabSatisfacaoCliente.class)
    private SatisfacaoCliente satisfacao = FabSatisfacaoCliente.SATISFEITO.getRegistro();

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Relacionamento")
    @InfoCampoValidadorLogico(descricao = "Validações de alteração de relacionamento")
    @ManyToOne(targetEntity = TipoRelacionamento.class)
    private TipoRelacionamento relacionamento;

    @OneToMany(mappedBy = "prospecto")
    private List<EmailCrm> emailsEnviadoRecebido;

    @Transient
    private List<TipoServico> listaApresentacoesVinculadas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean umaPessoaFisica;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean umaPessoaJuridica;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Endereço")
    @InfoCampoValorLogico(nomeCalculo = "textoEndereco")
    private String endereco;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Cep")
    @Deprecated
    @InfoCampoValorLogico(nomeCalculo = "textoCep")
    private String CEP;

    @InfoCampo(label = "Origem Prospecto", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = OrigemProspecto.class, optional = true)
    private OrigemProspecto origem;

    @InfoCampo(label = "Contato principal", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "contatosProspecto")
    @InfoCampoValorLogico(nomeCalculo = "contatoPrincipal", somenteLeitura = false)
    @ManyToOne(targetEntity = ContatoProspecto.class, cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private ContatoProspecto contatoPrincipal;

    @OneToMany(mappedBy = "prospecto", targetEntity = ContatoProspecto.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, camposExibidosEmLista = {"nome", "email"})
    private List<ContatoProspecto> contatosProspecto = new ArrayList<>();

    @OneToMany(targetEntity = ArquivoAnexado.class, mappedBy = "prospecto",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, label = "Arquivos da Pessoa")
    @Where(clause = "tipoAnexo!='ArquivoAnexadoEmail' && tipoAnexo != 'ArquivoAnexadoEmailEmConteudo' ")
    @OrderBy(value = "id DESC")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = false, nomeCalculo = "arquivos da Empresa")
    private List<ArquivoAnexado> arquivos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Arquivos da Pessoa")
    @InfoCampoValorLogico(atualizarSempreQueSalvar = false, nomeCalculo = "Ultimos arquivos anexados")
    @Transient
    private List<ArquivoAnexado> ultimosArquivos = new ArrayList<>();

    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_LOCALIZACAO, obrigatorio = false)
    @InfoCampoValidadorLogico()
    @InfoCampoValorLogico(nomeCalculo = "localizacao", atualizarSempreQueSalvar = false)
    @ManyToOne(optional = true, targetEntity = LocalizacaoPostavel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LocalizacaoPostavel localizacao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, camposExibidosEmLista = {"tipoServico", "valorSetup", "valorMensal"},
            label = "Serviços do Ultimo Orcamento")
    @InfoCampoValorLogico(nomeCalculo = "ServicosUltimoOrçamento")
    @Transient
    private List<ServicoOferecido> servicos;

    @ManyToOne(targetEntity = Orcamento.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @InfoCampoValorLogico(nomeCalculo = "ultimoOrçamento", somenteLeitura = true)
    private Orcamento ultimoOrcamento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @OrderBy(value = "dataHoraCriacao desc")
    private List<Orcamento> orcamentos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    @ManyToOne(targetEntity = MetaRelacionamento.class)
    @InfoCampoValorLogico(nomeCalculo = "meta", atualizarSempreQueSalvar = true)
    private MetaRelacionamento meta;

    private String canalRocketChat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prospectos_usuarios_responsaveis",
            joinColumns = @JoinColumn(name = "prospecto_id"),
            inverseJoinColumns = @JoinColumn(name = "usuarioCRM_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "usuariosRespDisponiveis")
    @InfoCampoValidadorLogico
    private List<UsuarioCRM> usuariosResponsaveis = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prospeco_usuarioConvidado",
            joinColumns = @JoinColumn(name = "prospecto_id"),
            inverseJoinColumns = @JoinColumn(name = "usuarioCRM_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "usuariosRespDisponiveis")
    private List<UsuarioConvidado> usuariosConvidados;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "usuariosResponsaveisDisponíveis")
    private List<UsuarioCRM> usuariosRespDisponiveis;

    @ManyToOne(targetEntity = TipoRelacionamento.class)
    @InfoCampoValorLogico(nomeCalculo = "Relacionamento Anterior", atualizarSempreQueSalvar = true)
    private TipoRelacionamento relacionamentoAnterior;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "usuariosResponsaveis")
    @InfoCampoValorLogico(nomeCalculo = "Resp. Comercial")
    private UsuarioCRM usuarioResponsavel;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "usuariosResponsaveis")
    @InfoCampoValorLogico(nomeCalculo = "Gestor sucesso")
    private UsuarioCRM usuarioAtendimento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Realizado em:")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoracriouProspecto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO, label = "Ultima Alteracao")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraAlterouProspecto = new Date();

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraUltimaInteracao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuarioCriou;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuarioEditou;

    @InfoCampoValorLogico(nomeCalculo = "qtdContatos")
    private int quantidadeContatos;

    @InfoCampoValorLogico(nomeCalculo = "qtdAnexos")
    private int quantidadeAnexos;

    @InfoCampoValorLogico(nomeCalculo = "qtdAtividades")
    private int quantidadeAtividades;

    @InfoCampoValorLogico(nomeCalculo = "qtdAtividadesLeads")
    @Transient
    private int quantidadeAtividadesLead;

    @InfoCampoValorLogico(nomeCalculo = "qtdEmailsTrocados")
    private int quantidadeEmailsTrocados;

    @InfoCampoValorLogico(nomeCalculo = "qtdEmailsNaoLidos")
    private int quantidadeEmailsNaoLidos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @OneToMany(mappedBy = "prospectoEmpresa", targetEntity = AtividadeCRM.class)
    private List<AtividadeCRM> atividadesExecutadas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Modo pós vendas")
    private boolean modoPosVendas;

    @InfoCampoValorLogico(nomeCalculo = "demandaUrgebcia")
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Demanda urgênte?")
    private boolean possuiDemandaUrgencia;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraMudancaRelacionamento;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = AtividadeCRM.class, mappedBy = "prospectoEmpresa", cascade = {CascadeType.REMOVE, CascadeType.REFRESH})
    @OrderBy("dataHoraInicioAtividade DESC")
    private List<AtividadeCRM> atividadesRealizadas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @OneToMany(mappedBy = "prospecto", targetEntity = HistoricoRelacionamento.class, cascade = CascadeType.REMOVE)
    @OrderBy("dataHora DESC")
    private List<HistoricoRelacionamento> historicoRelacionamento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    @InfoCampoValorLogico(nomeCalculo = "ativo")
    private boolean ativo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String linkNotionIntranet;

    @InfoCampo(tipo = FabTipoAtributoObjeto.RESPONSAVEL, label = "Responsável Empresa", somenteLeitura = false)
    @InfoCampoValorLogico(nomeCalculo = "Responsável", somenteLeitura = false)
    @InfoCampoValidadorLogico()
    private String responsavel;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, label = "Observação")
    @Column(length = 8000)
    private String observacao;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Estilo visualização")
    private EstiloVisualizacaoProspecto estiloVisualizacao;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValorLogico(nomeCalculo = "usuariosLogado solicitou")
    private boolean usuarioLogadoSolicitouAcesso;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tagAtendimento_prospecto",
            joinColumns = @JoinColumn(name = "prospecto_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, entidadeOpcoesDisponiveis = TagAtendimento.class)
    private List<TagAtendimento> tagsAtendimento;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "UltimaAtividade")
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean possuiAtividadesEmAndamento;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "prospectoEmpresa_id"),
            inverseJoinColumns = @JoinColumn(name = "clienteRelacionado_id"))
    private List<Pessoa> clientesRelacionados;

    @ManyToMany(mappedBy = "clientesRelacionados", cascade = CascadeType.ALL)
    private List<Pessoa> clientesQueMeRelacionam;

    @OneToMany(mappedBy = "prospecto")
    @Where(clause = "tipoEmail in ('EnvioEmailAtividade','EnvioEmail')")
    private List<EnvioEmail> emailsEnviados;

    @OneToMany(mappedBy = "prospecto")
    @Where(clause = " tipoEmail = 'EmailRecebido'")
    private List<EmailCrm> emailsRecebidos;

    @OneToMany(mappedBy = "prospecto")
    @Where(clause = " foiEnviado <> 1 ")
    private List<EnvioEmail> emailsNaoEnviados;

    @OneToMany(mappedBy = "prospecto")
    @Where(clause = " foiEnviado <> 1 ")
    private List<EnvioEmail> emailsNaoLido;

    @OneToMany(mappedBy = "prospectoEmpresa", targetEntity = AtividadeCRM.class)
    @Where(clause = "statusAtividade_id =  " + FabStatusAtividade.idEmAndamento + " and tipoEntidade='AtividadeCRM'")
    private List<AtividadeCRM> atividadesEmAndamento;

    @ListaProspectos(lista = ListasProspectos.DADOS_NAO_COLETADOS)
    @Transient
    private List<DadoCRM> listaDadosCRM;

    @Transient
    private List<TipoRelacionamento> relacionamentosComColetaDado;

    @ListaProspectos(lista = ListasProspectos.DADOS_COLETADOS_POR_TIPO_RELACIONAMENTO)
    @Transient
    private List<DadoCRM> listaDadosCRMPorTipoRelacionamento;

    @ListaProspectos(lista = ListasProspectos.DADOS_COLETADOS_POR_TIPO_RELACIONAMENTO)
    @Transient
    private List<DadoCRM> listaDadosColetadosNaoVinculados;

    @InfoCampoValorLogico(nomeCalculo = "pertence ao usuário logado")
    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean pertenceAoUsuarioLogado;

    @InfoCampoValorLogico(nomeCalculo = "Campos dinamicos Agrupados")
    @Transient
    private List<GrupoCampoInstanciado> camposDinamicosAgrupados;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "UltimaAtividade")
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "tividadesEmAndamento")
    private AtividadeCRM ultimaAtividadeEmAndamento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @InfoCampoValorLogico(nomeCalculo = "Docs Cliente categoria")
    @Transient
    private List<DocsEquipeDaCategoria> docEquipeCategorias;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @InfoCampoValorLogico(nomeCalculo = "Docs equipe Categoria")
    @Transient
    private List<DocsClienteDaCategoria> docClienteCategorias;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @InfoCampoValorLogico(nomeCalculo = "Integracoes")
    @Transient
    private List<IntegracaoLink> integracoesLink;

    @OneToMany(targetEntity = ChamadoCliente.class, mappedBy = "pessoa")
    @Where(clause = "status_id = " + FabStatusChamado.ID_AGUARDANDO_ATENDIMENTO + " OR status_id = " + FabStatusChamado.ID_EM_ATENDIMENTO)
    private List<ChamadoCliente> chamadosAbertos;

    @OneToMany(targetEntity = Solicitacao.class, mappedBy = "pessoa")
    @Where(clause = " foiFinalizada = 0")
    private List<Solicitacao> solicitacoesAbertas;

    public Pessoa() {
        super();

    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = TipoRelacionamento.class)
    public void prepararNovoObjeto(Object... parametros) {

        if (parametros != null && parametros.length > 0) {
            try {
                super.prepararNovoObjeto(parametros); //To change body of generated methods, choose Tools | Templates.
                setRelacionamento(getParametroInicialEnviado(TipoRelacionamento.class, parametros));
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro preparando prospecto Empresa", t);
            }
        }
    }

    public Date getDataHoracriouProspecto() {
        return dataHoracriouProspecto;
    }

    public void setDataHoracriouProspecto(Date dataHoracriouProspecto) {
        this.dataHoracriouProspecto = dataHoracriouProspecto;
    }

    public Date getDataHoraAlterouProspecto() {
        return dataHoraAlterouProspecto;
    }

    public List<DadoCRM> getListaDadosNaoColetadosCRM() {
        listaDadosCRM = ListasProspectos.DADOS_NAO_COLETADOS.getLista(this, getTipoRelacionamentosPossiveisRetroagir());
        return listaDadosCRM;
    }

    public void setDataHoraAlterouProspecto(Date dataHoraAlterouProspecto) {
        this.dataHoraAlterouProspecto = dataHoraAlterouProspecto;
    }

    public void setDadosColetados(List<DadoCRM> pDadosColetados) {

        for (DadoCRM dado : pDadosColetados) {
            if (dado.getTipoDadoCRM().getCampoProspectoCorrespondente() != null && !dado.getTipoDadoCRM().getCampoProspectoCorrespondente().isEmpty()) {
                try {
                    if (getCampoInstanciadoByNomeOuAnotacao(dado.getTipoDadoCRM().getCampoProspectoCorrespondente()).getFabricaTipoAtributo().equals(dado.getCampoInstanciado().getFabricaTipoAtributo())) {
                        getCampoInstanciadoByNomeOuAnotacao(dado.getTipoDadoCRM().getCampoProspectoCorrespondente()).setValor(dado.getCampoInstanciado().getValor());
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro vinculando dado ao campo do prospecto", t);
                }
            }
        }

        this.dadosColetados = pDadosColetados;
    }

    public List<ArquivoAnexado> getArquivos() {
        if (arquivos != null) {

            for (TipoServico tipo : getListaApresentacoesVinculadas()) {
                for (ArquivoAnexado arq : tipo.getApresentacoes()) {
                    if (!arquivos.contains(arq)) {
                        arquivos.add(arq);
                    }
                }
            }

        }
        return arquivos;
    }

    public List<ContatoProspecto> getContatosComEmail() {
        List<ContatoProspecto> lista = new ArrayList<>();
        try {
            getContatosProspecto().stream()
                    .filter(cont -> UtilCRCStringValidador.isNAO_NuloNemBranco(cont.getEmail()))
                    .forEach(lista::add);
        } catch (Throwable t) {

        }
        return lista;
    }

    public List<TipoServico> getListaApresentacoesVinculadas() {

        if (UtilCRCListas.isNuloOuVazio(listaApresentacoesVinculadas) && !UtilCRCListas.isNuloOuVazio(getServicos())) {
            listaApresentacoesVinculadas = new ArrayList<>();
            for (ServicoOferecido servico : getServicos()) {
                if (servico.getTipoServico() != null) {
                    if (!servico.getTipoServico().getApresentacoes().isEmpty()) {
                        listaApresentacoesVinculadas.add(servico.getTipoServico());
                    }
                }
            }
        }
        return listaApresentacoesVinculadas;

    }

    public boolean adicionarContato(ContatoProspecto c) {
        if (c == null) {
            return false;
        }
        if (getContatosProspecto().contains(c)) {
            SBCore.enviarAvisoAoUsuario("O contato " + c.getEmail() + " Já foi cadastrado");
            return false;
        }
        if (c.getEmail() == null || c.getEmail().isEmpty()) {
            SBCore.enviarAvisoAoUsuario("É obrigatório informar um e-mail");
            return false;
        }
        c.setProspecto(this);
        getContatosProspecto().add(c);
        return true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public List<ServicoOferecido> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoOferecido> servicos) {
        this.servicos = servicos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public List<DadoCRM> getTodosdadosColetados() {
        return todosdadosColetados;
    }

    public void setTodosdadosColetados(List<DadoCRM> todosdadosColetados) {
        this.todosdadosColetados = todosdadosColetados;
    }

    @Override
    public String getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(String telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getOutrosTelefones() {
        return outrosTelefones;
    }

    public void setOutrosTelefones(String outrosTelefones) {
        this.outrosTelefones = outrosTelefones;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUmPerfilPrivado() {
        return umPerfilPrivado;
    }

    public void setUmPerfilPrivado(boolean umPerfilPrivado) {
        this.umPerfilPrivado = umPerfilPrivado;
    }

    public boolean isUmPerfilPublico() {
        return umPerfilPublico;
    }

    public void setUmPerfilPublico(boolean umPerfilPublico) {
        this.umPerfilPublico = umPerfilPublico;
    }

    public boolean isUmPerfilCompartilhado() {
        return umPerfilCompartilhado;
    }

    public void setUmPerfilCompartilhado(boolean umPerfilCompartilhado) {
        this.umPerfilCompartilhado = umPerfilCompartilhado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Deprecated
    public String getCEP() {
        return CEP;
    }

    @Deprecated
    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    @Override
    public LocalizacaoPostavel getLocalizacao() {

        return localizacao;
    }

    @Override
    public void setLocalizacao(ComoLocalPostagem localizacao) {
        this.localizacao = (LocalizacaoPostavel) localizacao;
    }

    public void setLocalizacao(LocalizacaoPostavel localizacao) {
        this.localizacao = localizacao;
    }

    public OrigemProspecto getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemProspecto origem) {
        this.origem = origem;
    }

    public List<ContatoProspecto> getContatosProspecto() {
        return contatosProspecto;

    }

    public void setContatosProspecto(List<ContatoProspecto> contatosProspecto) {
        this.contatosProspecto = contatosProspecto;
    }

    public void setArquivos(List<ArquivoAnexado> arquivos) {
        this.arquivos = arquivos;
    }

    public PessoaFisica getComoPessoaFisica() {
        try {
            return (PessoaFisica) this;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro lendo pessoa como pessoa Física", t);
            return null;
        }
    }

    public PessoaJuridica getComoPessoaJuridica() {
        try {
            return (PessoaJuridica) this;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro lendo pessoa como pessoa Juridica", t);
            return null;
        }
    }

    @Override
    public void setLocalizacao(ComoLocal pLocal) {
        localizacao = (LocalizacaoPostavel) pLocal;
    }

    public boolean isUmaPessoaFisica() {
        if (this instanceof PessoaFisica) {
            umaPessoaFisica = true;
        }
        return umaPessoaFisica;
    }

    public boolean isUmaPessoaJuridica() {
        if (this instanceof PessoaJuridica) {
            umaPessoaJuridica = true;
        }
        return umaPessoaJuridica;
    }

    public String getCanalRocketChat() {
        return canalRocketChat;
    }

    public void setCanalRocketChat(String canalRocketChat) {
        this.canalRocketChat = canalRocketChat;
    }

    public List<DadoCRM> getDadosColetados() {
        return dadosColetados;
    }

    public List<UsuarioCRM> getUsuariosResponsaveis() {
        return usuariosResponsaveis;
    }

    public void setUsuariosResponsaveis(List<UsuarioCRM> usuariosResponsaveis) {
        this.usuariosResponsaveis = usuariosResponsaveis;
    }

    public TipoRelacionamento getRelacionamentoAnterior() {
        return relacionamentoAnterior;
    }

    public void setRelacionamentoAnterior(TipoRelacionamento relacionamentoAnterior) {
        this.relacionamentoAnterior = relacionamentoAnterior;
    }

    public Orcamento getUltimoOrcamento() {
        return ultimoOrcamento;
    }

    public void setUltimoOrcamento(Orcamento ultimoOrcamento) {
        this.ultimoOrcamento = ultimoOrcamento;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public UsuarioCRM getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(UsuarioCRM usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public UsuarioCRM getUsuarioCriou() {
        return usuarioCriou;
    }

    public void setUsuarioCriou(UsuarioCRM usuarioCriou) {
        this.usuarioCriou = usuarioCriou;
    }

    @Override
    public UsuarioCRM getUsuarioEditou() {
        return usuarioEditou;
    }

    public void setUsuarioEditou(UsuarioCRM usuarioEditou) {
        this.usuarioEditou = usuarioEditou;
    }

    public int getQuantidadeContatos() {
        return quantidadeContatos;
    }

    public int getQuantidadeAnexos() {
        return quantidadeAnexos;
    }

    public void setQuantidadeAnexos(int quantidadeAnexos) {
        this.quantidadeAnexos = quantidadeAnexos;
    }

    public int getQuantidadeAtividades() {
        return quantidadeAtividades;
    }

    public int getQuantidadeEmailsTrocados() {
        return quantidadeEmailsTrocados;
    }

    public int getQuantidadeEmailsNaoLidos() {
        return quantidadeEmailsNaoLidos;
    }

    public List<AtividadeCRM> getAtividadesExecutadas() {
        return atividadesExecutadas;
    }

    public boolean isModoPosVendas() {
        return modoPosVendas;
    }

    public void setModoPosVendas(boolean modoPosVendas) {
        this.modoPosVendas = modoPosVendas;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataHoraUltimaInteracao() {
        return dataHoraUltimaInteracao;
    }

    public void setDataHoraUltimaInteracao(Date dataHoraUltimaInteracao) {
        this.dataHoraUltimaInteracao = dataHoraUltimaInteracao;
    }

    @Override
    public MetaRelacionamento getMeta() {
        if (meta == null) {
            meta = getRelacionamento().getMetaRelacionamento();
        }
        return meta;
    }

    public void setMeta(MetaRelacionamento meta) {
        this.meta = meta;
    }

    public boolean isPossuiDemandaUrgencia() {
        return possuiDemandaUrgencia;
    }

    public void setPossuiDemandaUrgencia(boolean possuiDemandaUrgencia) {
        this.possuiDemandaUrgencia = possuiDemandaUrgencia;
    }

    public SatisfacaoCliente getSatisfacao() {
        return satisfacao;
    }

    public void setSatisfacao(SatisfacaoCliente satisfacao) {
        this.satisfacao = satisfacao;
    }

    @Override
    public String getTelefone() {
        return telefonePrincipal;
    }

    public boolean isUsuarioLogadoTemAcesso() {
        return atendenteLogadoTemAcesso;
    }

    public void setUsuarioLogadoTemAcesso(boolean usuarioLogadoTemAcesso) {
        this.atendenteLogadoTemAcesso = usuarioLogadoTemAcesso;
    }

    public boolean isAtendenteLogadoTemAcesso() {
        return atendenteLogadoTemAcesso;
    }

    public void setAtendenteLogadoTemAcesso(boolean atendenteLogadoTemAcesso) {
        this.atendenteLogadoTemAcesso = atendenteLogadoTemAcesso;
    }

    public boolean isAtendenteLogadoResponsavelPrincipal() {
        return atendenteLogadoResponsavelPrincipal;
    }

    public void setAtendenteLogadoResponsavelPrincipal(boolean atendenteLogadoResponsavelPrincipal) {
        this.atendenteLogadoResponsavelPrincipal = atendenteLogadoResponsavelPrincipal;
    }

    public List<UsuarioCRM> getUsuariosRespDisponiveis() {
        return usuariosRespDisponiveis;
    }

    public void setUsuariosRespDisponiveis(List<UsuarioCRM> usuariosRespDisponiveis) {
        this.usuariosRespDisponiveis = usuariosRespDisponiveis;
    }

    @Override
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoRelacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(TipoRelacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }

    public Date getDataHoraMudancaRelacionamento() {
        return dataHoraMudancaRelacionamento;
    }

    public void setDataHoraMudancaRelacionamento(Date dataHoraMudancaRelacionamento) {
        this.dataHoraMudancaRelacionamento = dataHoraMudancaRelacionamento;
    }

    public List<AtividadeCRM> getAtividadesRealizadas() {
        return atividadesRealizadas;
    }

    public void setAtividadesRealizadas(List<AtividadeCRM> AtividadesRealizadas) {
        this.atividadesRealizadas = AtividadesRealizadas;
    }

    public List<HistoricoRelacionamento> getHistoricoRelacionamento() {
        return historicoRelacionamento;
    }

    public List<EmailCrm> getEmailsEnviadoRecebido() {
        return emailsEnviadoRecebido;
    }

    public UsuarioCRM getUsuarioAtendimento() {
        return usuarioAtendimento;
    }

    public void setUsuarioAtendimento(UsuarioCRM usuarioAtendimento) {
        this.usuarioAtendimento = usuarioAtendimento;
    }

    @Override
    public String getCodigoCanalChat() {
        return canalRocketChat;
    }

    public int getQuantidadeAtividadesLead() {
        return quantidadeAtividadesLead;
    }

    public void setQuantidadeAtividadesLead(int quantidadeAtividadesLead) {
        this.quantidadeAtividadesLead = quantidadeAtividadesLead;
    }

    public void setQuantidadeAtividades(int quantidadeAtividades) {
        this.quantidadeAtividades = quantidadeAtividades;
    }

    public String getLinkNotionIntranet() {
        return linkNotionIntranet;
    }

    public void setLinkNotionIntranet(String linkNotionIntranet) {
        this.linkNotionIntranet = linkNotionIntranet;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getResponsavelPrimeiroNome() {
        if (UtilCRCStringValidador.isNuloOuEmbranco(responsavel)) {
            return "";
        } else {
            return responsavel.split(" ")[0];
            //return UtilCRCStringBuscaTrecho.getStringAteEncontrarIsto(responsavel, " ");
        }

    }

    public boolean isUsuarioLogadoPermitido() {

        return SBCore.getServicoPermissao().isObjetoPermitidoUsuario(SBCore.getUsuarioLogado(), this);

    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void addObservacao(String pObservacaoAdd) {
        if (observacao == null) {
            observacao = "";
        }
        observacao = observacao + "|" + pObservacaoAdd;
    }

    public EstiloVisualizacaoProspecto getEstiloVisualizacao() {
        return estiloVisualizacao;
    }

    public void setEstiloVisualizacao(EstiloVisualizacaoProspecto estiloVisualizacao) {
        this.estiloVisualizacao = estiloVisualizacao;
    }

    public ContatoProspecto getContatoPrincipal() {
        return contatoPrincipal;
    }

    public void setContatoPrincipal(ContatoProspecto contatoPrincipal) {
        this.contatoPrincipal = contatoPrincipal;
    }

    public List<TagAtendimento> getTagsAtendimento() {
        return tagsAtendimento;
    }

    public void setTagsAtendimento(List<TagAtendimento> tagsAtendimento) {
        this.tagsAtendimento = tagsAtendimento;
    }

    /**
     *
     * Calcula em runtime quais são as açoes disponíveis de acorodo com o
     * histórioco de atividades e açoes
     *
     * @return Retorna as ações disponíveis de acordo com o histórioco de
     * atividades, e Relacionamento Atual
     */
    public List<TipoAtividadeCRM> getAtividadesDisponiveis() {
        if (getId() == null) {
            return new ArrayList();
        }
        return getRelacionamento().getTiposAtividadeDisponiveis();

    }

    public List<TipoAtividadeCRM> getAtividadesMetaDisponiveis() {
        if (getId() == null) {
            return new ArrayList();
        }
        return getRelacionamento().getMetaRelacionamento().getTiposAtividadeDisponiveis();

    }

    public boolean isPossuiAtividadesEmAndamento() {
        return possuiAtividadesEmAndamento;
    }

    public void setPossuiAtividadesEmAndamento(boolean possuiAtividadesEmAndamento) {
        this.possuiAtividadesEmAndamento = possuiAtividadesEmAndamento;
    }

    public List<Pessoa> getClientesRelacionados() {
        return clientesRelacionados;
    }

    public void setClientesRelacionados(List<Pessoa> clientesRelacionados) {
        this.clientesRelacionados = clientesRelacionados;
    }

    public List<Pessoa> getClientesQueMeRelacionam() {
        return clientesQueMeRelacionam;
    }

    public void setClientesQueMeRelacionam(List<Pessoa> clientesQueMeRelacionam) {
        this.clientesQueMeRelacionam = clientesQueMeRelacionam;
    }

    public List<EnvioEmail> getEmailsEnviados() {
        return emailsEnviados;
    }

    public void setEmailsEnviados(List<EnvioEmail> emailsEnviados) {
        this.emailsEnviados = emailsEnviados;
    }

    public List<EmailCrm> getEmailsRecebidos() {
        return emailsRecebidos;
    }

    public void setEmailsRecebidos(List<EmailCrm> emailsRecebidos) {
        this.emailsRecebidos = emailsRecebidos;
    }

    public List<EnvioEmail> getEmailsNaoEnviados() {
        return emailsNaoEnviados;
    }

    public void setEmailsNaoEnviados(List<EnvioEmail> emailsNaoEnviados) {
        this.emailsNaoEnviados = emailsNaoEnviados;
    }

    public List<EnvioEmail> getEmailsNaoLido() {
        return emailsNaoLido;
    }

    public void setEmailsNaoLido(List<EnvioEmail> emailsNaoLido) {
        this.emailsNaoLido = emailsNaoLido;
    }

    public List<AtividadeCRM> getAtividadesEmAndamento() {
        return atividadesEmAndamento;
    }

    public void setAtividadesEmAndamento(List<AtividadeCRM> atividadesEmAndamento) {
        this.atividadesEmAndamento = atividadesEmAndamento;
    }

    public List<DadoCRM> getListaDadosCRM() {
        return listaDadosCRM;
    }

    public void setListaDadosCRM(List<DadoCRM> listaDadosCRM) {
        this.listaDadosCRM = listaDadosCRM;
    }

    public List<TipoRelacionamento> getRelacionamentosComColetaDado() {
        return relacionamentosComColetaDado;
    }

    public void setRelacionamentosComColetaDado(List<TipoRelacionamento> relacionamentosComColetaDado) {
        this.relacionamentosComColetaDado = relacionamentosComColetaDado;
    }

    public List<DadoCRM> getListaDadosCRMPorTipoRelacionamento() {
        return listaDadosCRMPorTipoRelacionamento;
    }

    public void setListaDadosCRMPorTipoRelacionamento(List<DadoCRM> listaDadosCRMPorTipoRelacionamento) {
        this.listaDadosCRMPorTipoRelacionamento = listaDadosCRMPorTipoRelacionamento;
    }

    public List<DadoCRM> getListaDadosColetadosNaoVinculados() {
        return listaDadosColetadosNaoVinculados;
    }

    public void setListaDadosColetadosNaoVinculados(List<DadoCRM> listaDadosColetadosNaoVinculados) {
        this.listaDadosColetadosNaoVinculados = listaDadosColetadosNaoVinculados;
    }

    public boolean isPertenceAoUsuarioLogado() {
        return pertenceAoUsuarioLogado;
    }

    public void setPertenceAoUsuarioLogado(boolean pertenceAoUsuarioLogado) {
        this.pertenceAoUsuarioLogado = pertenceAoUsuarioLogado;
    }

    public List<GrupoCampoInstanciado> getCamposDinamicosAgrupados() {
        return camposDinamicosAgrupados;
    }

    public void setCamposDinamicosAgrupados(List<GrupoCampoInstanciado> camposDinamicosAgrupados) {
        this.camposDinamicosAgrupados = camposDinamicosAgrupados;
    }

    public AtividadeCRM getUltimaAtividadeEmAndamento() {
        return ultimaAtividadeEmAndamento;
    }

    public void setUltimaAtividadeEmAndamento(AtividadeCRM ultimaAtividadeEmAndamento) {
        this.ultimaAtividadeEmAndamento = ultimaAtividadeEmAndamento;
    }

    public List<TipoRelacionamento> getRelacionamentosComDadosColetados() {
        if (UtilCRCListas.isNuloOuVazio(relacionamentosComColetaDado)) {
            relacionamentosComColetaDado = ListasProspectos.RELACIONAMENTOS_COM_DADOS_COLETADOS.getLista(this);
        }
        return relacionamentosComColetaDado;
    }

    public List<DadoCRM> getDadosColetadosPorTipoRelacionamento(TipoRelacionamento pTipoRelacionamento) {
        if (UtilCRCListas.isNuloOuVazio(listaDadosCRMPorTipoRelacionamento)) {
            listaDadosCRMPorTipoRelacionamento = ListasProspectos.DADOS_COLETADOS_POR_TIPO_RELACIONAMENTO.getLista(this, pTipoRelacionamento);
        }
        return listaDadosCRMPorTipoRelacionamento;
    }

    public List<DadoCRM> getDadosColetadosPorTipoAtividade(TipoAtividadeCRM pTipoRelacionamento) {
        if (UtilCRCListas.isNuloOuVazio(listaDadosCRMPorTipoRelacionamento)) {
            listaDadosCRMPorTipoRelacionamento = ListasProspectos.DADOS_COLETADOS_POR_TIPO_ATIVIDADE.getLista(this, pTipoRelacionamento);
        }
        return listaDadosCRMPorTipoRelacionamento;
    }

    public List<DadoCRM> getListaDadosColetadosNaoVinculado() {
        if (UtilCRCListas.isNuloOuVazio(listaDadosColetadosNaoVinculados)) {
            listaDadosColetadosNaoVinculados = ListasProspectos.DADOS_COLETADOS_NAO_VINCULADOS.getLista(this);
        }
        return listaDadosColetadosNaoVinculados;
    }

    public List<TipoRelacionamento> getTipoRelacionamentosPossiveisRetroagir() {
        List<TipoRelacionamento> listaRelacionamentosPositivos = new ArrayList<>();

        for (TipoAtividadeCRM tipoAtividade : getAtividadesDisponiveis()) {
            if (tipoAtividade.isGeraNovoRelacionamento()) {
                if (tipoAtividade.getRelacionamentoGerado().getPeso() < getRelacionamento().getPeso()) {
                    listaRelacionamentosPositivos.add(tipoAtividade.getRelacionamentoGerado());
                }
            }
        }
        return listaRelacionamentosPositivos;
    }

    /**
     * Utilize isAtendenteLogadoTemAcesso()
     *
     * @return isAtendenteLogadoTemAcesso()
     */
    @Transient
    public boolean isUsuarioLogadoResponsavel() {
        return getCampoInstanciadoByNomeOuAnotacao(CPPessoa.atendentelogadotemacesso).getValorComoBoolean();
    }

    public List<DadoCRM> getListaDadosNaoColetadosCRM(AtividadeCRM pTipoRelacionamento) {

        return ListasProspectos.DADOS_NAO_COLETADOS.getLista(this, pTipoRelacionamento);
    }

    /**
     *
     * Este médo só deve ser chamado pelo ACAOCRM, salvar atividade
     *
     * @param pNovoRelacionamento Novo relacionamento setado
     */
    @Deprecated
    public void alterarRelacionamento(TipoRelacionamento pNovoRelacionamento) throws ErroValidacao {

        getCPinst("relacionamento").setValorSeValido(pNovoRelacionamento);

    }

    public List<TipoRelacionamento> getRelacionamentosPossiveisEvolucao() {
        List<TipoRelacionamento> listaRelacionamentosPositivos = new ArrayList<>();

        for (TipoAtividadeCRM tipoAtividade : getAtividadesDisponiveis()) {
            if (tipoAtividade.isGeraNovoRelacionamento()) {
                if (tipoAtividade.getRelacionamentoGerado().getPeso() >= getRelacionamento().getPeso()) {
                    listaRelacionamentosPositivos.add(tipoAtividade.getRelacionamentoGerado());
                }
            }
        }
        return listaRelacionamentosPositivos;
    }

    public boolean isContatoInicialPreechido() {
        return !(UtilCRCStringValidador.isNuloOuEmbranco(getResponsavel())
                && UtilCRCStringValidador.isNuloOuEmbranco(getEmail()));

    }

    public List<DocsEquipeDaCategoria> getDocEquipeCategorias() {
        return docEquipeCategorias;
    }

    public void setDocEquipeCategorias(List<DocsEquipeDaCategoria> docEquipeCategorias) {
        this.docEquipeCategorias = docEquipeCategorias;
    }

    public List<DocsClienteDaCategoria> getDocClienteCategorias() {
        return docClienteCategorias;
    }

    public void setDocClienteCategorias(List<DocsClienteDaCategoria> docClienteCategorias) {
        this.docClienteCategorias = docClienteCategorias;
    }

    public List<IntegracaoLink> getIntegracoesLink() {
        return integracoesLink;
    }

    public void setIntegracoesLink(List<IntegracaoLink> integracoesLink) {
        this.integracoesLink = integracoesLink;
    }

    public boolean isUsuarioLogadoSolicitouAcesso() {
        return usuarioLogadoSolicitouAcesso;
    }

    public void setUsuarioLogadoSolicitouAcesso(boolean usuarioLogadoSolicitouAcesso) {
        this.usuarioLogadoSolicitouAcesso = usuarioLogadoSolicitouAcesso;
    }

    public List<ChamadoCliente> getChamadosAbertos() {
        return chamadosAbertos;
    }

    public void setChamadosAbertos(List<ChamadoCliente> chamadosAbertos) {
        this.chamadosAbertos = chamadosAbertos;
    }

    public List<Solicitacao> getSolicitacoesAbertas() {
        return solicitacoesAbertas;
    }

    public void setSolicitacoesAbertas(List<Solicitacao> solicitacoesAbertas) {
        this.solicitacoesAbertas = solicitacoesAbertas;
    }

    public List<UsuarioConvidado> getUsuariosConvidados() {
        return usuariosConvidados;
    }

    public void setUsuariosConvidados(List<UsuarioConvidado> usuariosConvidados) {
        this.usuariosConvidados = usuariosConvidados;
    }

    public List<ArquivoAnexado> getUltimosArquivos() {
        return ultimosArquivos;
    }

    public void setUltimosArquivos(List<ArquivoAnexado> ultimosArquivos) {
        this.ultimosArquivos = ultimosArquivos;
    }

}
