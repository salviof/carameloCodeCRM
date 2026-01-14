package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahoraremotovideo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia.ValidadorReservaHorarioVideoConferencia;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorariovideoconferencia.ValidadoresReservaHorarioVideoConferencia;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario.ValidacaoReservaHorarioPessoaRelacionada;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraRemotoVideo;

@ValidadorReservaHorarioVideoConferencia(validador = ValidadoresReservaHorarioVideoConferencia.PESSOARELACIONADA)
public class ValidacaoReservaHoraRemotoVideoPessoaRelacionada extends ValidacaoGenerica<ReservaHoraRemotoVideo> {

    public ValidacaoReservaHoraRemotoVideoPessoaRelacionada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
        validacaoPadrao = new ValidacaoReservaHorarioPessoaRelacionada(pCampo);
    }

    private final ValidacaoReservaHorarioPessoaRelacionada validacaoPadrao;

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return null;
    }

    public ReservaHoraRemotoVideo getReservaHorarioVideoConferencia() {
        return getObjetoDoAtributo();
    }
}
