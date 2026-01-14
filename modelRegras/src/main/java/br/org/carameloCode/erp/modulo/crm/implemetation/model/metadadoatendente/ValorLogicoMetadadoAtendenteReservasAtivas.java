package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadoatendente;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.MetadadoAtendente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValorLogicoMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValoresLogicosMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.CPReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.FabStatusReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValorLogicoMetadadoAtendente(calculo = ValoresLogicosMetadadoAtendente.RESERVASATIVAS)
public class ValorLogicoMetadadoAtendenteReservasAtivas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoAtendenteReservasAtivas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getMetadado().getUsuario() != null) {
            if (!isCacheAtivado()) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();

                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ReservaHorario.class, em);
                consulta.addCondicaoManyToOneIgualA(CPReservaHorario.atendenteresponsavel, getMetadado().getUsuario());
                consulta.addCondicaoDataHoraMaiorOuIgualA(CPReservaHorario.inicioreservaatendente, UtilCRCDataHora.decrementarDias(new Date(), 1));
                consulta.addCondicaoManyToOneContemNoIntervalo(CPReservaHorario.status,
                        Lists.newArrayList(FabStatusReservaHorario.AGENDADO.getRegistro(), FabStatusReservaHorario.CONFIRMADO.getRegistro())
                );
                getMetadado().setReservasAtivas(consulta.resultadoSomarQuantidade());
                UtilSBPersistencia.fecharEM(em);
                ativarCache(30);
            }
        }
        return getMetadado().getReservasAtivas();
    }

    public MetadadoAtendente getMetadado() {
        return (MetadadoAtendente) getCampoInst().getObjetoDoAtributo();
    }
}
