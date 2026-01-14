package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.CPEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValidadorEscopoPesquisaMelhorHorario;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesquisamelhorhorario.ValidadoresEscopoPesquisaMelhorHorario;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValidadorEscopoPesquisaMelhorHorario(validador = ValidadoresEscopoPesquisaMelhorHorario.ATIVO)
public class ValidacaoEscopoPesquisaMelhorHorarioAtivo extends ValidacaoGenerica<EscopoPesquisaMelhorHorario> {

    public ValidacaoEscopoPesquisaMelhorHorarioAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        if (getEscopoPesquisaMelhorHorario().getCPinst(CPEscopoPesquisaMelhorHorario.datahoramaximopesquisa).getValor() != null) {
            if (getEscopoPesquisaMelhorHorario().getDatahoraMaximoPesquisa().getTime() <= new Date().getTime()) {
                getEscopoPesquisaMelhorHorario().setAtivo(false);
                throw new ErroValidacao("A data-hora máxima do escopo já expirou");
            }
        } else {
            throw new ErroValidacao("Defina a data-hora maxima do escopo");
        }
        getEscopoPesquisaMelhorHorario().setAtivo((boolean) pValor);
        return null;
    }

    public EscopoPesquisaMelhorHorario getEscopoPesquisaMelhorHorario() {
        return getObjetoDoAtributo();
    }
}
