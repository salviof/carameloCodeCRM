package br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Versão do assunto e do conteúdo de um modelo de e-mail, registrada a cada
 * alteração, feita pelo usuário ou por uma execução de IA.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Históricos de alterações de modelo de e-mail", tags = "Histórico de alteração de modelo de e-mail", icone = "fa fa-clock-rotate-left")
@EntityListeners(ListenerEntidadePadrao.class)
public class HistoricoAltModeloEmail extends EntidadeSimples {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Assunto do modelo de e-mail nesta versão.", label = "Assunto")
    private String assunto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML, descricao = "Conteúdo do modelo de e-mail nesta versão.", label = "Conteúdo")
    @Column(columnDefinition = "TEXT")
    private String conteudo;

    @ManyToOne(targetEntity = ModeloEmail.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Modelo de e-mail alterado.", label = "Modelo")
    private ModeloEmail modelo;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Data e hora")
    private Date dataHora;

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, descricao = "Usuário que fez a alteração.", label = "Usuário")
    private UsuarioSB usuario;

    @ManyToOne(targetEntity = ExecucaoIA.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Execução de IA que gerou a alteração; vazio quando a alteração foi manual.", label = "Execução de IA")
    private ExecucaoIA execucaoIA;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ModeloEmail getModelo() {
        return modelo;
    }

    public void setModelo(ModeloEmail modelo) {
        this.modelo = modelo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public UsuarioSB getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSB usuario) {
        this.usuario = usuario;
    }

    public ExecucaoIA getExecucaoIA() {
        return execucaoIA;
    }

    public void setExecucaoIA(ExecucaoIA execucaoIA) {
        this.execucaoIA = execucaoIA;
    }

}
