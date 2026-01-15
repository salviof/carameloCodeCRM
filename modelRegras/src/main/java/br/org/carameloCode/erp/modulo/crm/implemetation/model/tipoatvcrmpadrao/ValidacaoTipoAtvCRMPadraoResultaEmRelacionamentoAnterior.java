package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatvcrmpadrao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtvCRMPadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvcrmpadrao.ValidadorTipoAtvCRMPadrao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatvcrmpadrao.ValidadoresTipoAtvCRMPadrao;

@ValidadorTipoAtvCRMPadrao(validador = ValidadoresTipoAtvCRMPadrao.RESULTAEMRELACIONAMENTOANTERIOR)
public class ValidacaoTipoAtvCRMPadraoResultaEmRelacionamentoAnterior extends ValidacaoGenerica<TipoAtvCRMPadrao> {

    public ValidacaoTipoAtvCRMPadraoResultaEmRelacionamentoAnterior(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList<>();
    }

    public TipoAtvCRMPadrao getTipoAtvCRMPadrao() {
        return getObjetoDoAtributo();
    }
}
