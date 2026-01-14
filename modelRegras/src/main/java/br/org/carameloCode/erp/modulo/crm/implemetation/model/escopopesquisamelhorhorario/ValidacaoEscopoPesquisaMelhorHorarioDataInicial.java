package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValidadorEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValidadoresEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValidadorEscopoPesquisaMelhorHorario(validador = ValidadoresEscopoPesquisaMelhorHorario.DATAINICIAL)
public class ValidacaoEscopoPesquisaMelhorHorarioDataInicial extends ValidacaoGenerica<EscopoPesquisaMelhorHorario> {

    public ValidacaoEscopoPesquisaMelhorHorarioDataInicial(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        getEscopoPesquisaMelhorHorario().getCPinst(CPEscopoPesquisaMelhorHorario.datahoramaximopesquisa).getValor();
        return null;
    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
        return getObjetoDoAtributo();
    }
}
