/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.telas.compomente;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = {"Tipo comomnente Visual"}, plural = "Tipos Componente Visual")
public class TipoComponentVisual extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @OneToMany(cascade = CascadeType.REMOVE, targetEntity = ParametroComponente.class)
    private List<ParametroComponente> parametros;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomeComponente;
    private String codigoFonte;
    private String javaScript;
    private String css;

    public void criarParametros() {
        List<String> prEncontrados = new ArrayList<>();
        prEncontrados.addAll(UtilCRCStringValidador.getListaStringEntreCaracter(codigoFonte, UtilCRCStringValidador.TIPO_SINALIZADOR.COLCHETE));
        prEncontrados.addAll(UtilCRCStringValidador.getListaStringEntreCaracter(javaScript, UtilCRCStringValidador.TIPO_SINALIZADOR.COLCHETE));
        prEncontrados.addAll(UtilCRCStringValidador.getListaStringEntreCaracter(css, UtilCRCStringValidador.TIPO_SINALIZADOR.COLCHETE));
        parametros = new ArrayList<>();
        for (String pr : prEncontrados) {

        }

    }

    public void geraWidgetWordPress(String Diretorio) {

    }

    public void geraWidgetWordPress() {

    }

    public void geraComponenteJSF() {

    }

    public List<ParametroComponente> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroComponente> parametros) {
        this.parametros = parametros;
    }

    public String getNomeComponente() {
        return nomeComponente;
    }

    public void setNomeComponente(String nomeComponente) {
        this.nomeComponente = nomeComponente;
    }

    public String getCodigoFonte() {
        return codigoFonte;
    }

    public void setCodigoFonte(String codigoFonte) {
        this.codigoFonte = codigoFonte;
    }

    public String getJavaScript() {
        return javaScript;
    }

    public void setJavaScript(String javaScript) {
        this.javaScript = javaScript;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

}
