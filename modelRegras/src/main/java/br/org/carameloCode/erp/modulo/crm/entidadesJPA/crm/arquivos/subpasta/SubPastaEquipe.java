/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
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
@InfoObjetoSB(tags = "Subpasta privada Equipe", plural = "Subpastas privadas da equipe", modulo = ERPCrm.NOME_MODULO_ERP)
public class SubPastaEquipe extends SubPastaPrivada {

    @ManyToOne(targetEntity = CategoriaArquivoEquipe.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Diretório", somenteLeitura = true)
    private CategoriaArquivoEquipe categoriaEquipe;

    @OneToMany(targetEntity = SubPastaEquipe.class, mappedBy = "pastaPai")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES, label = "Subpastas privadas", somenteLeitura = true)
    private List<SubPastaEquipe> subPastas;

    @Transient
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Diretório ", somenteLeitura = true)
    @InfoCampoValorLogico(nomeCalculo = "Diretório próximo")
    private CategoriaArquivoEquipe diretorioProximo;

    public List<SubPastaEquipe> getSubPastas() {
        return subPastas;
    }

    public CategoriaArquivoEquipe getCategoriaEquipe() {
        return categoriaEquipe;
    }

    public void setCategoriaEquipe(CategoriaArquivoEquipe categoriaEquipe) {
        this.categoriaEquipe = categoriaEquipe;
    }

    public CategoriaArquivoEquipe getDiretorioProximo() {
        return diretorioProximo;
    }

    public void setDiretorioProximo(CategoriaArquivoEquipe diretorioProximo) {
        this.diretorioProximo = diretorioProximo;
    }

}
