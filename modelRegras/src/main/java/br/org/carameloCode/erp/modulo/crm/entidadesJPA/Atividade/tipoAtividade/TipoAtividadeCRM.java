/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chatBot.TipoChatBot;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.transacionalMkt.tipo.TipoEmailTransacional;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.InfoGrupoCampo;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
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
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 *
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Tipo atividade CRM "}, plural = "Tipos de Atividade CRM ", icone = "fa fa-paint-brush", modulo = ERPCrm.NOME_MODULO_ERP)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidade")
public class TipoAtividadeCRM extends EntidadeSimplesORM implements ComoAcaoDoSistema {

    @Id()
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "geradorIdTipoAtividade",
            strategy = "br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.GeradorIdTipoAtividade")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "geradorIdTipoAtividade")

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, label = "Código")
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, obrigatorio = true)
    private String nomeAtividade;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, obrigatorio = true)
    private String nomeInicioAtivida;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, obrigatorio = true)
    private String nomeFimAtividade;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEntidade;

    //@Transient
    //private List<FabDadoCRM> camposNescessarios;
    private String textoAjuda;
    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String cor;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE, descricao = "https://fontawesome.com/v4.7.0/examples/")
    private String icone;
    @ManyToOne(targetEntity = TipoRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoRelacionamento relacionamentoGerado;
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean progresso = false;
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean regresso = false;
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Gera e-mail?")
    @InfoCampoVerdadeiroOuFalso()
    private boolean temDisparoDeEmail = false;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Executar direto sem Relatório?")
    @InfoCampoValidadorLogico()
    @InfoCampoVerdadeiroOuFalso()
    private boolean execucaoDiretaSemRelatorio = false;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, label = "Formulário alternativo", descricao = "Um formulário alternativo é um formulário customizado por desenvolvedores.")
    @InfoCampoValorLogico(nomeCalculo = "xhtmlAlternativo")
    private String xhtmlAlternativo;
    private int diasAgendarNovaAtividade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Precisa ter serviços")
    @InfoCampoVerdadeiroOuFalso()
    private boolean precisaServicosDefinidos = false;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Precisa ter préAnálise")
    @InfoCampoVerdadeiroOuFalso()
    private boolean precisaTerPreAnalise = false;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Precisa enviar o documento")
    @InfoCampoVerdadeiroOuFalso()
    private boolean precisaEnviarDocumento = true;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Precisa ter Imagem cadastrada")
    @InfoCampoVerdadeiroOuFalso()
    private boolean precisaTerImagem;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Precisa ter anexo no e-mail")
    @InfoCampoVerdadeiroOuFalso()
    private boolean precisaTerAnexo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Precisa ter Site")
    @InfoCampoVerdadeiroOuFalso()
    private boolean precisaTerSite;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Modo pós vendas?", descricao = "Ativa o modo pós vendas, para empresa ou pessoa, ao concluir a atividades")
    @InfoCampoVerdadeiroOuFalso()
    private boolean ativaModoPosVendas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Apenas pós vendas", descricao = "Disponibilizar apenas no pós vendas")
    @InfoCampoVerdadeiroOuFalso()
    private boolean disponivelApenasPosVendas;

    @InfoCampo(label = "Email Mkt", descricao = "Eviar e-mail de Marketing trasacional ao concluir",
            tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "emailsTransacionaisDisponiveis")
    // @ManyToOne(targetEntity = TipoEmailTransacional.class)
    @Transient
    private TipoEmailTransacional emailTransacionalMkt;

    @Transient
    // @InfoCampoValorLogico(nomeCalculo = "EmailsTransacionaisDisponiveis")
    private List<TipoEmailTransacional> emailsTransacionaisDisponiveis;

