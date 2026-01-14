/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Arquivo de cliente", plural = "Arquivos de cliente", modulo = ERPCrm.NOME_MODULO_ERP)
public class ArquivoCliente extends ArquivoAnexado {

    @ManyToOne(fetch = FetchType.LAZY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, obrigatorio = true)
    private CategoriaArquivoCliente categoriaArqCli;

    public CategoriaArquivoCliente getCategoriaArqCli() {
        return categoriaArqCli;
    }

    public void setCategoriaArqCli(CategoriaArquivoCliente categoriaArqCli) {
        this.categoriaArqCli = categoriaArqCli;
    }

}
