/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeORMStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoStatus;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemCor;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemIcone;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sfurbino
 */
@Entity
@InfoObjetoSB(tags = "Status Chamado", plural = "Status de chamados", icone = "fa fa-life-ring", fabricaVinculada = FabStatusChamado.class, modulo = ERPCrm.NOME_MODULO_ERP)
public class StatusChamado extends EntidadeORMStatus implements ComoTemCor, ComoTemIcone, ComoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.STATUS_ENUM)
    private FabStatusChamado fabStatus;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;
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

    public FabStatusChamado getFabStatus() {
        return fabStatus;
    }

    public void setFabStatus(FabStatusChamado fabStatus) {
        this.fabStatus = fabStatus;
    }

    public boolean isModoRascunho() {
        return fabStatus.equals(FabStatusChamado.RASCUNHO);
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

}
