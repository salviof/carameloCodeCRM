/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.filtroTipoRelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
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
@InfoObjetoSB(tags = "Filtro", plural = "Filtros de relacionamento")
public class FiltroRelacionamento extends EntidadeSimplesORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @OneToMany(mappedBy = "filtro")
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<CondicaoFiltroRelacionamento> condicoes;

    @ManyToOne(targetEntity = TipoRelacionamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoRelacionamento tipoRelacionamento;

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

    public List<CondicaoFiltroRelacionamento> getCondicoes() {
        return condicoes;
    }

    public void setCondicoes(List<CondicaoFiltroRelacionamento> condicoes) {
        this.condicoes = condicoes;
    }

    public TipoRelacionamento getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

}
