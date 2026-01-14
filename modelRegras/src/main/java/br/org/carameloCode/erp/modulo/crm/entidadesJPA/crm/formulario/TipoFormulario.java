/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.resposta.RespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.TipoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import br.org.carameloCode.erp.modulo.crm.api.model.respostaformulario.CPRespostaFormulario;
import org.hibernate.annotations.Formula;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Tipo Formulário", plural = "Tipos de formulário")
public class TipoFormulario
        extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampoValorLogico(nomeCalculo = "nome", somenteLeitura = true)
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL, somenteLeitura = true)
    private String urlPublica;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, somenteLeitura = true)
    @Column(unique = true)
    private String codigoTypebot;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL, somenteLeitura = true)
    private String urlServicoTypebot;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean integrarDados = true;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean reprocessarQuandoHouverErroDados = false;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean notificarAtendente;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioPadrao;

    @ManyToOne(targetEntity = OrigemProspecto.class)
    private OrigemProspecto origemPadrao;

    @ManyToOne(targetEntity = TipoEmpresa.class)
    private TipoEmpresa tipoEmpresa;

    @OneToMany(mappedBy = CPRespostaFormulario.tipoformulario)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, somenteLeitura = true)
    private List<RespostaFormulario> respostas;

    @Formula(
            "(SELECT GROUP_CONCAT(CONCAT('[', r.codigoResposta, ']')) "
            + " FROM RespostaFormulario r "
            + " WHERE r.tipoFormulario_id = id "
            + "   AND r.dadosProcessadosSucesso = 1 "
            + " ORDER BY r.id DESC "
            + " LIMIT 50)"
    )
    @Column(columnDefinition = "varchar(4000)")
    private String ultimas50RespCodigos;

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

    public String getUrlPublica() {
        return urlPublica;
    }

    public void setUrlPublica(String urlPublica) {
        this.urlPublica = urlPublica;
    }

    public String getCodigoTypebot() {
        return codigoTypebot;
    }

    public void setCodigoTypebot(String codigoFormularioTypebot) {
        this.codigoTypebot = codigoFormularioTypebot;
    }

    public String getUrlServicoTypebot() {
        return urlServicoTypebot;
    }

    public void setUrlServicoTypebot(String urlServicoTypebot) {
        this.urlServicoTypebot = urlServicoTypebot;
    }

    public boolean isIntegrarDados() {
        return integrarDados;
    }

    public void setIntegrarDados(boolean integrarDados) {
        this.integrarDados = integrarDados;
    }

    public boolean isReprocessarQuandoHouverErroDados() {
        return reprocessarQuandoHouverErroDados;
    }

    public void setReprocessarQuandoHouverErroDados(boolean reprocessarQuandoHouverErroDados) {
        this.reprocessarQuandoHouverErroDados = reprocessarQuandoHouverErroDados;
    }

    public List<RespostaFormulario> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaFormulario> respostas) {
        this.respostas = respostas;
    }

    public String getUltimas50RespCodigos() {
        return ultimas50RespCodigos;
    }

    public void setUltimas50RespCodigos(String ultimas50RespCodigos) {
        this.ultimas50RespCodigos = ultimas50RespCodigos;
    }

    public boolean isNotificarAtendente() {
        return notificarAtendente;
    }

    public void setNotificarAtendente(boolean notificarAtendente) {
        this.notificarAtendente = notificarAtendente;
    }

    public UsuarioSB getUsuarioPadrao() {
        return usuarioPadrao;
    }

    public void setUsuarioPadrao(UsuarioSB usuarioPadrao) {
        this.usuarioPadrao = usuarioPadrao;
    }

    public OrigemProspecto getOrigemPadrao() {
        return origemPadrao;
    }

    public void setOrigemPadrao(OrigemProspecto origemPadrao) {
        this.origemPadrao = origemPadrao;
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

}
