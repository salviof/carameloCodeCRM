/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta.RespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.tipoResposta.TipoRespostaComunicacao;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoComunicacao;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação confirnação cliente", plural = "Solicitações de confirmações de cliente")
public class SolicitacaoConfirmacaoCliente extends Solicitacao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA,
            label = "Contato", obrigatorio = true, caminhoParaLista = "pessoa.contatosProspecto")
    @ManyToOne(targetEntity = ContatoProspecto.class)
    private ContatoProspecto contatoPessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.HTML)
    @Column(length = 4000)
    @InfoCampoValorLogico(nomeCalculo = "Descrição confirmação")
    private String descricaoConfirmacao;

    @Enumerated(EnumType.STRING)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA,
            nomeOrigem = "Tipo Questão", obrigatorio = true)
    private FabTipoComunicacao fabTipoComunicacao = FabTipoComunicacao.NOTIFICAR;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "respostasPossiveis")
    private TipoRespostaComunicacao tipoRespostaSelecionada;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "RespostaPossíveis")
    private List<TipoRespostaComunicacao> respostasPossiveis;

    @Override
    public ComoTipoComunicacao getTipoComunicacao() {
        if (fabTipoComunicacao == null) {
            return super.getTipoComunicacao(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        } else {
            return fabTipoComunicacao.getRegistro();
        }

    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {Pessoa.class, UsuarioCRM.class})
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        setPessoa(getParametroInicialEnviado(Pessoa.class, parametros));
        setUsuarioSolicitante((UsuarioCRM) SBCore.getUsuarioLogado());

    }

    @Override
    public ItfRespostaComunicacao getRespostaEscolhida() {
        if (tipoRespostaSelecionada != null) {
            return new RespostaComunicacao(this, tipoRespostaSelecionada);
        } else {
            return super.getRespostaEscolhida();
        }
    }

    public String getDescricaoConfirmacao() {
        return descricaoConfirmacao;
    }

    public void setDescricaoConfirmacao(String descricaoConfirmacao) {
        this.descricaoConfirmacao = descricaoConfirmacao;
    }

    public FabTipoComunicacao getFabTipoComunicacao() {
        return fabTipoComunicacao;
    }

    public void setFabTipoComunicacao(FabTipoComunicacao fabTipoComunicacao) {
        this.fabTipoComunicacao = fabTipoComunicacao;
    }

    public void setContatoPessoa(ContatoPessoal contatoPessoa) {
        this.contatoPessoa = contatoPessoa;
    }

    public TipoRespostaComunicacao getTipoRespostaSelecionada() {
        return tipoRespostaSelecionada;
    }

    public void setTipoRespostaSelecionada(TipoRespostaComunicacao tipoRespostaSelecionada) {
        this.tipoRespostaSelecionada = tipoRespostaSelecionada;
    }

    public List<TipoRespostaComunicacao> getRespostasPossiveis() {
        return respostasPossiveis;
    }

    public void setRespostasPossiveis(List<TipoRespostaComunicacao> respostasPossiveis) {
        this.respostasPossiveis = respostasPossiveis;
    }

    public ContatoProspecto getContatoPessoa() {
        return contatoPessoa;
    }

    public void setContatoPessoa(ContatoProspecto contatoPessoa) {
        this.contatoPessoa = contatoPessoa;
    }

}
