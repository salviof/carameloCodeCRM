package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValidadorReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorapresencial.ValidadoresReservaHoraPresencial;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario.ValidacaoReservaHorarioInicioReservaAtendente;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHorario;

@ValidadorReservaHoraPresencial(validador = ValidadoresReservaHoraPresencial.INICIORESERVAATENDENTE)
public class ValidacaoReservaHoraPresencialInicioReservaAtendente extends ValidacaoReservaHorarioInicioReservaAtendente {

    public ValidacaoReservaHoraPresencialInicioReservaAtendente(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (getReservaHoraPresencial().getFinalReservaAtendente() != null) {
            if (!getReservaHoraPresencial().getFinalReservaAtendente().after((Date) pValor)) {
                throw new ErroValidacao("A hora inicial precisa ser anterior a hora final");
            }
        }
        return new ArrayList<>();
    }

    public ReservaHorario getReservaHoraPresencial() {
        return getObjetoDoAtributo();
    }
}
