/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.List;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Documento da Categoria", plural = "Documentos cliente da categoria")
public class DocsEquipeDaCategoria extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private CategoriaArquivoEquipe categoria;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<ArquivoAnexado> arquivoAnexado;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private int quantidade;

    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = CategoriaArquivoEquipe.class)
    public void preparacaoObjeto(CategoriaArquivoEquipe pCategoria) {
        categoria = pCategoria;
        nome = pCategoria.getNome();
        icone = pCategoria.getIcone();
        id = pCategoria.getId();

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

    public List<ArquivoAnexado> getArquivoAnexado() {
        return arquivoAnexado;
    }

    public void setArquivoAnexado(List<ArquivoAnexado> arquivoAnexado) {
        this.arquivoAnexado = arquivoAnexado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public CategoriaArquivoEquipe getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaArquivoEquipe categoria) {
        this.categoria = categoria;
    }

    public String getIcone() {
        if (UtilCRCStringValidador.isNuloOuEmbranco(icone)) {
            return "fa fa-folder-o";
        }
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

}
