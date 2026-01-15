/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação de chamado", plural = "Solicitações de atividade")
public class SolicitacaoAtividadeEquipe extends Solicitacao {

    @ManyToOne(targetEntity = TipoAtividadeCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private TipoAtividadeCRM tipoAtividade;

    public SolicitacaoAtividadeEquipe() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_ATIVIDADE_EQUIPE.getRegistro());
    }

    public TipoAtividadeCRM getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividadeCRM tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

}
