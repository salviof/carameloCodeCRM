/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.resposta;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Formulário", plural = "Formulários", icone = "fa  fa-clipboard")
public class RespostaFormulario extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @ManyToOne(targetEntity = Pessoa.class)
    private Pessoa pessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @Column(unique = true)
    private String codigoResposta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    @Column(length = 8000, columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String jsonResposta;

    @ManyToOne(targetEntity = UsuarioCRM.class)
    private UsuarioCRM usuarioResponsavelAtendimento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @ManyToOne(targetEntity = TipoFormulario.class, optional = false)
    private TipoFormulario tipoFormulario;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean pessoaExistente;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean dadosProcessadosSucesso;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean atendenteNotificado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraUltimoProcessamento;

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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getJsonResposta() {
        return jsonResposta;
    }

    public void setJsonResposta(String jsonRespota) {
        this.jsonResposta = jsonRespota;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(String codigoResposta) {
        this.codigoResposta = codigoResposta;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public boolean isPessoaExistente() {
        return pessoaExistente;
    }

    public void setPessoaExistente(boolean pessoaExistente) {
        this.pessoaExistente = pessoaExistente;
    }

    public UsuarioCRM getUsuarioResponsavelAtendimento() {
        return usuarioResponsavelAtendimento;
    }

    public void setUsuarioResponsavelAtendimento(UsuarioCRM usuarioResponsavelAtendimento) {
        this.usuarioResponsavelAtendimento = usuarioResponsavelAtendimento;
    }

    public boolean isDadosProcessadosSucesso() {
        return dadosProcessadosSucesso;
    }

    public void setDadosProcessadosSucesso(boolean dadosProcessadosSucesso) {
        this.dadosProcessadosSucesso = dadosProcessadosSucesso;
    }

    public Date getDataHoraUltimoProcessamento() {
        return dataHoraUltimoProcessamento;
    }

    public void setDataHoraUltimoProcessamento(Date dataHoraUltimoProcessamento) {
        this.dataHoraUltimoProcessamento = dataHoraUltimoProcessamento;
    }

    public boolean isAtendenteNotificado() {
        return atendenteNotificado;
    }

    public void setAtendenteNotificado(boolean atendenteNotificado) {
        this.atendenteNotificado = atendenteNotificado;
    }

}
