/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.config;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade.FabTipoExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoFabricaExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoIA;

/**
 *
 * @author salvio
 */
public class ServicoInteligenciaArtificialCRM implements ComoServicoIA {

    @Override
    public ComoFabricaExecucaoIA getFabricaExecucaoPorEntidade(ComoEntidadeSimples pEntidade) {
        if (pEntidade instanceof ModeloEmailMarketing) {
            return FabTipoExecucaoIA.EMAIL_MARKETING;
        }
        if (pEntidade instanceof ModeloEmail) {
            return FabTipoExecucaoIA.EMAIL_TRANSACIONAL;
        }
        if (pEntidade instanceof AtividadeCRM) {
            return FabTipoExecucaoIA.EXECUCAO_ATIVIDADE;
        }

        return null;
    }

}
