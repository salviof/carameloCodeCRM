/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.historicoRelacionamento.HistoricoRelacionamento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author salviofurbino
 * @since 07/09/2019
 * @version 1.0
 */
public class ProspectoRepositorio {

    public static List<HistoricoRelacionamento> getUltimasAlteracoesProspectos() {
        List<HistoricoRelacionamento> resposta
                = UtilSBPersistencia.getListaRegistrosByHQL(" from HistoricoRelacionamento where atividadeReferencia_id!=null order by dataHora ", 200,
                        CarameloCode.getServicoRepositorioEntidade().getAcessoDadosDoContexto().getEntitiManager());
        Collections.reverse(resposta);
        return resposta;
    }

}
