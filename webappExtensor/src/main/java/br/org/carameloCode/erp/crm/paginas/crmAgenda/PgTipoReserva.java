/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAgenda;

import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.reflexao.anotacoes.InfoPagina;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.FabAcaoAgendaMentoPublico;
import br.org.carameloCode.erp.modulo.agenda.regradeNegocio.disponibilidades.InfoAcaoAgendamentoPublico;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.tipoAgendamentoPublico.TipoAgendamentoAtdmPublico;

/**
 *
 * @author sfurbino
 */
@Named
@InfoPagina(nomeCurto = "Tipo de reserva", tags = {"Tipos de Reservas"})
@InfoAcaoAgendamentoPublico(acao = FabAcaoAgendaMentoPublico.TIPO_RESERVA_MB)
public class PgTipoReserva extends MB_paginaCadastroEntidades<TipoAgendamentoAtdmPublico> {

    @PostConstruct
    public void inicio() {
        if (getAcaoSelecionada() == null) {
            executaAcaoSelecionadaPorEnum(FabAcaoAgendaMentoPublico.TIPO_RESERVA_FRM_LISTAR);
        }
    }
}
