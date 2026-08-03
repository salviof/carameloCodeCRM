/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubPastaEquipe;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Diretórios de arquivo Interno", tags = "Diretório de arquivos internos", icone = "fa fa-folder-o", modulo = ERPCrm.NOME_MODULO_ERP)
public class CategoriaArquivoEquipe extends EntidadeSimplesORM implements ComoPasta {

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

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Tem solicitação para mim?")
    private boolean temSolicitacaoParaMim;

    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String cor;

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

    @Override
    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    @Override
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

    public void setSubPastas(List<CategoriaArquivoEquipe> subPastas) {
        this.subPastas = subPastas;
    }

    public List<SubPastaEquipe> getSubpastasPrivadas() {
        return subpastasPrivadas;
    }

    public void setSubpastasPrivadas(List<SubPastaEquipe> subpastasPrivadas) {
        this.subpastasPrivadas = subpastasPrivadas;
    }

    @Override
    public boolean isTemSolicitacaoParaMim() {
        return temSolicitacaoParaMim;
    }

    public void setTemSolicitacaoParaMim(boolean temSolicitacaoParaMim) {
        this.temSolicitacaoParaMim = temSolicitacaoParaMim;
    }

    @Override
    public String getCor() {
        return cor;
    }

    public List<CategoriaArquivoEquipe> getSubPastas() {
        return subPastas;
    }

    @Override
    public int getQuantidade() {
        return 0;
    }

    @Override
    public boolean isPastaDoCliente() {
        return false;
    }

}
