/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.LogEMailEnviadoLido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
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
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.comunicacao.ComoEmailSimples;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Documento Enviado "}, plural = "Documentos enviados ")
public class EnvioEmail extends EmailCrm implements ComoEmailSimples {

    @Deprecated
    public EnvioEmail() {
    }

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date datadisparo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricaoEnvio;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML_TEMPLATE)
    @ManyToOne(targetEntity = ModeloEmail.class)
    private ModeloEmail modeloEmail;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "envioDocumento_contatos",
            joinColumns = @JoinColumn(name = "envioDocumento_id"),
            inverseJoinColumns = @JoinColumn(name = "contatos_id"))
    @InfoCampo(label = "Destinatários", tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "contatosDisponiveis")
    @InfoCampoValidadorLogico
    private List<ContatoProspecto> contatos;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "Contatos Disponiveis", atualizarSempreQueSalvar = false)
    private List<ContatoProspecto> contatosDisponiveis;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "envioEmail_arquivoAnexado",
            joinColumns = @JoinColumn(name = "envioEmail_id"),
            inverseJoinColumns = @JoinColumn(name = "arquivoAnexado_id"))
    @InfoCampoValorLogico(nomeCalculo = "Arquivos anexados", atualizarSempreQueSalvar = false)
    private List<ArquivoAnexado> arquivosAnexados;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @InfoCampoValorLogico(nomeCalculo = "Arquivos disponíveis", atualizarSempreQueSalvar = false)
    private List<ArquivoAnexado> arquivosDisponiveis;

    @ManyToOne(targetEntity = StatusEnvioEmail.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private StatusEnvioEmail statusEnvio;

    @CalculoEnvioEmail(calculo = CalculosEnvioEmail.FOI_ENVIADO)
    private boolean foiEnviado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso(textoPositivo = "Copiar Colaboradores", iconeNegativo = "Não Enviar Cópia")
    private boolean enviarCopiaColaboradores;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso(textoPositivo = "Enviar para mim", iconeNegativo = "não Enviar para mim")
    private boolean enviarCopiaUsuarioEnviou;

    @InfoCampo(label = "UltimoRascunho")
    @Transient
    private EnvioEmailRascunhoAutoSave ultimoRascunho;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA, label = "Horário agendamento disparo")
    @Temporal(TemporalType.TIMESTAMP)
    @Future
    private Date dataHoraAgendamentoDisparo;

    @InfoCampoValorLogico(nomeCalculo = "Lido pelo destinatário", atualizarSempreQueSalvar = false)
    //   @Transient
    private boolean foiLidoPeloDestinatario;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "CampoForJson")
    @Column(length = 5000)
    private String htmlMailCampoParaJson;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "CampoForJson")
    @Column(length = 5000)
    private String htmlMailCampoEmCopiaJson;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "email", cascade = CascadeType.ALL)
    private List<LogEMailEnviadoLido> logsDeLeitura;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean emRevisao;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<LogFalhaEnvioEmail> falhasEnvio;

    @ManyToOne
    private UsuarioCRM revisor;

    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {PessoaJuridica.class})
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        if (parametros.length > 1) {

            try {
                setProspecto(getParametroInicialEnviado(PessoaJuridica.class, parametros));
            } catch (Throwable t) {

            }

            if (getProspecto() == null) {
                //   throw new ErroPreparandoObjeto(this, "O prospecto não foi informado");
                setUmEmailDeProspecto(false);
            }
            setStatusEnvio(FabStatusEnvioEmail.RASCUNHO.getRegistro());
            contatos = new ArrayList<>();
        }
    }

    public Date getDatadisparo() {
        return datadisparo;
    }

    public void setDatadisparo(Date datadisparo) {
        this.datadisparo = datadisparo;
    }

    @OneToMany(mappedBy = "emailVinculado", orphanRemoval = true)
    private List<EnvioEmailRascunhoAutoSave> historico;

    public List<ContatoProspecto> getContatos() {
        if (contatos == null || contatos.isEmpty()) {
            contatos = new ArrayList<>();
            if (getProspecto() != null) {
                //    ContatoProspecto novoContato = new ContatoProspecto();

                //      novoContato.setNome(getProspecto().getNome());
                //      novoContato.setEmail(getProspecto().getEmail());
                //    contatos.add(novoContato);
            }

        }
        return contatos;
    }

    public void setContatos(List<ContatoProspecto> contatos) {
        this.contatos = contatos;
    }

    public String getDescricaoEnvio() {
        return descricaoEnvio;
    }

    public void setDescricaoEnvio(String descricaoEnvio) {
        this.descricaoEnvio = descricaoEnvio;
    }

    public List<ArquivoAnexado> getArquivosAnexados() {
        return arquivosAnexados;
    }

    public void setArquivosAnexados(List<ArquivoAnexado> arquivosAnexados) {
        this.arquivosAnexados = arquivosAnexados;
    }

    public StatusEnvioEmail getStatusEnvio() {
        return statusEnvio;
    }

    public void setStatusEnvio(StatusEnvioEmail pStatusEnvio) {
        if (pStatusEnvio.equals(FabStatusEnvioEmail.ENVIADO.getRegistro())) {
            foiEnviado = true;
        }
        this.statusEnvio = pStatusEnvio;
    }

    public void adicionarContato(ContatoProspecto pNovoContato) {
        try {
            if (pNovoContato == null) {
                return;
            }
            if (UtilCRCStringValidador.isNAO_NuloNemBranco(pNovoContato.getEmail())) {
                contatos.add(pNovoContato);
            } else {
                SBCore.getCentralDeMensagens().enviarMsgErroAoUsuario("O contato " + pNovoContato.getEmail() + "não possui um e-mail cadastrado");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao ", t);
        }
    }

    public List<ContatoProspecto> getContatosDoProspectoComEmail() {
        List<ContatoProspecto> contatosComEmail = new ArrayList<>();
        getProspecto().getContatosComEmail().forEach((cont) -> {
            contatosComEmail.add(cont);
        });
        return contatosComEmail;
    }

    public boolean isFoiEnviado() {
        return foiEnviado;
    }

    public ModeloEmail getModeloEmail() {
        return modeloEmail;
    }

    public void setModeloEmail(ModeloEmail modeloEmail) {
        this.modeloEmail = modeloEmail;
    }

    public void adicionarArquivoAnexado(ArquivoAnexado pArquivo) {
        if (arquivosAnexados == null) {
            arquivosAnexados = new ArrayList<>();
        }
        if (!arquivosAnexados.contains(pArquivo)) {
            arquivosAnexados.add(pArquivo);
        }
    }

    public String getMensagem() {
        return getTexto();
    }

    public String getEmailDestinatario() {
        return getContatos().get(0).getEmail();
    }

    public String getEmailRementente() {
        return getUsuarioInsersao().getEmail();
    }

    public boolean isEmailEnviadoUsuario() {
        return true;
    }

    public boolean isEmailRecebidoUsuario() {
        return false;
    }

    public Date getDataHoraMensagemRecebida() {
        return datadisparo;
    }

    @Override
    public String getTextoMensagem() {
        return getTexto();
    }

    @Override
    public String getEmailsDestinatarios() {

        return UtilCRCListas.getAtributoSeparadosPorPontoVirgula(getContatos(), "email");

    }

    @Override
    public String getEmailRemetente() {
        return getUsuarioCriou().getEmail();
    }

    @Override
    @PreUpdate
    protected void autoExecAntesSalvarAtualizarRegistro() {
        super.autoExecAntesSalvarAtualizarRegistro();
        if (!getStatusEnvio().equals(FabStatusEnvioEmail.RASCUNHO.getRegistro())) {
            if (getHistorico() != null) {
                getHistorico().clear();
            }
        }
    }

    public List<EnvioEmailRascunhoAutoSave> getHistorico() {
        return historico;
    }

    public EnvioEmailRascunhoAutoSave getUltimoRascunho() {
        return ultimoRascunho;
    }

    public boolean isEnviarCopiaColaboradores() {
        return enviarCopiaColaboradores;
    }

    public void setEnviarCopiaColaboradores(boolean enviarCopiaColaboradores) {
        this.enviarCopiaColaboradores = enviarCopiaColaboradores;
    }

    public boolean isEnviarCopiaUsuarioEnviou() {
        return enviarCopiaUsuarioEnviou;
    }

    public void setEnviarCopiaUsuarioEnviou(boolean enviarCopiaUsuarioEnviou) {
        this.enviarCopiaUsuarioEnviou = enviarCopiaUsuarioEnviou;
    }

    public List<ContatoProspecto> getContatosDisponiveis() {
        return contatosDisponiveis;
    }

    public void setContatosDisponiveis(List<ContatoProspecto> contatosDisponiveis) {
        this.contatosDisponiveis = contatosDisponiveis;
    }

    enum tipoDeEnvio {
        COLABORADORES, PROSPECTOS, MARKETING
    }

    @Enumerated(EnumType.ORDINAL)
    private tipoDeEnvio tipoDeEnvio;

    public List<ArquivoAnexado> getArquivosDisponiveis() {
        return arquivosDisponiveis;
    }

    public void setArquivosDisponiveis(List<ArquivoAnexado> arquivosDisponiveis) {
        this.arquivosDisponiveis = arquivosDisponiveis;
    }

    public tipoDeEnvio getTipoDeEnvio() {
        return tipoDeEnvio;
    }

    public void setTipoDeEnvio(tipoDeEnvio tipoDeEnvio) {
        this.tipoDeEnvio = tipoDeEnvio;
    }

    public Date getDataHoraAgendamentoDisparo() {
        return dataHoraAgendamentoDisparo;
    }

    public void setDataHoraAgendamentoDisparo(Date dataHoraAgendamentoDisparo) {
        this.dataHoraAgendamentoDisparo = dataHoraAgendamentoDisparo;
    }

    public String getHtmlMailCampoParaJson() {
        return htmlMailCampoParaJson;
    }

    public void setHtmlMailCampoParaJson(String htmlMailCampoParaJson) {
        this.htmlMailCampoParaJson = htmlMailCampoParaJson;
    }

    public String getHtmlMailCampoEmCopiaJson() {
        return htmlMailCampoEmCopiaJson;
    }

    public void setHtmlMailCampoEmCopiaJson(String htmlMailCampoEmCopiaJson) {
        this.htmlMailCampoEmCopiaJson = htmlMailCampoEmCopiaJson;
    }

    public boolean isFoiLidoPeloDestinatario() {
        return foiLidoPeloDestinatario;
    }

    public void setFoiLidoPeloDestinatario(boolean foiLidoPeloDestinatario) {
        this.foiLidoPeloDestinatario = foiLidoPeloDestinatario;
    }

    public List<LogEMailEnviadoLido> getLogsDeLeitura() {
        return logsDeLeitura;
    }

    public void setLogsDeLeitura(List<LogEMailEnviadoLido> logsDeLeitura) {
        this.logsDeLeitura = logsDeLeitura;
    }

    public boolean isEmRevisao() {
        return emRevisao;
    }

    public void setEmRevisao(boolean emRevisao) {
        this.emRevisao = emRevisao;
    }

    public UsuarioCRM getRevisor() {
        return revisor;
    }

    public void setRevisor(UsuarioCRM revisor) {
        this.revisor = revisor;
    }

    public List<LogFalhaEnvioEmail> getFalhasEnvio() {
        return falhasEnvio;
    }

    public void setFalhasEnvio(List<LogFalhaEnvioEmail> falhasEnvio) {
        this.falhasEnvio = falhasEnvio;
    }

    public void setFoiEnviado(boolean foiEnviado) {
        this.foiEnviado = foiEnviado;
    }

}
