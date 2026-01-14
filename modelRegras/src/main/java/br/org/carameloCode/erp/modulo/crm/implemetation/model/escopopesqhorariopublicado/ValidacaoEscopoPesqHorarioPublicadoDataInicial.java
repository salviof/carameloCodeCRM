package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadorEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadoresEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario.ValidacaoEscopoPesquisaMelhorHorarioDataInicial;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;

@ValidadorEscopoPesqHorarioPublicado(validador = ValidadoresEscopoPesqHorarioPublicado.DATAINICIAL)
public class ValidacaoEscopoPesqHorarioPublicadoDataInicial extends ValidacaoGenerica<EscopoPesqHorarioPublicado> {

    public ValidacaoEscopoPesqHorarioPublicadoDataInicial(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
        validacaoSimples = new ValidacaoEscopoPesquisaMelhorHorarioDataInicial(pCampo);
    }
    private final ValidacaoEscopoPesquisaMelhorHorarioDataInicial validacaoSimples;

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return validacaoSimples.validar(pValor);
    }

    public EscopoPesqHorarioPublicado getEscopoPesqHorarioPublicado() {
        return getObjetoDoAtributo();
    }
}
