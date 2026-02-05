package br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.TipoMensagemMktWhatsApp;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@InfoObjetoSB(plural = "Disparo de Mensagens WhatsApp", tags = "Disparo WhatsApp", icone = "fa fa-paper-plane")
@Entity
public class DisparoEmMassa extends EntidadeSimples implements ComoTemStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataDisparo;

    @ManyToOne(targetEntity = TipoMensagemMktWhatsApp.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoMensagemMktWhatsApp tipoMensagem;

    @ManyToOne(targetEntity = StatusDisparo.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private StatusDisparo status = FabStatusDisparo.RASCUNHO.getRegistro();

    @InfoCampoVerdadeiroOuFalso(textoPositivo = "Enviar para Secundarios", iconeNegativo = "Só para responsavels", iconePostivio = "fa fa-user", textoNegativo = "fa fa-users")
    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean enviarParaContatosSecundarios;

    @ManyToOne
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValidadorLogico()
    private MetaRelacionamento metaRelacionamento;

    @OneToMany(fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "relacionamentosDisponiveis")
    private List<TipoRelacionamento> relacionamentos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, caminhoParaLista = "metaRelacionamento.tiposRelacionamento")
    @InfoCampoValorLogico(nomeCalculo = "RelacionametosDisponiveis")
    @Transient
    private List<TipoRelacionamento> relacionamentosDisponiveis;

    @OneToMany(mappedBy = "disparoEmMassa")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<MensagemMktWhatsapp> mensagensEnviadas;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDataDisparo() {
        return dataDisparo;
    }

    public void setDataDisparo(Date dataDisparo) {
        this.dataDisparo = dataDisparo;
    }

    public MetaRelacionamento getMetaRelacionamento() {
        return metaRelacionamento;
    }

    public void setMetaRelacionamento(MetaRelacionamento metaRelacionamento) {
        this.metaRelacionamento = metaRelacionamento;
    }

    public List<TipoRelacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public void setRelacionamentos(List<TipoRelacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }

    public StatusDisparo getStatus() {
        return status;
    }

    public void setStatus(StatusDisparo status) {
        this.status = status;
    }

    public boolean isEnviarParaContatosSecundarios() {
        return enviarParaContatosSecundarios;
    }

    public void setEnviarParaContatosSecundarios(boolean enviarParaContatosSecundarios) {
        this.enviarParaContatosSecundarios = enviarParaContatosSecundarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoMensagemMktWhatsApp getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagemMktWhatsApp tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public List<TipoRelacionamento> getRelacionamentosDisponiveis() {
        return relacionamentosDisponiveis;
    }

    public void setRelacionamentosDisponiveis(List<TipoRelacionamento> relacionamentosDisponiveis) {
        this.relacionamentosDisponiveis = relacionamentosDisponiveis;
    }

    public List<MensagemMktWhatsapp> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public void setMensagensEnviadas(List<MensagemMktWhatsapp> mensagensEnviadas) {
        this.mensagensEnviadas = mensagensEnviadas;
    }

    @Override
    public ComoStatus getStatusPrincipal() {
        return status;
    }

}
