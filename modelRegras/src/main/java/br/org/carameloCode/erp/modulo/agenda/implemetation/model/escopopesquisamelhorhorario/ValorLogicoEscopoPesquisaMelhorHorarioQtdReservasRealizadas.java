package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValorLogicoEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValoresLogicosEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.reserva.ReservaHorario;

@ValorLogicoEscopoPesquisaMelhorHorario(calculo = ValoresLogicosEscopoPesquisaMelhorHorario.QTDRESERVASREALIZADAS)
public class ValorLogicoEscopoPesquisaMelhorHorarioQtdReservasRealizadas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesquisaMelhorHorarioQtdReservasRealizadas(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEscopo().getQtdReservasRealizadas() == 0) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            ConsultaDinamicaDeEntidade consultaReserva = new ConsultaDinamicaDeEntidade(ReservaHorario.class, em);
            consultaReserva.addCondicaoManyToManyContendoObjeto("escopoOrigem", getEscopo());
            Long resultado = consultaReserva.resultadoSomarQuantidade();
            getEscopo().setQtdMaximoReservas(resultado.intValue());
        }
        return getEscopo().getQtdMaximoReservas();
    }

    public EscopoPesquisaMelhorHorario getEscopo() {
        return (EscopoPesquisaMelhorHorario) getCampoInst().getObjetoDoAtributo();
    }
}
