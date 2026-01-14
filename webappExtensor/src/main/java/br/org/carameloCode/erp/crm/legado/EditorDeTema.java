/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.legado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author SalvioF
 */
public class EditorDeTema extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    private Properties propriedades;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private final String arquivoConfig;

    private String campoSelecionado;
    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String corCampoSelecionado;

    private boolean atualizarTodosMesmaCor;

    public EditorDeTema(String pNomeTema) {
        arquivoConfig = SBWebPaginas.getCaminhoWebResourcesDeveloper()
                + "/primefaces-" + pNomeTema + "/less/variables/theme-variables.less";
        try {
            propriedades = new Properties();
            propriedades.load(UTilSBCoreInputs.getStreamByLocalFile(arquivoConfig));

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro lendo variaveis", t);

        }

    }

    public String getCampoSelecionado() {
        return campoSelecionado;
    }

    public void setCampoSelecionado(String campoSelecionado) {
        this.campoSelecionado = campoSelecionado;
    }

    public void aplicarCorEmCampoSelecionado(String pCampoSelecionado) {
        campoSelecionado = pCampoSelecionado;
        setCorCampoSelecionado(corCampoSelecionado);
    }

    public void aplicarCor() {
        aplicarCorEmCampoSelecionado(campoSelecionado);
    }

    public String getCorCampoSelecionado() {
        return corCampoSelecionado;
    }

    public void setCorCampoSelecionado(String corCampoSelecionado) {
        this.corCampoSelecionado = corCampoSelecionado;
        if (campoSelecionado != null) {
            propriedades.put(campoSelecionado, "#" + corCampoSelecionado);
        }
    }

    public String getCorDaPropriedade(String pPropriedade) {
        return (String) propriedades.get(pPropriedade);
    }

    public void setCorDaPropriedade(String pPropriedade, String pValor) {
        propriedades.put(pPropriedade, pValor);
    }

    public List<String> getPropriedades() {

        List<String> lista = new ArrayList<>();
        Enumeration nomesProp = propriedades.propertyNames();
        while (nomesProp.hasMoreElements()) {
            String nextElement = (String) nomesProp.nextElement();
            lista.add(nextElement);

        }

        return lista;

    }

    public void salvar() {
        File arquivoConfigRuntime = new File(arquivoConfig);
        try {
            OutputStream out = new FileOutputStream(arquivoConfigRuntime);
            propriedades.store(out, "");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao salvar as prpriedades", t);
        }

    }

    public boolean isAtualizarTodosMesmaCor() {
        return atualizarTodosMesmaCor;
    }

    public void setAtualizarTodosMesmaCor(boolean atualizarTodosMesmaCor) {
        this.atualizarTodosMesmaCor = atualizarTodosMesmaCor;
    }

}
