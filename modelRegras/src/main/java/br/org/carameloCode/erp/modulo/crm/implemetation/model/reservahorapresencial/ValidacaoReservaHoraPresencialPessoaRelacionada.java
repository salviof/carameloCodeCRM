package br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorapresencial;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial.ValidadorReservaHorarioEncontroPresencial;
import br.org.carameloCode.erp.modulo.crm.api.model.reservahorarioencontropresencial.ValidadoresReservaHorarioEncontroPresencial;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.reservahorario.ValidacaoReservaHorarioPessoaRelacionada;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.reserva.ReservaHoraPresencial;

@ValidadorReservaHorarioEncontroPresencial(validador = ValidadoresReservaHorarioEncontroPresencial.PESSOARELACIONADA)
public class ValidacaoReservaHoraPresencialPessoaRelacionada extends ValidacaoGenerica<ReservaHoraPresencial> {

    public ValidacaoReservaHoraPresencialPessoaRelacionada(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
        validacaoPadrao = new ValidacaoReservaHorarioPessoaRelacionada(pCampo);
    }

    private final ValidacaoReservaHorarioPessoaRelacionada validacaoPadrao;

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return null;
    }

    public ReservaHoraPresencial getReservaHorarioEncontroPresencial() {
        return getObjetoDoAtributo();
    }
}
