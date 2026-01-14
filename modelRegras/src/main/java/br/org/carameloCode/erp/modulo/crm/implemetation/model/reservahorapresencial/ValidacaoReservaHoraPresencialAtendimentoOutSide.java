package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.LocalizacaoPostavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial.ValidadorReservaHorarioEncontroPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial.ValidadoresReservaHorarioEncontroPresencial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;

@ValidadorReservaHorarioEncontroPresencial(validador = ValidadoresReservaHorarioEncontroPresencial.ATENDIMENTOOUTSIDE)
public class ValidacaoReservaHoraPresencialAtendimentoOutSide extends ValidacaoGenerica<ReservaHoraPresencial> {

    public ValidacaoReservaHoraPresencialAtendimentoOutSide(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        boolean valorAnterior = getReservaHorarioEncontroPresencial().isAtendimentoOutSide();

        boolean novoValor = (boolean) pValor;
        boolean valorModificado = valorAnterior != novoValor;
        getReservaHorarioEncontroPresencial().setAtendimentoOutSide(novoValor);
        if (valorModificado) {
            if (!getReservaHorarioEncontroPresencial().isAtendimentoOutSide()) {
                getReservaHorarioEncontroPresencial().setLocalizacao(getReservaHorarioEncontroPresencial().getTipoAgendamento().getComoReservaPresencial().getLocalPadraoReuniao());
                if (getReservaHorarioEncontroPresencial().getLocalizacao() == null) {
                    getReservaHorarioEncontroPresencial().setTextoLocalizacao(getReservaHorarioEncontroPresencial().getTipoAgendamento().getComoReservaPresencial().getTextoLocalizacaoReuniaoInSide());
                }
            } else {
                getReservaHorarioEncontroPresencial().setLocalizacao(new LocalizacaoPostavel());
                getReservaHorarioEncontroPresencial().getLocalizacao().prepararNovoObjeto();
            }
        }
        return null;
    }

    public ReservaHoraPresencial getReservaHorarioEncontroPresencial() {
        return getObjetoDoAtributo();
    }
}
