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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Dados coletados na atividade", tags = {"Dado coletado na atividade"}, modulo = ERPCrm.NOME_MODULO_ERP)
@Entity
public class DadoColetado_Atividade extends EntidadeORMGenerica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @ManyToOne(targetEntity = AtividadeCRM.class)
    private AtividadeCRM atividade;
    @ManyToOne(targetEntity = DadoCRM.class)
    private DadoCRM dadoCrm;

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

    public DadoCRM getDadoCrm() {
        return dadoCrm;
    }

    public void setDadoCrm(DadoCRM dadoCrm) {
        this.dadoCrm = dadoCrm;
    }

}
