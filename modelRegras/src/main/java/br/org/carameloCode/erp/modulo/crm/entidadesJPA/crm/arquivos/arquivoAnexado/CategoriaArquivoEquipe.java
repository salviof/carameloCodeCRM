/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.subpasta.SubPastaEquipe;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Diretórios de arquivo Interno", tags = "Diretório de arquivos internos", icone = "fa fa-folder-o", modulo = ERPCrm.NOME_MODULO_ERP)
public class CategoriaArquivoEquipe extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean compartilharComConvidados;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = CategoriaArquivoEquipe.class)
    private CategoriaArquivoEquipe pastaPai;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @OneToMany(mappedBy = "pastaPai", targetEntity = CategoriaArquivoEquipe.class)
    private List<CategoriaArquivoEquipe> subPastas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @OneToMany(mappedBy = "categoriaEquipe", targetEntity = SubPastaEquipe.class)
    private List<SubPastaEquipe> subpastasPrivadas;

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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public boolean isCompartilharComConvidados() {
        return compartilharComConvidados;
    }

    public void setCompartilharComConvidados(boolean compartilharComConvidados) {
        this.compartilharComConvidados = compartilharComConvidados;
    }

    public CategoriaArquivoEquipe getPastaPai() {
        return pastaPai;
    }

    public void setPastaPai(CategoriaArquivoEquipe pastaPai) {
        this.pastaPai = pastaPai;
    }

    public List<CategoriaArquivoEquipe> getSubPastas() {
        return subPastas;
    }

    public void setSubPastas(List<CategoriaArquivoEquipe> subPastas) {
        this.subPastas = subPastas;
    }

    public List<SubPastaEquipe> getSubpastasPrivadas() {
        return subpastasPrivadas;
    }

    public void setSubpastasPrivadas(List<SubPastaEquipe> subpastasPrivadas) {
        this.subpastasPrivadas = subpastasPrivadas;
    }

}
