/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(plural = "Dados não coletados", tags = {"Dado não coletado"}, modulo = ERPCrm.NOME_MODULO_ERP)
public class DadoNaoColetado_Atividade extends EntidadeORMGenerica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @ManyToOne(targetEntity = AtividadeCRM.class, cascade = {CascadeType.ALL})
    private AtividadeCRM atividade;
    @ManyToOne(targetEntity = AtividadeCRM.class, cascade = {CascadeType.ALL})
    private DadoCRM dadoCRM;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeCRM atividade) {
        this.atividade = atividade;
    }

    public DadoCRM getDadoCRM() {
        return dadoCRM;
    }

    public void setDadoCRM(DadoCRM dadoCRM) {
        this.dadoCRM = dadoCRM;
    }

}
