/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.historicoRelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salviofurbino
 * @since 06/09/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(tags = {"Histórico Relacionamento"}, plural = "Histórico de mudanças de Relacionamento")
@EntityListeners(ListenerEntidadePadrao.class)
public class HistoricoRelacionamento extends EntidadeORMNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Realizado em:")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHora;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @InfoCampoValorLogico(nomeCalculo = "Descrição")
    private String descricao;

    @ManyToOne(targetEntity = AtividadeCRM.class, optional = true)
    private AtividadeCRM atividadeReferencia;

    @ManyToOne(targetEntity = Pessoa.class, optional = false)
    private Pessoa prospecto;

    @ManyToOne(targetEntity = TipoRelacionamento.class)
    private TipoRelacionamento relacionamentoAnterior;
    @ManyToOne(targetEntity = TipoRelacionamento.class)
    private TipoRelacionamento novoRelacionamento;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, label = "Realizado em:")
    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuario;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = PessoaJuridica.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros); //chamada super do metodo (implementação classe pai)
        //      atividadeReferencia = getParametroInicialEnviado(AtividadeCRM.class, parametros);
        prospecto = getParametroInicialEnviado(PessoaJuridica.class, parametros);
        relacionamentoAnterior = prospecto.getRelacionamento();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public AtividadeCRM getAtividadeReferencia() {
        return atividadeReferencia;
    }

    public void setAtividadeReferencia(AtividadeCRM atividadeReferencia) {
        this.atividadeReferencia = atividadeReferencia;
    }

    public TipoRelacionamento getRelacionamentoAnterior() {
        return relacionamentoAnterior;
    }

    public void setRelacionamentoAnterior(TipoRelacionamento relacionamentoAnterior) {
        this.relacionamentoAnterior = relacionamentoAnterior;
    }

    public TipoRelacionamento getNovoRelacionamento() {
        return novoRelacionamento;
    }

    public void setNovoRelacionamento(TipoRelacionamento novoRelacionamento) {
        this.novoRelacionamento = novoRelacionamento;
    }

    public UsuarioCRM getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCRM usuario) {
        this.usuario = usuario;
    }

    public String getDescritivo() {
        descricao = String.valueOf(getId());
        return descricao;
    }

    public Pessoa getProspecto() {
        return prospecto;
    }

    public void setProspecto(PessoaJuridica prospecto) {
        this.prospecto = prospecto;
    }

}
