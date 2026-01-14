/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.CategoriaArquivoEquipe;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoCliente.CategoriaArquivoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(plural = "Modelos de Codumento CRM", tags = "Modelo de Documento CRM", modulo = ERPCrm.NOME_MODULO_ERP)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoModelo")
public class ModeloDocumentoCRM extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    @NotNull
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    @NotNull
    private String descricao;
    @InfoCampo(label = "Extenção")
    private String extencao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ARQUIVO_DE_ENTIDADE)
    @NotNull
    private String documento;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dadosDinamicosDoModelo",
            joinColumns = @JoinColumn(name = "modeloDoc_id"),
            inverseJoinColumns = @JoinColumn(name = "tipoDado_id"))
    private List<TipoDadoCRM> tipoDatoDinamico;
    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoModelo;

    @ManyToOne(targetEntity = CategoriaArquivoCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private CategoriaArquivoCliente categoriaArquivoCliente;

    @ManyToOne(targetEntity = CategoriaArquivoEquipe.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private CategoriaArquivoEquipe categoriaArquivoEquipe;

    @ManyToOne(targetEntity = Pessoa.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private Pessoa leadParaTestes;

    public FabTipoArquivoConhecido getTipoDocumento() {
        return FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(extencao);

    }

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
        if (nome == null) {
            nome = documento;
        }
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getExtencao() {
        return extencao;
    }

    public void setExtencao(String extencao) {
        this.extencao = extencao;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {

        this.documento = documento;
    }

    public List<TipoDadoCRM> getTipoDatoDinamico() {
        return tipoDatoDinamico;
    }

    public String getTipoModelo() {
        return tipoModelo;
    }

    public void setTipoModelo(String tipoModelo) {
        this.tipoModelo = tipoModelo;
    }

    public CategoriaArquivoCliente getCategoriaArquivoCliente() {
        return categoriaArquivoCliente;
    }

    public void setCategoriaArquivoCliente(CategoriaArquivoCliente categoriaArquivoCliente) {
        this.categoriaArquivoCliente = categoriaArquivoCliente;
    }

    public CategoriaArquivoEquipe getCategoriaArquivoEquipe() {
        return categoriaArquivoEquipe;
    }

    public void setCategoriaArquivoEquipe(CategoriaArquivoEquipe categoriaArquivoEquipe) {
        this.categoriaArquivoEquipe = categoriaArquivoEquipe;
    }

    public Pessoa getLeadParaTestes() {
        return leadParaTestes;
    }

    public void setLeadParaTestes(Pessoa leadParaTestes) {
        this.leadParaTestes = leadParaTestes;
    }

}
