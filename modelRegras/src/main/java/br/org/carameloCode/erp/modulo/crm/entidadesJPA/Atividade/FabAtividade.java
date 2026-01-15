/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;

/**
 *
 * @author sfurbino
 */
public enum FabAtividade {

    novaAtividade, novaAtividadeNovoProspect;
    private AtividadeCRM atividade = new AtividadeCRM();

    public AtividadeCRM getAtividade(FabTipoAtividadeCRM tipoAtividade, PessoaJuridica prosp) {

        FabAtividade.novaAtividade.getAtividade(FabTipoAtividadeCRM.ATIVAR_CAMPANHA, prosp);

        return null;
    }

    public AtividadeCRM getAtividade(FabTipoAtividadeCRM tipoAtividade) {

        FabAtividade.novaAtividade.getAtividade(FabTipoAtividadeCRM.ATIVAR_CAMPANHA);

        return null;
    }

}
