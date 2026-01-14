package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValorLogicoEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValoresLogicosEscopoPesqHorarioPublicado;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoEscopoPesqHorarioPublicado(calculo = ValoresLogicosEscopoPesqHorarioPublicado.QTDRESERVASREALIZADAS)
public class ValorLogicoEscopoPesqHorarioPublicadoQtdReservasRealizadas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEscopoPesqHorarioPublicadoQtdReservasRealizadas(
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
            UtilSBPersistencia.fecharEM(em);
        }
        return getEscopo().getQtdMaximoReservas();
    }

    public EscopoPesqHorarioPublicado getEscopo() {
        return (EscopoPesqHorarioPublicado) getCampoInst().getObjetoDoAtributo();
    }

}
