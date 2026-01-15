/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.ArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente.CategoriaArquivoCliente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Subpasta privada Cliente", plural = "Subpastas privadas do cliente", modulo = ERPCrm.NOME_MODULO_ERP)
public class SubpastaCliente extends SubPastaPrivada {

    @ManyToOne(targetEntity = CategoriaArquivoCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Diretório")
    private CategoriaArquivoCliente categoriaCliente;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Diretório ")
    @InfoCampoValorLogico(nomeCalculo = "Diretório próximo")
    private CategoriaArquivoCliente diretorioProximo;

    @OneToMany(targetEntity = SubpastaCliente.class, mappedBy = "pastaPai")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, label = "Subpastas privadas", somenteLeitura = true)
    private List<SubpastaCliente> subPastas;

    @OneToMany(mappedBy = "subPasta")
    private List<ArquivoCliente> arquivos;

    public List<ArquivoCliente> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<ArquivoCliente> arquivos) {
        this.arquivos = arquivos;
    }

    public CategoriaArquivoCliente getCategoriaCliente() {
        return categoriaCliente;
    }

    public void setCategoriaCliente(CategoriaArquivoCliente categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }

    public CategoriaArquivoCliente getDiretorioProximo() {
        return diretorioProximo;
    }

    public void setDiretorioProximo(CategoriaArquivoCliente diretorioProximo) {
        this.diretorioProximo = diretorioProximo;
    }

    public List<SubpastaCliente> getSubPastas() {
        return subPastas;
    }

}
