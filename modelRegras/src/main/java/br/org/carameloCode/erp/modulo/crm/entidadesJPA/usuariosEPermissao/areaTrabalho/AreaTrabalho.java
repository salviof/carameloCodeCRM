/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author salviofurbino
 * @since 09/09/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(tags = {"Area de trabalho"}, plural = "Areas de trabalho", modulo = ERPCrm.NOME_MODULO_ERP)
public class AreaTrabalho extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @InfoCampoValorLogico(nomeCalculo = "Nome Area de Trabalho")
    private String nome;
    @ManyToOne(optional = false, targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuario;
    @ManyToOne(targetEntity = MetaRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "usuario.estatisticas.meta")
    private MetaRelacionamento meta;
    @ManyToOne(targetEntity = TipoRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoRelacionamento relacionamento;
    @ManyToOne(targetEntity = TagAtendimento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TagAtendimento tag;

    @InfoCampoValorLogico(nomeCalculo = "Nao Lido Email", atualizarSempreQueSalvar = false)
    private int naoLidoEmailQtd;
    @InfoCampoValorLogico(nomeCalculo = "não lido email desconhecido", atualizarSempreQueSalvar = false)
    private int naoLidoEmaiDesconhecidolQtd;
    @InfoCampoValorLogico(nomeCalculo = "Nao lido assinaturas", atualizarSempreQueSalvar = false)
    private int naoLidoEmailAssinaturasQtd;
    @InfoCampoValorLogico(nomeCalculo = "Nao Lida pessoas", atualizarSempreQueSalvar = false)
    private int naoLidoEmailPessoasQtd;
    @InfoCampoValorLogico(nomeCalculo = "Nao Lido Mensagem", atualizarSempreQueSalvar = false)
    private int naoLidaMensagemQtd;
    @InfoCampoValorLogico(nomeCalculo = "agenda não cumprida", atualizarSempreQueSalvar = false)
    private int naoExecutadoAgendaQtd;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso(iconeNegativo = "fa fa-th-large", iconePostivio = "fa fa-bars", textoNegativo = "Cartões", textoPositivo = "Lista")
    private boolean mostrarListaMinhasPessoas;

    @Transient
    private Date dataHoraAtualizacao;

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
    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioCRM getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCRM usuario) {
        this.usuario = usuario;
    }

    public MetaRelacionamento getMeta() {
        return meta;
    }

    public void setMeta(MetaRelacionamento meta) {
        if (meta == null) {
            relacionamento = null;
        }
        this.meta = meta;
    }

    public TipoRelacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(TipoRelacionamento relacionamento) {
        this.relacionamento = relacionamento;
        if (relacionamento != null) {
            this.meta = relacionamento.getMetaRelacionamento();
        }
    }

    public TagAtendimento getTag() {
        return tag;
    }

    public void setTag(TagAtendimento tag) {
        this.tag = tag;
    }

    public boolean isModoVisualizarTags() {
        return tag != null;
    }

    public boolean isMostrarListaMinhasPessoas() {
        return mostrarListaMinhasPessoas;
    }

    public void setMostrarListaMinhasPessoas(boolean mostrarListaMinhasPessoas) {
        this.mostrarListaMinhasPessoas = mostrarListaMinhasPessoas;
    }

    public int getNaoLidoEmailQtd() {
        return naoLidoEmailQtd;
    }

    public void setNaoLidoEmailQtd(int naoLidoEmailQtd) {
        this.naoLidoEmailQtd = naoLidoEmailQtd;
    }

    public int getNaoLidoEmaiDesconhecidolQtd() {
        return naoLidoEmaiDesconhecidolQtd;
    }

    public void setNaoLidoEmaiDesconhecidolQtd(int naoLidoEmaiDesconhecidolQtd) {
        this.naoLidoEmaiDesconhecidolQtd = naoLidoEmaiDesconhecidolQtd;
    }

    public int getNaoLidoEmailAssinaturasQtd() {
        return naoLidoEmailAssinaturasQtd;
    }

    public void setNaoLidoEmailAssinaturasQtd(int naoLidoEmailAssinaturasQtd) {
        this.naoLidoEmailAssinaturasQtd = naoLidoEmailAssinaturasQtd;
    }

    public int getNaoLidoEmailPessoasQtd() {
        return naoLidoEmailPessoasQtd;
    }

    public void setNaoLidoEmailPessoasQtd(int naoLidoEmailPessoasQtd) {
        this.naoLidoEmailPessoasQtd = naoLidoEmailPessoasQtd;
    }

    public int getNaoLidaMensagemQtd() {
        return naoLidaMensagemQtd;
    }

    public void setNaoLidaMensagemQtd(int naoLidaMensagemQtd) {
        this.naoLidaMensagemQtd = naoLidaMensagemQtd;
    }

    public int getNaoExecutadoAgendaQtd() {
        return naoExecutadoAgendaQtd;
    }

    public void setNaoExecutadoAgendaQtd(int naoExecutadoAgendaQtd) {
        this.naoExecutadoAgendaQtd = naoExecutadoAgendaQtd;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

}
