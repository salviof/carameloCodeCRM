/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.orcamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.ServicoOferecido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.Casa_Nova.mktFaturamento.api.objetos.orcamento.ItfOrcamentoFaturaMkt;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(plural = "Orçamentos", tags = "Orçamento ", icone = "fa fa-money")
@EntityListeners(ListenerOrcamento.class)
public class Orcamento extends EntidadeORMNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, somenteLeitura = true)
    private Long id;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private UsuarioCRM usuariocriou;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @InfoCampoValorLogico(nomeCalculo = "Descrição orçamentos")
    private String descricao;

    @ManyToOne(targetEntity = Pessoa.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, somenteLeitura = false, obrigatorio = true)
    private Pessoa pessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.MOEDA_REAL, label = "Total", somenteLeitura = true)
    @InfoCampoValorLogico(nomeCalculo = "Valor total")
    private double valorTotal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.MOEDA_REAL, label = "Total mensal", somenteLeitura = true)
    @InfoCampoValorLogico(nomeCalculo = "Valor Mensal")
    private double valorMensalTotal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorMensalComDesconto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL)
    private double porcentagemMensalComDesconto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.MOEDA_REAL)
    private double valorComDesconto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.PERCENTUAL)
    private double porcentagemDesconto;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "itensSazonais")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ServicoOferecido> itensSazonais;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "itensRecorrentes")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ServicoOferecido> itemRecorrente;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ServicoOferecido> servicoOferecido;
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataHoraCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO)
    private Date dataHoraEdicao;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    private UsuarioCRM usuarioEditou;

    @InfoCampoValorLogico(nomeCalculo = "Orçamento Fatura", atualizarSempreQueSalvar = false)
    @Transient
    private ItfOrcamentoFaturaMkt orcamentoFatura;

    public ItfOrcamentoFaturaMkt getOrcamentoFatura() {
        return orcamentoFatura;
    }

    public void setOrcamentoFatura(ItfOrcamentoFaturaMkt orcamentoFatura) {
        this.orcamentoFatura = orcamentoFatura;
    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = Pessoa.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        if (parametros.length > 0) {
            setPessoa((Pessoa) parametros[0]);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ServicoOferecido> getServicoOferecido() {
        return servicoOferecido;
    }

    public void setServicoOferecido(List<ServicoOferecido> servicoOferecido) {
        this.servicoOferecido = servicoOferecido;
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

    public UsuarioCRM getUsuariocriou() {
        return usuariocriou;
    }

    public void setUsuariocriou(UsuarioCRM usuariocriou) {
        this.usuariocriou = usuariocriou;
    }

    public UsuarioCRM getUsuarioEditou() {
        return usuarioEditou;
    }

    public void setUsuarioEditou(UsuarioCRM usuarioEditou) {
        this.usuarioEditou = usuarioEditou;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorMensalTotal() {
        return valorMensalTotal;
    }

    public void setValorMensalTotal(double valorMensalTotal) {
        this.valorMensalTotal = valorMensalTotal;
    }

    public double getValorMensalComDesconto() {
        return valorMensalComDesconto;
    }

    public void setValorMensalComDesconto(double valorMensalComDesconto) {
        this.valorMensalComDesconto = valorMensalComDesconto;
    }

    public double getPorcentagemMensalComDesconto() {
        return porcentagemMensalComDesconto;
    }

    public void setPorcentagemMensalComDesconto(double porcentagemMensalComDesconto) {
        this.porcentagemMensalComDesconto = porcentagemMensalComDesconto;
    }

    public double getValorComDesconto() {
        return valorComDesconto;
    }

    public void setValorComDesconto(double valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
    }

    public double getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(double porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    @Override
    public String getDescritivo() {
        return descricao;
    }

    @Override
    public void setDescritivo(String descricao) {
        this.descricao = descricao;
    }

    public List<ServicoOferecido> getItensSazonais() {
        return itensSazonais;
    }

    public void setItensSazonais(List<ServicoOferecido> itensSazonais) {
        this.itensSazonais = itensSazonais;
    }

    public List<ServicoOferecido> getItemRecorrente() {
        return itemRecorrente;
    }

    public void setItemRecorrente(List<ServicoOferecido> itemRecorrente) {
        this.itemRecorrente = itemRecorrente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
