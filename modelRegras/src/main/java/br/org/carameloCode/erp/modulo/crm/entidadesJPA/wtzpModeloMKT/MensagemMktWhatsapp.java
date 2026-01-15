package br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Mensagem de Wtzap", plural = "Manssagens de whatazapp", icone = "fa fa-whatsapp")
public class MensagemMktWhatsapp extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = TipoMensagemMktWhatsApp.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    @InfoCampoValidadorLogico()
    private TipoMensagemMktWhatsApp tipo;

    @ManyToOne(targetEntity = Pessoa.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    @InfoCampoValorLogico(nomeCalculo = "pessoa")
    private Pessoa pessoa;

    @ManyToOne(targetEntity = ContatoProspecto.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ContatoProspecto contato;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private String reciboEnvio;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso
    private boolean enviado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso
    private boolean recebido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso
    private boolean lido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    @ManyToOne(targetEntity = AtividadeCRM.class)
    private AtividadeCRM atividade;

    @InfoCampo(label = "Link Preview mensagem", somenteLeitura = true)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "LinkPreview")
    private String linkPreview;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = ContatoProspecto.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        contato = getParametroInicialEnviado(ContatoProspecto.class, parametros);
        pessoa = contato.getProspecto();
    }

    public Long getId() {
        return id;
    }

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeCRM atividade) {
        this.atividade = atividade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMensagemMktWhatsApp getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensagemMktWhatsApp tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public ContatoProspecto getContato() {
        return contato;
    }

    public void setContato(ContatoProspecto contato) {
        this.contato = contato;
    }

    public String getReciboEnvio() {
        return reciboEnvio;
    }

    public void setReciboEnvio(String reciboEnvio) {
        this.reciboEnvio = reciboEnvio;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public boolean isRecebido() {
        return recebido;
    }

    public void setRecebido(boolean recebido) {
        this.recebido = recebido;
    }

    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }

    public String getLinkPreview() {
        return linkPreview;
    }

    public void setLinkPreview(String linkPreview) {
        this.linkPreview = linkPreview;
    }

}
