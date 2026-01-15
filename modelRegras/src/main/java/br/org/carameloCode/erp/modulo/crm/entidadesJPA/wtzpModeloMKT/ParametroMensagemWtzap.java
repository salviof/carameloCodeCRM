/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.ItfTipoParametroMsgWhatsapp;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValidadorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

@Entity
@InfoObjetoSB(tags = {"Parametro de Mensagem Wtzap"}, plural = "Parametros de mensagens de Wtzap")
public class ParametroMensagemWtzap extends EntidadeSimplesORM implements ItfTipoParametroMsgWhatsapp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @InfoCampoValorLogico(nomeCalculo = "Descrição", somenteLeitura = false)
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, label = "Ordem")
    private int codigoParametro;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO, label = "Parametro de cabeçalho?")
    @InfoCampoVerdadeiroOuFalso(textoNegativo = "Corpo", textoPositivo = "Cabeçalho")
    private boolean cabecalho;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String tipoParametroWtzap = "text";

    @ManyToOne(targetEntity = TipoMensagemMktWhatsApp.class, optional = false)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = true)
    private TipoMensagemMktWhatsApp tipoMensagem;

    @ManyToOne(targetEntity = TipoDadoCRM.class, optional = true)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @InfoCampoValidadorLogico()
    private TipoDadoCRM tipoDado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @InfoCampoValidadorLogico()
    private String dadoRelativo;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = TipoMensagemMktWhatsApp.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        setTipoMensagem(getParametroInicialEnviado(TipoMensagemMktWhatsApp.class, parametros));
    }

    public boolean isUmaOrigemDadpDinamico() {
        return tipoDado != null;
    }

    public boolean isUmaOrigemCaminhoRelativo() {
        return !UtilCRCStringValidador.isNuloOuEmbranco(dadoRelativo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean isCabecalho() {
        return cabecalho;
    }

    @Override
    public int getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(int codigoParametro) {
        this.codigoParametro = codigoParametro;
    }

    public void setCabecalho(boolean cabecalho) {
        this.cabecalho = cabecalho;
    }

    public TipoMensagemMktWhatsApp getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagemMktWhatsApp tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public TipoDadoCRM getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(TipoDadoCRM tipoDado) {
        this.tipoDado = tipoDado;
    }

    @Override
    public String getTipoParametroWtzap() {
        return tipoParametroWtzap;
    }

    public void setTipoParametroWtzap(String tipoParametroWtzap) {
        this.tipoParametroWtzap = tipoParametroWtzap;
    }

    public String getDadoRelativo() {
        return dadoRelativo;
    }

    public void setDadoRelativo(String dadoRelativo) {
        this.dadoRelativo = dadoRelativo;
    }

}
