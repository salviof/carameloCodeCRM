package br.org.carameloCode.erp.modulo.crm.implemetation.model.disponibilidadeatdmtpublico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValidadorDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValidadoresDisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;

@ValidadorDisponibilidadeAtdmtPublico(validador = ValidadoresDisponibilidadeAtdmtPublico.DIASDASEMANA)
public class ValidacaoDisponibilidadeAtdmtPublicoDiasDaSemana extends ValidacaoGenerica<DisponibilidadeAtdmtPublico> {

    public static String definirValor(String pValorAnterior, int integerDiaDaSemana, boolean valor) {
        StringBuilder valorBuilder = new StringBuilder(pValorAnterior);
        char valorStr;
        if (valor) {
            valorStr = '1';
        } else {
            valorStr = '0';
        }
        valorBuilder.setCharAt(integerDiaDaSemana - 1, valorStr);
        return valorBuilder.toString();
    }

    public ValidacaoDisponibilidadeAtdmtPublicoDiasDaSemana(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (((String) pValor).isEmpty() || ((String) pValor).length() != 7) {
            throw new ErroValidacao("Dias de semana inválidos");
        }
        return new ArrayList<>();
    }

    public DisponibilidadeAtdmtPublico getDisponibilidadeAtdmtPublico() {
        return getObjetoDoAtributo();
    }
}
