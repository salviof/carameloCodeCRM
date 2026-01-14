package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.FabTipoWidgetFormulario;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.time.DayOfWeek;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValidadorEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValidadoresEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.disponibilidadeatdmtpublico.ValidacaoDisponibilidadeAtdmtPublicoDiasDaSemana;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValidadorEscopoPesquisaMelhorHorario(validador = ValidadoresEscopoPesquisaMelhorHorario.DIASEMANATERCA)
public class ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaTerca extends ValidacaoGenerica<EscopoPesquisaMelhorHorario> {

    public ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaTerca(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        boolean ativo = (boolean) pValor;
        String diasSemanaAtualizado
                = ValidacaoDisponibilidadeAtdmtPublicoDiasDaSemana
                        .definirValor(getEscopoPesquisaMelhorHorario().getDiasDaSemana(), DayOfWeek.TUESDAY.getValue(), ativo);
        getEscopoPesquisaMelhorHorario().setDiasDaSemana(diasSemanaAtualizado);
        getEscopoPesquisaMelhorHorario().setDiaSemanaTerca(ativo);
        return FabTipoWidgetFormulario.getCampos(getEscopoPesquisaMelhorHorario().getCPinst(CPEscopoPesquisaMelhorHorario.diasdasemana));

    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
        return getObjetoDoAtributo();
    }
}
