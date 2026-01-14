/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.transitorio;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.DadosProspectoGoogle;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SalvioF
 */
@InfoObjetoSB(plural = "Pesquisas", tags = {"Dados pesquisa"})
public class DadosPesquisaGooglePlace extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String termo = "";
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String local = "Belo Horizonte";
    private String pageToken;

    private OrigemProspecto origemSelecionada;

    public List<DadosProspectoGoogle> dadosEncontrados;

    public String getTermo() {
        return termo;
    }

    public void adicionarDado(DadosProspectoGoogle dado) {
        if (dadosEncontrados == null) {
            dadosEncontrados = new ArrayList<>();
        }
        dadosEncontrados.add(dado);
    }

    public void setTermo(String pTempor) {
        if (pTempor == null) {
            pTempor = "";
            return;
        }
        if (!pTempor.equals(termo)) {
            dadosEncontrados = new ArrayList<>();
            pageToken = null;
        }
        this.termo = pTempor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTemPageToken() {
        return pageToken != null;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public OrigemProspecto getOrigemSelecionada() {
        return origemSelecionada;
    }

    public void setOrigemSelecionada(OrigemProspecto origemSelecionada) {
        this.origemSelecionada = origemSelecionada;
    }

    public List<DadosProspectoGoogle> getDadosEncontrados() {
        if (dadosEncontrados == null) {
            dadosEncontrados = new ArrayList<>();
        }
        return dadosEncontrados;
    }

}
