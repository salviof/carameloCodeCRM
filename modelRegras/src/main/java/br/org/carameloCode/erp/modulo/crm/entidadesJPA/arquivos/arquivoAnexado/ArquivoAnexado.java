/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmail.ArquivoAnexadoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexadoEmailEmConteudo.ArquivoAnexadoEmailEmConteudo;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaPrivada;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoArquivo;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author SalvioF
 */
@Entity
@InfoObjetoSB(plural = "Arquivos anexados ", tags = "Arquivo anexado ", modulo = ERPCrm.NOME_MODULO_ERP)
@DiscriminatorColumn(name = "tipoAnexo")
@EntityListeners(ListenerEntidadePadrao.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ArquivoAnexado extends EntidadeORMNormal implements ComoArquivo {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE)
    private String arquivo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = Pessoa.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Pessoa prospecto;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoAnexo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, somenteLeitura = true)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioCriou;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO, somenteLeitura = true)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioAtualizou;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, somenteLeitura = true)

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO, somenteLeitura = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEdicao;

    @ManyToMany(targetEntity = EnvioEmail.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "envioEmail_arquivoAnexado",
            joinColumns = @JoinColumn(name = "arquivoAnexado_id"),
            inverseJoinColumns = @JoinColumn(name = "envioEmail_id"))
    private List<EnvioEmail> emailsEnviadosComAnexo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    @InfoCampoValorLogico(atualizarSempreQueSalvar = true, nomeCalculo = "Icone de acordo com extenção do arquivo")
    private String icone;

    @ManyToOne(targetEntity = CategoriaArquivoEquipe.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private CategoriaArquivoEquipe categoriaArqEquipe;

    @ManyToOne
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private SubPastaPrivada subPasta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO, label = "Ativo")
    private boolean ativo = true;

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
        if (nome == null) {
            nome = arquivo;
        }
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public void setProspecto(Pessoa prospecto) {
        this.prospecto = prospecto;
    }

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public UsuarioSB getUsuarioCriou() {
        return usuarioCriou;
    }

    public void setUsuarioCriou(UsuarioSB usuarioCriou) {
        this.usuarioCriou = usuarioCriou;
    }

    public UsuarioSB getUsuarioAtualizou() {
        return usuarioAtualizou;
    }

    public void setUsuarioAtualizou(UsuarioSB usuarioAtualizou) {
        this.usuarioAtualizou = usuarioAtualizou;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Date getDataHoraEdicao() {
        return dataHoraEdicao;
    }

    public void setDataHoraEdicao(Date dataHoraEdicao) {
        this.dataHoraEdicao = dataHoraEdicao;
    }

    public List<EnvioEmail> getEmailsEnviadosComAnexo() {
        return emailsEnviadosComAnexo;
    }

    public void setEmailsEnviadosComAnexo(List<EnvioEmail> emailsEnviadosComAnexo) {
        this.emailsEnviadosComAnexo = emailsEnviadosComAnexo;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ArquivoAnexadoEmailRecebido getComoArquivoAnexadoEmailRecebido() {
        return (ArquivoAnexadoEmailRecebido) this;
    }
//

    public ArquivoAnexadoEmailEmConteudo getComoArquivoAnexadoEmailConteudo() {
        return (ArquivoAnexadoEmailEmConteudo) this;
    }

    public CategoriaArquivoEquipe getCategoriaArqEquipe() {
        return categoriaArqEquipe;
    }

    public void setCategoriaArqEquipe(CategoriaArquivoEquipe categoriaArqEquipe) {
        this.categoriaArqEquipe = categoriaArqEquipe;
    }

    public SubPastaPrivada getSubPasta() {
        return subPasta;
    }

    public void setSubPasta(SubPastaPrivada subPasta) {
        this.subPasta = subPasta;
    }

}
