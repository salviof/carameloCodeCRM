/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Infografico de Cx Postal"}, plural = "Infograficos de Cxs Postais")
public class CaixaPostalEstatisticas extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;

    @ManyToOne(targetEntity = CaixaPostal.class)
    private CaixaPostal caixaPostal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    @InfoCampoValorLogico(nomeCalculo = "Quantidade não lida desconhecido")
    private int quantidadeNaoLidoConhecido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    @InfoCampoValorLogico(nomeCalculo = "Quantidade não lida desconhecido")
    private int quantidadeNaoLidoDesconhecido;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    @InfoCampoValorLogico(nomeCalculo = "Quantidade não lida pessoal")
    private int quantidadeNaoLidoPessoal;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = CaixaPostal.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        setCaixaPostal(getParametroInicialEnviado(CaixaPostal.class, parametros));
        descricao = "Dados - " + getCaixaPostal().getEmail();
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

    public CaixaPostal getCaixaPostal() {
        return caixaPostal;
    }

    public void setCaixaPostal(CaixaPostal caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    public int getQuantidadeNaoLidoConhecido() {
        return quantidadeNaoLidoConhecido;
    }

    public void setQuantidadeNaoLidoConhecido(int quantidadeNaoLidoConhecido) {
        this.quantidadeNaoLidoConhecido = quantidadeNaoLidoConhecido;
    }

    public int getQuantidadeNaoLidoDesconhecido() {
        return quantidadeNaoLidoDesconhecido;
    }

    public void setQuantidadeNaoLidoDesconhecido(int quantidadeNaoLidoDesconhecido) {
        this.quantidadeNaoLidoDesconhecido = quantidadeNaoLidoDesconhecido;
    }

    public int getQuantidadeNaoLidoPessoal() {
        return quantidadeNaoLidoPessoal;
    }

    public void setQuantidadeNaoLidoPessoal(int quantidadeNaoLidoPessoal) {
        this.quantidadeNaoLidoPessoal = quantidadeNaoLidoPessoal;
    }

}
