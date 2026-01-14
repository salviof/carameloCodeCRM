package br.org.carameloCode.erp.modulo.crm.implemetation.model.disponibilidadeatdmtpublico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.time.DayOfWeek;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValidadorDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValidadoresDisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;

@ValidadorDisponibilidadeAtdmtPublico(validador = ValidadoresDisponibilidadeAtdmtPublico.DIASEMANADOMINGO)
public class ValidacaoDisponibilidadeAtdmtPublicoDiaSemanaDomingo extends ValidacaoGenerica<DisponibilidadeAtdmtPublico> {

    public ValidacaoDisponibilidadeAtdmtPublicoDiaSemanaDomingo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        boolean ativo = (boolean) pValor;
        String diasSemanaAtualizado
                = ValidacaoDisponibilidadeAtdmtPublicoDiasDaSemana.definirValor(getDisponibilidadeAtdmtPublico().getDiasDaSemana(), DayOfWeek.SUNDAY.getValue(), ativo);
        getDisponibilidadeAtdmtPublico().setDiasDaSemana(diasSemanaAtualizado);
        getDisponibilidadeAtdmtPublico().setDiaSemanaDomingo(ativo);
        return null;

    }

    public DisponibilidadeAtdmtPublico getDisponibilidadeAtdmtPublico() {
        return getObjetoDoAtributo();
    }
}
