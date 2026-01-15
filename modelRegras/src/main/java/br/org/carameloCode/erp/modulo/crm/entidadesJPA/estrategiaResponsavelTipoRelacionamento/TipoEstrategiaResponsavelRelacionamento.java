/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.estrategiaResponsavelTipoRelacionamento;

import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMNormal;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeVinculadoAEnum;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salviofurbino
 * @since 02/05/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(plural = "Estratégias de responsabilidade", tags = "Estrategia de responsabilidade", icone = "fa fa-map-signs")
public class TipoEstrategiaResponsavelRelacionamento extends EntidadeORMNormal implements ComoEntidadeVinculadoAEnum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.URL)
    private String url;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String header;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String regexResultadoEsperado;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataHoraCriou;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO)
    private Date dataHoraEditou;
    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private UsuarioSB usuarioCriou;
    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    private UsuarioSB usuarioEditou;

    @Override
    @InfoPreparacaoObjeto()
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros); //chamada super do metodo (implementação classe pai)
        tipoEstrategia = FabTipoEstrategiaResponsalvelRelacionamento.API_WEB_SERVICE_PERSONALIZADO;
    }

    @Enumerated(EnumType.STRING)
    private FabTipoEstrategiaResponsalvelRelacionamento tipoEstrategia;

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        tipoEstrategia = (FabTipoEstrategiaResponsalvelRelacionamento) pFabrica;
    }

    @Override
    public FabTipoEstrategiaResponsalvelRelacionamento getEnumVinculado() {
        return tipoEstrategia;
    }

    public boolean isUmaEstrategiaPersonalizadaWS() {
        return tipoEstrategia.equals(FabTipoEstrategiaResponsalvelRelacionamento.API_WEB_SERVICE_PERSONALIZADO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getRegexResultadoEsperado() {
        return regexResultadoEsperado;
    }

    public void setRegexResultadoEsperado(String regexResultadoEsperado) {
        this.regexResultadoEsperado = regexResultadoEsperado;
    }

    public Date getDataHoraCriou() {
        return dataHoraCriou;
    }

    public void setDataHoraCriou(Date dataHoraCriou) {
        this.dataHoraCriou = dataHoraCriou;
    }

    public Date getDataHoraEditou() {
        return dataHoraEditou;
    }

    public void setDataHoraEditou(Date dataHoraEditou) {
        this.dataHoraEditou = dataHoraEditou;
    }

    public UsuarioSB getUsuarioCriou() {
        return usuarioCriou;
    }

    public void setUsuarioCriou(UsuarioSB usuarioCriou) {
        this.usuarioCriou = usuarioCriou;
    }

    public UsuarioSB getUsuarioEditou() {
        return usuarioEditou;
    }

    public void setUsuarioEditou(UsuarioSB usuarioEditou) {
        this.usuarioEditou = usuarioEditou;
    }

    public FabTipoEstrategiaResponsalvelRelacionamento getTipoEstrategia() {
        return tipoEstrategia;
    }

    public void setTipoEstrategia(FabTipoEstrategiaResponsalvelRelacionamento tipoEstrategia) {
        this.tipoEstrategia = tipoEstrategia;
    }

}
