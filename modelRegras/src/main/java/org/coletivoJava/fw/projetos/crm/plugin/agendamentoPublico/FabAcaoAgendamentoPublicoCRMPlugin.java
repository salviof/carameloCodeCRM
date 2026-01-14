/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coletivoJava.fw.projetos.crm.plugin.agendamentoPublico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

/**
 *
 * @author sfurbino
 */
@InfoModulosCRM(modulo = FabModulosCRM.PLUGIN)
public enum FabAcaoAgendamentoPublicoCRMPlugin implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = EscopoPesquisaMelhorHorario.class, icone = "fa fa-ticket")
    AGENDAMENTO_PUBLICO_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-video-camera")
    AGENDAMENTO_PUBLICO_FRM_ATENDER_REMOTO,
    @InfoTipoAcaoFormulario(icone = "fa fa-handshake-o")
    AGENDAMENTO_PUBLICO_FRM_ATENDER_NO_LOCAL,;

}
