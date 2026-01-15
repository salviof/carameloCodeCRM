/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.sms.MensagemSMS;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeContatoPessoa;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Bairro;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Cidade;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.UnidadeFederativa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Contatos da empresa", tags = "Contato da empresa")
@EntityListeners(ListenerContatoProspecto.class)
@Table(name = "ContatoProspecto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoContato")
@Entity(name = "ContatoProspecto")
public class ContatoProspecto extends EntidadeContatoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, label = "Nome", obrigatorio = true, valorMinimo = 2)
    @InfoCampoValidadorLogico()
    private String nome;
    @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "prospectosDisponiveis")
    private Pessoa prospecto;

    private String cargo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TELEFONE_CELULAR, label = "Whatsapp")
    private String celular;

    @InfoCampoValorLogico(nomeCalculo = "celularFormatoInternacional")
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String celularFormatoInternacional;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TELEFONE_GENERICO)
    @Deprecated
    private String telefoneAlternativo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.EMAIL, label = "E-mail", obrigatorio = false)
    @InfoCampoValidadorLogico()
    private String email;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String observacao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean umContatoPrincipal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean emailClienteverificado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean telefoneClienteverificado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraVerificacaoCliente;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoContato;

    @InfoCampoValorLogico(nomeCalculo = "Prospectos Disponiveis")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @Transient
    private List<PessoaJuridica> prospectosDisponiveis;

    @ManyToOne(targetEntity = LocalizacaoDeContato.class, optional = true)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, somenteLeitura = true, obrigatorio = false)
    private LocalizacaoDeContato localizacaoContato;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValorLogico(nomeCalculo = "Código Mautic", atualizarSempreQueSalvar = false)
    private String codigoMautic;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValorLogico(nomeCalculo = "usuárioVinculado", atualizarSempreQueSalvar = true)
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private UsuarioCrmCliente usuarioVinculado;

    @InfoCampoValorLogico(nomeCalculo = "ativo")
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO)
    private boolean ativo = true;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, targetEntity = ReservaHorario.class)
    @JoinTable(name = "contatosAtendidos_reservas",
            joinColumns = @JoinColumn(name = "contatoatendido_id"),
            inverseJoinColumns = @JoinColumn(name = "reservaHorario_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "prospecto.contatosProspecto")
    private List<ReservaHorario> reservas;

    @OneToMany(targetEntity = MensagemMktWhatsapp.class, mappedBy = "contato", orphanRemoval = true)
    private List<MensagemMktWhatsapp> mensagens;

    @OneToMany(targetEntity = MensagemSMS.class, mappedBy = "contato", orphanRemoval = true)
    private List<MensagemSMS> mensagensSMS;

    public ContatoProspecto() {

    }

    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = Pessoa.class)
    @Override
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        Pessoa p = getParametroInicialEnviado(Pessoa.class, parametros);
        if (p != null) {
            setProspecto(p);
        }
    }

    @Override
    public Long getId() {
        return (Long) id;
    }

    @Override
    public void setId(Long id) {
        this.id = (Long) id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public void setProspecto(Pessoa prospecto) {
        this.prospecto = prospecto;
    }

    public boolean isUmContatoPrincipal() {
        return umContatoPrincipal;
    }

    public void setUmContatoPrincipal(boolean umContatoPrincipal) {
        this.umContatoPrincipal = umContatoPrincipal;
    }

    public String getPrimeiroNome() {
        try {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(nome)) {
                return nome.split(" ")[0];
            }
        } catch (Throwable t) {
            return "";
        }
        return "";
    }

    public List<PessoaJuridica> getProspectosDisponiveis() {
        return prospectosDisponiveis;
    }

    public void setProspectosDisponiveis(List<PessoaJuridica> prospectosDisponiveis) {
        this.prospectosDisponiveis = prospectosDisponiveis;
    }

    @Override
    public void instanciarNovoEndereco() {
        setLocalizacao(new LocalizacaoDeContato());
        getLocalizacao().setBairro(new Bairro());
        getLocalizacao().getBairro().setCidade(new Cidade());
        getLocalizacao().getBairro().getCidade().setUnidadeFederativa(new UnidadeFederativa());
    }

    @Override
    public void setLocalizacao(ComoLocal pLocal) {
        localizacaoContato = (LocalizacaoDeContato) pLocal;
    }

    @Override
    public String getApelido() {
        return getEmail();
    }

    @Override
    public String getTelefonePrincipal() {
        return celular;
    }

    public ContatoPessoal getComoContatoPrivado() {
        try {
            return (ContatoPessoal) this;
        } catch (Throwable t) {
            return null;
        }

    }

    public ContatoColaborador getComoContatoColaborador() {
        try {
            return (ContatoColaborador) this;
        } catch (Throwable t) {
            return null;
        }

    }

    public LocalizacaoDeContato getLocalizacaoContato() {
        return localizacaoContato;
    }

    public void setLocalizacaoContato(LocalizacaoDeContato localizacaoContato) {
        this.localizacaoContato = localizacaoContato;
    }

    public boolean isPossuiEmail() {
        return !UtilCRCStringValidador.isNuloOuEmbranco(getEmail());
    }

    public boolean isPossuiTelefone() {
        return !UtilCRCStringValidador.isNuloOuEmbranco(getCelular());
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCelularFormatoInternacional() {
        return celularFormatoInternacional;
    }

    public void setCelularFormatoInternacional(String celularFormatoInternacional) {
        this.celularFormatoInternacional = celularFormatoInternacional;
    }

    public String getCodigoMautic() {
        return codigoMautic;
    }

    public void setCodigoMautic(String codigoMautic) {
        this.codigoMautic = codigoMautic;
    }

    public UsuarioCrmCliente getUsuarioVinculado() {
        return usuarioVinculado;
    }

    public void setUsuarioVinculado(UsuarioCrmCliente usuarioVinculado) {
        this.usuarioVinculado = usuarioVinculado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isUmContatoDeColaborador() {
        return this instanceof ContatoColaborador;
    }

    public boolean isUmContatoDeLead() {
        if (tipoContato != null) {
            return tipoContato.equals(ContatoProspecto.class.getSimpleName());
        }
        return UtilCRCReflexaoObjeto.getClassExtraindoProxy(this.getClass().getSimpleName()).getClass().getSimpleName().equals(ContatoProspecto.class.getSimpleName());

    }

    public boolean isUmContatoPessoal() {
        return this instanceof ContatoPessoal;
    }

    public List<ReservaHorario> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaHorario> reservas) {
        this.reservas = reservas;
    }

    public List<MensagemMktWhatsapp> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<MensagemMktWhatsapp> mensagens) {
        this.mensagens = mensagens;
    }

    public List<MensagemSMS> getMensagensSMS() {
        return mensagensSMS;
    }

    public void setMensagensSMS(List<MensagemSMS> mensagensSMS) {
        this.mensagensSMS = mensagensSMS;
    }

    public boolean isEmailClienteverificado() {
        return emailClienteverificado;
    }

    public void setEmailClienteverificado(boolean emailClienteverificado) {
        this.emailClienteverificado = emailClienteverificado;
    }

    public boolean isTelefoneClienteverificado() {
        return telefoneClienteverificado;
    }

    public void setTelefoneClienteverificado(boolean telefoneClienteverificado) {
        this.telefoneClienteverificado = telefoneClienteverificado;
    }

    public Date getDataHoraVerificacaoCliente() {
        return dataHoraVerificacaoCliente;
    }

    public void setDataHoraVerificacaoCliente(Date dataHoraVerificacaoCliente) {
        this.dataHoraVerificacaoCliente = dataHoraVerificacaoCliente;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

}