//    @CalculoTipoAtividadeCRM(calculo = CalculosTipoAtividadeCRM.FORMULARIO_EXECUCAO)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "formularioExecucao")
    private AtividadeCRM acaoFormularioExecucao;

    @ManyToOne(targetEntity = ModeloEmail.class, cascade = CascadeType.ALL)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValidadorLogico()
    private ModeloEmail modeloEmail;

    @Transient
    //@CalculoTipoAtividadeCRM(calculo = CalculosTipoAtividadeCRM.FORMULARIO_EXECUCAO)
    @InfoCampoValorLogico(nomeCalculo = "AtividadeformularioExecucao")
    private AtividadeCRM atividadeAbertaPeloUsuarioLogado;

    @ManyToOne(targetEntity = TipoAtividadeCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoAtividadeCRM atividadeAgendada;

    @ManyToOne(targetEntity = TipoAtividadeCRM.class)
    @InfoCampo(label = "Atividade Agendada apos leitura email", tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoAtividadeCRM atividadeAgendadaAposLeituraEmail;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "Dias de agendamento após leitura")
    private int diasAgendamentoAposLeitura;

    @OneToMany(mappedBy = "tipoAtividadeVinculada", cascade = CascadeType.ALL, targetEntity = ModeloDocumentoTipoAtividade.class, fetch = FetchType.LAZY)
    public List<ModeloDocumentoTipoAtividade> modelosDocumentoVinculados;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tipoAtividade_autorizados",
            joinColumns = @JoinColumn(name = "tipoAtividade_id"),
            inverseJoinColumns = @JoinColumn(name = "usuarioCRM_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, entidadeOpcoesDisponiveis = UsuarioCRM.class)
    private List<UsuarioCRM> usuariosAutorizados;

    @InfoCampo(label = "Gera Retorno a Relacionamento Anterior",
            descricao = "Indica Que o Relacionamento deve voltar ao relacionamento anterior quando conluir uma atividade",
            tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoValidadorLogico()
    private boolean resultaEmRelacionamentoAnterior;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean atividadePublica;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tagAtendimento_tipoAtividade_adicionar",
            joinColumns = @JoinColumn(name = "tipoAtividade_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, entidadeOpcoesDisponiveis = TagAtendimento.class)
    private List<TagAtendimento> tagsAtendimentoAdicionadas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tagAtendimento_tipoAtividade_remover",
            joinColumns = @JoinColumn(name = "tipoAtividade_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, entidadeOpcoesDisponiveis = TagAtendimento.class)
    private List<TagAtendimento> tagsAtendimentoRemovidas;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "dadosObrigatorios_tipoAtividade",
            joinColumns = @JoinColumn(name = "tipoDadoCRM"),
            inverseJoinColumns = @JoinColumn(name = "tipoAtividade_id"))
    @InfoGrupoCampo(camposDeclarados = {"nome", "label", "mascara", "fabricaTipoAtributo"})
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<TipoDadoCRM> tiposDadosColetarNaAtividade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Ação de plugin vinculado",
            caminhoParaLista = "acoesDePluginsDisponiveis"
    )
    @ManyToOne(targetEntity = AcaoDoSistema.class, cascade = CascadeType.ALL)
    private AcaoDoSistema acaoDePLuginVunculado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Ações de plugin")
    private List<AcaoDoSistema> acoesDePluginsDisponiveis;

    @InfoCampoVerdadeiroOuFalso(iconePostivio = "Clientes", textoNegativo = "Leads em negociação")
    @InfoCampo(label = "Atividade para clientes")
    private boolean apenasCLiente;

    @ManyToOne(targetEntity = TipoMensagemMktWhatsApp.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoMensagemMktWhatsApp tipoMensagemWtzap;

    @ManyToOne(targetEntity = TipoChatBot.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoChatBot tipoChatBot;

    public List<AcaoDoSistema> getAcoesDePluginsDisponiveis() {
        return acoesDePluginsDisponiveis;
    }

    public void setAcoesDePluginsDisponiveis(List<AcaoDoSistema> acoesDePluginsDisponiveis) {
        this.acoesDePluginsDisponiveis = acoesDePluginsDisponiveis;
    }

    public ItfAcaoFormulario getAcaoFormularioExecucao() {
        return (ItfAcaoFormulario) CalculosTipoAtividadeCRM.ACAO_FORMULARIO_EXECUCAO.getValor(this);
    }

//    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Gera novo relacionamento?")
//    private boolean geraNovoRelacionamento;
    private void ajustaCor(String pNovaCor) {

        if (pNovaCor == null || pNovaCor.equalsIgnoreCase("FFFFFF") || pNovaCor.equals("ffffff")) {
            if (getRelacionamentoGerado() != null) {
                if (isProgresso()) {
                    cor = "00cc66";
                }
                if (isRegresso()) {
                    cor = "ff5050";
                }
            } else {
                cor = "337ab7";
            }
        } else {
            cor = pNovaCor;
        }
        if (!UtilCRCStringValidador.isNuloOuEmbranco(cor)) {
            cor = cor.replace("#", "");
        }
    }

    public TipoAtividadeCRM() {
        super();

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public AcaoDoSistema getAcaoDePLuginVunculado() {
        return acaoDePLuginVunculado;
    }

    public void setAcaoDePLuginVunculado(AcaoDoSistema acaoDePLuginVunculado) {
        this.acaoDePLuginVunculado = acaoDePLuginVunculado;
    }

    public String getNomeInicioAtivida() {
        return nomeInicioAtivida;
    }

    public void setNomeInicioAtivida(String nomeInicioAtivida) {
        this.nomeInicioAtivida = nomeInicioAtivida;
    }

    public String getTextoAjuda() {
        return textoAjuda;
    }

    public void setTextoAjuda(String textoAjuda) {
        this.textoAjuda = textoAjuda;
    }

    @Override
    public String getIcone() {
        return icone;
    }

    @Override
    public void setIconeAcao(String icone) {
        this.icone = icone;
    }

    public TipoRelacionamento getRelacionamentoGerado() {
        return relacionamentoGerado;
    }

    public void setRelacionamentoGerado(TipoRelacionamento relacionamentoGerado) {

        this.relacionamentoGerado = relacionamentoGerado;
    }

    public boolean isProgresso() {
        return progresso;
    }

    public void setProgresso(boolean progresso) {
        this.progresso = progresso;
        regresso = !progresso;
    }

    public boolean isRegresso() {
        return regresso;
    }

    public void setRegresso(boolean regresso) {
        this.regresso = regresso;
        progresso = !regresso;
    }

    @Override
    public String getNomeAcao() {
        return nomeInicioAtivida;
    }

    @Override
    public String getIconeAcao() {
        return icone;
    }

    @Override
    public String getCor() {

        ajustaCor(cor);
        return cor;
    }

    @Override
    public String getDescricao() {
        return textoAjuda;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;

    }

    public String getNomeFimAtividaed() {
        return nomeFimAtividade;
    }

    public void setNomeFimAtividaed(String nomeFimAtividaed) {
        this.nomeFimAtividade = nomeFimAtividaed;
    }

    public String getXhtml() {

        return (String) CalculosTipoAtividadeCRM.FORMULARIO_EXECUCAO.getValor(this);

    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return FabModulosCRM.ATENDIMENTO_CRM.getModulo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return true;
    }

    @Override
    public void setNomeAcao(String pNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDescricao(String pDescricao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        FabModulosCRM.ATENDIMENTO_CRM.getRegistro();
    }

    @Override
    public boolean isConfigurado() {
        return true;
    }

    @Override
    public String getNomeEnumOriginal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return FabTipoAcaoSistema.ACAO_FORMULARIO;
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return false;
    }

    @Override
    public void configurarPropriedadesBasicas(ComoAcaoDoSistema pAcaoDoSistema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdDescritivoJira() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdDescritivoJira(String pIdJira) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrecisaPermissao(boolean pPermissao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComoFabricaAcoes getEnumAcaoDoSistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUmaAcaoGenerica() {
        return false;
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return false;
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return false;
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        return false;
    }

    @Override
    public boolean isUmaAcaoController() {
        return false;
    }

    public TipoAtividadeCRM getAtividadeAgendadaAposLeituraEmail() {
        return atividadeAgendadaAposLeituraEmail;
    }

    public void setAtividadeAgendadaAposLeituraEmail(TipoAtividadeCRM atividadeAgendadaAposLeituraEmail) {
        this.atividadeAgendadaAposLeituraEmail = atividadeAgendadaAposLeituraEmail;
    }

    public int getDiasAgendamentoAposLeitura() {
        return diasAgendamentoAposLeitura;
    }

    public void setDiasAgendamentoAposLeitura(int diasAgendamentoAposLeitura) {
        this.diasAgendamentoAposLeitura = diasAgendamentoAposLeitura;
    }

    @Override
    public String getNomeDominio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormulario getComoFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getComoFormularioEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoGerenciarEntidade getComoGestaoEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComoAcaoController getComoController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComoAcaoSecundaria getComoSecundaria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComoAcaoControllerEntidade getComoControllerEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getXhtmlAlternativo() {
        if (UtilCRCStringValidador.isNuloOuEmbranco(xhtmlAlternativo)) {
            return getXhtml();
        }
        return xhtmlAlternativo;
    }

    public void setXhtmlAlternativo(String pXhtmlAlternatico) {
        if (UtilCRCStringValidador.isNuloOuEmbranco(pXhtmlAlternatico)) {
            this.xhtmlAlternativo = null;
        } else {
            if (!pXhtmlAlternatico.equals(getXhtml())) {
                this.xhtmlAlternativo = pXhtmlAlternatico;
            } else {
                this.xhtmlAlternativo = null;
            }
        }
    }

    @Override
    public boolean isAcaoTemModal() {
        return false;
    }

    public boolean isGeraNovoRelacionamento() {
        return getRelacionamentoGerado() != null || resultaEmRelacionamentoAnterior;

    }

    public List<ModeloDocumentoTipoAtividade> getModelosDocumentoVinculados() {
        return modelosDocumentoVinculados;
    }

    public boolean isTemModeloDocumentoVinculado() {
        return !getModelosDocumentoVinculados().isEmpty();
    }

    public boolean isTemModeloEmail() {
        return getModeloEmail() != null;
    }

    public void setCor(String pCor) {
        ajustaCor(pCor);
        this.cor = pCor;
    }

    public String getNomeFimAtividade() {
        return nomeFimAtividade;
    }

    public void setNomeFimAtividade(String nomeFimAtividade) {
        this.nomeFimAtividade = nomeFimAtividade;
    }

    public int getDiasAgendarNovaAtividade() {
        return diasAgendarNovaAtividade;
    }

    public void setDiasAgendarNovaAtividade(int diasAgendarNovaAtividade) {
        this.diasAgendarNovaAtividade = diasAgendarNovaAtividade;
    }

    public TipoAtividadeCRM getAtividadeAgendada() {
        return atividadeAgendada;
    }

    public void setAtividadeAgendada(TipoAtividadeCRM atividadeAgendada) {
        this.atividadeAgendada = atividadeAgendada;
    }

    public AtividadeCRM getAtividadeAbertaPeloUsuarioLogado() {
        atividadeAbertaPeloUsuarioLogado = (AtividadeCRM) CalculosTipoAtividadeCRM.ATIVIDADE_ABERTA_PELO_USUARIO_LOGADO.getValor(this);
        return atividadeAbertaPeloUsuarioLogado;
    }

    public boolean isTemDisparoDeEmail() {
        return temDisparoDeEmail;
    }

    public void setTemDisparoDeEmail(boolean temDisparoDeEmail) {
        this.temDisparoDeEmail = temDisparoDeEmail;
    }

    public ModeloEmail getModeloEmail() {
        return modeloEmail;
    }

    public void setModeloEmail(ModeloEmail pModeloEmail) {
        if (pModeloEmail != null) {
            temDisparoDeEmail = true;
        } else {
            temDisparoDeEmail = false;
        }
        this.modeloEmail = pModeloEmail;
    }

    public boolean isTemFormularioAlternativo() {
        return !UtilCRCStringValidador.isNuloOuEmbranco(xhtmlAlternativo);
    }

    public boolean isPrecisaServicosDefinidos() {
        return precisaServicosDefinidos;
    }

    public void setPrecisaServicosDefinidos(boolean precisaServicosDefinidos) {
        this.precisaServicosDefinidos = precisaServicosDefinidos;
    }

    public boolean isPrecisaTerPreAnalise() {
        return precisaTerPreAnalise;
    }

    public void setPrecisaTerPreAnalise(boolean precisaTerPreAnalise) {
        this.precisaTerPreAnalise = precisaTerPreAnalise;
    }

    public boolean isPrecisaEnviarDocumento() {
        if (!isTemModeloDocumentoVinculado()) {
            precisaEnviarDocumento = false;
        }
        return precisaEnviarDocumento;
    }

    public void setPrecisaEnviarDocumento(boolean precisaEnviarDocumento) {
        this.precisaEnviarDocumento = precisaEnviarDocumento;
    }

    public boolean isPrecisaTerImagem() {
        return precisaTerImagem;
    }

    public void setPrecisaTerImagem(boolean precisaTerImagem) {
        this.precisaTerImagem = precisaTerImagem;
    }

    public boolean isPrecisaTerAnexo() {
        return precisaTerAnexo;
    }

    public void setPrecisaTerAnexo(boolean precisaTerAnexo) {
        this.precisaTerAnexo = precisaTerAnexo;
    }

    public List<UsuarioCRM> getUsuariosAutorizados() {
        return usuariosAutorizados;
    }

    public void setUsuariosAutorizados(List<UsuarioCRM> usuariosAutorizados) {
        this.usuariosAutorizados = usuariosAutorizados;
    }

    public boolean isPrecisaTerSite() {
        return precisaTerSite;
    }

    public void setPrecisaTerSite(boolean precisaTerSite) {
        this.precisaTerSite = precisaTerSite;
    }

    public boolean isExecucaoDiretaSemRelatorio() {
        return execucaoDiretaSemRelatorio;
    }

    public void setExecucaoDiretaSemRelatorio(boolean execucaoDiretaSemRelatorio) {
        this.execucaoDiretaSemRelatorio = execucaoDiretaSemRelatorio;
    }

    public boolean isResultaEmRelacionamentoAnterior() {
        return resultaEmRelacionamentoAnterior;
    }

    public void setResultaEmRelacionamentoAnterior(boolean resultaEmRelacionamentoAnterior) {
        this.resultaEmRelacionamentoAnterior = resultaEmRelacionamentoAnterior;
    }

    public boolean isAtividadePublica() {
        return atividadePublica;
    }

    public void setAtividadePublica(boolean atividadePublica) {
        this.atividadePublica = atividadePublica;
    }

    public List<TagAtendimento> getTagsAtendimentoAdicionadas() {
        return tagsAtendimentoAdicionadas;
    }

    public void setTagsAtendimentoAdicionadas(List<TagAtendimento> tagsAtendimentoAdicionadas) {
        this.tagsAtendimentoAdicionadas = tagsAtendimentoAdicionadas;
    }

    public List<TagAtendimento> getTagsAtendimentoRemovidas() {
        return tagsAtendimentoRemovidas;
    }

    public void setTagsAtendimentoRemovidas(List<TagAtendimento> tagsAtendimentoRemovidas) {
        this.tagsAtendimentoRemovidas = tagsAtendimentoRemovidas;
    }

    public List<TipoDadoCRM> getTiposDadosColetarNaAtividade() {
        return tiposDadosColetarNaAtividade;
    }

    public void setTiposDadosColetarNaAtividade(List<TipoDadoCRM> tiposDadosColetarNaAtividade) {
        this.tiposDadosColetarNaAtividade = tiposDadosColetarNaAtividade;
    }

    public TipoEmailTransacional getEmailTransacionalMkt() {
        return emailTransacionalMkt;
    }

    public void setEmailTransacionalMkt(TipoEmailTransacional emailTransacionalMkt) {
        this.emailTransacionalMkt = emailTransacionalMkt;
    }

    public List<TipoEmailTransacional> getEmailsTransacionaisDisponiveis() {
        return emailsTransacionaisDisponiveis;
    }

    public void setEmailsTransacionaisDisponiveis(List<TipoEmailTransacional> emailsTransacionaisDisponiveis) {
        this.emailsTransacionaisDisponiveis = emailsTransacionaisDisponiveis;
    }
//

    public boolean isAtivaModoPosVendas() {
        return ativaModoPosVendas;
    }

    public void setAtivaModoPosVendas(boolean ativaModoPosVendas) {
        this.ativaModoPosVendas = ativaModoPosVendas;
    }

    public boolean isDisponivelApenasPosVendas() {
        return disponivelApenasPosVendas;
    }

    public void setDisponivelApenasPosVendas(boolean disponivelApenasPosVendas) {
        this.disponivelApenasPosVendas = disponivelApenasPosVendas;
    }

    public boolean isApenasCLiente() {
        return apenasCLiente;
    }

    public void setApenasCLiente(boolean apenasCLiente) {
        this.apenasCLiente = apenasCLiente;
    }

    public TipoMensagemMktWhatsApp getTipoMensagemWtzap() {
        return tipoMensagemWtzap;
    }

    public void setTipoMensagemWtzap(TipoMensagemMktWhatsApp tipoMensagemWtzap) {
        this.tipoMensagemWtzap = tipoMensagemWtzap;
    }

    public TipoChatBot getTipoChatBot() {
        return tipoChatBot;
    }

    public void setTipoChatBot(TipoChatBot tipoChatBot) {
        this.tipoChatBot = tipoChatBot;
    }

}
