/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoCliente;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado.ComoPasta;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.subpasta.SubpastaCliente;
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
@InfoObjetoSB(plural = "Diretório de arquivos", tags = "Diretório de cliente", icone = "fa fa-folder-o", modulo = ERPCrm.NOME_MODULO_ERP)
public class CategoriaArquivoCliente extends EntidadeSimplesORM implements ComoPasta {

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
    @ManyToOne(targetEntity = CategoriaArquivoCliente.class)
    private CategoriaArquivoCliente pastaPai;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @OneToMany(mappedBy = "pastaPai", targetEntity = CategoriaArquivoCliente.class)
    private List<CategoriaArquivoCliente> subPastas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @OneToMany(mappedBy = "categoriaCliente", targetEntity = SubpastaCliente.class)
    private List<SubpastaCliente> subpastasPrivadas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Tem solicitação para mim?")
    private boolean temSolicitacaoParaMim;

    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String cor;

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

    public CategoriaArquivoCliente getPastaPai() {
        return pastaPai;
    }

    public void setPastaPai(CategoriaArquivoCliente pastaPai) {
        this.pastaPai = pastaPai;
    }

    public void setSubPastas(List<CategoriaArquivoCliente> subPastas) {
        this.subPastas = subPastas;
    }

    public void setSubpastasPrivadas(List<SubpastaCliente> subpastasPrivadas) {
        this.subpastasPrivadas = subpastasPrivadas;
    }

    public boolean isTemSolicitacaoParaMim() {
        return temSolicitacaoParaMim;
    }

    public void setTemSolicitacaoParaMim(boolean temSolicitacaoParaMim) {
        this.temSolicitacaoParaMim = temSolicitacaoParaMim;
    }

    public CategoriaArquivoCliente() {
    }

    public List<CategoriaArquivoCliente> getSubPastas() {
        return subPastas;
    }

    public List<SubpastaCliente> getSubpastasPrivadas() {
        return subpastasPrivadas;
    }

    @Override
    public String getCor() {
        return cor;
    }

    @Override
    public int getQuantidade() {
        return 0;
    }

}
