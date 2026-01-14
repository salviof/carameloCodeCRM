/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.CPTipoDadoCRM;

/**
 *
 * @author sfurbino
 * @since 27/12/2019
 * @version 1.0
 */
@InfoObjetoSB(plural = "Grupos de Dados", tags = "Grupo de dados")
@Entity
public class GrupoTipoDadoCrm extends EntidadeSimplesORM {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID, somenteLeitura = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = CPTipoDadoCRM.grupotipodadodinamico)
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, label = "Tipos de dados")
    private List<TipoDadoCRM> tiposDados;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

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

    public List<TipoDadoCRM> getTiposDados() {
        return tiposDados;
    }

    public void setTiposDados(List<TipoDadoCRM> tiposDados) {
        this.tiposDados = tiposDados;
    }

}
