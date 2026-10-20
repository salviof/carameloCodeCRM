/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoModeloDocumento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(tags = {"Modelo de E-mail"}, plural = "Modelos de E-mail")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoModelo")
public class ModeloEmail extends EntidadeORMNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @Min(10)
    @InfoCampoValorLogico(nomeCalculo = "Assunto", somenteLeitura = false)
    private String assunto;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoModelo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML_TEMPLATE)
    @InfoCampoModeloDocumento(classeModeloVinculado = PessoaJuridica.class, camposCadastrados = {"nome", "email"})
    @Column(length = 8000, columnDefinition = "TEXT")
    @InfoCampoValorLogico(nomeCalculo = "Conteúdo modelo", somenteLeitura = false)
    private String textoModelo;
    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private UsuarioSB usuariocriou;
    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    private UsuarioSB usuarioEditou;
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private Date dataHoraCriou;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEditou;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @Transient
    private boolean editarComIA = false;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = Persona.class)
    private Persona personaCoautoria;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML)
    @Column(length = 1000, columnDefinition = "TEXT")
    private String instrucoesIAFormatacaoEmail;

    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("dataHora DESC")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, descricao = "Versões anteriores do assunto e do conteúdo, da mais recente para a mais antiga.", label = "Histórico de alterações", somenteLeitura = true)
    private List<HistoricoAltModeloEmail> historicoAlteracoes = new ArrayList<>();

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML)
    @Column(length = 8000, columnDefinition = "TEXT")
    private String instrucaoCompletaIA;

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

    @Override
    public void setNome(String pNome) {
        this.nome = pNome;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getDescritivo() {
        return descricao;
    }

    public void setDescritivo(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioSB getUsuariocriou() {
        return usuariocriou;
    }

    public void setUsuariocriou(UsuarioSB usuariocriou) {
        this.usuariocriou = usuariocriou;
    }

    public UsuarioSB getUsuarioEditou() {
        return usuarioEditou;
    }

    public void setUsuarioEditou(UsuarioSB usuarioEditou) {
        this.usuarioEditou = usuarioEditou;
    }

    public Date getDataHoraCriou() {
        return dataHoraCriou;
    }

    public void setDataHoraCriou(Date dataHoraCriou) {
        this.dataHoraCriou = dataHoraCriou;
    }

    public Date getDataHoraEditou() {
        return dataHoraEditou;
    }

    public void setDataHoraEditou(Date dataHoraEditou) {
        this.dataHoraEditou = dataHoraEditou;
    }

    public String getTextoModelo() {
        return textoModelo;
    }

    public void setTextoModelo(String textoModelo) {
        this.textoModelo = textoModelo;
    }

    public String getTipoModelo() {
        return tipoModelo;
    }

    public void setTipoModelo(String tipoModelo) {
        this.tipoModelo = tipoModelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Persona getPersonaCoautoria() {
        return personaCoautoria;
    }

    public void setPersonaCoautoria(Persona personaCoautoria) {
        this.personaCoautoria = personaCoautoria;
    }

    public String getInstrucoesIAFormatacaoEmail() {
        return instrucoesIAFormatacaoEmail;
    }

    public void setInstrucoesIAFormatacaoEmail(String instrucoesIAFormatacaoEmail) {
        this.instrucoesIAFormatacaoEmail = instrucoesIAFormatacaoEmail;
    }

    public String getInstrucaoCompletaIA() {
        return instrucaoCompletaIA;
    }

    public void setInstrucaoCompletaIA(String instrucaoCompletaIA) {
        this.instrucaoCompletaIA = instrucaoCompletaIA;
    }

    public boolean isEditarComIA() {
        return editarComIA;
    }

    public void setEditarComIA(boolean editarComIA) {
        this.editarComIA = editarComIA;
    }

    public List<HistoricoAltModeloEmail> getHistoricoAlteracoes() {
        return historicoAlteracoes;
    }

    public void setHistoricoAlteracoes(List<HistoricoAltModeloEmail> historicoAlteracoes) {
        this.historicoAlteracoes = historicoAlteracoes;
    }
}
