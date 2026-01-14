package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValidadorReservaHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorario.ValidadoresReservaHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValidadorReservaHorario(validador = ValidadoresReservaHorario.FINALRESERVAATENDENTE)
public class ValidacaoReservaHorarioFinalReservaAtendente extends ValidacaoGenerica<ReservaHoraPresencial> {

    public ValidacaoReservaHorarioFinalReservaAtendente(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (getReservaHoraPresencial().getInicioReservaAtendente() != null) {
            if (!getReservaHoraPresencial().getInicioReservaAtendente().before((Date) pValor)) {
                throw new ErroValidacao("A hora inicial precisa ser anterior a hora final");
            }
        }
        return new ArrayList<>();
    }

    public ReservaHorario getReservaHoraPresencial() {
        return getObjetoDoAtributo();
    }
}
