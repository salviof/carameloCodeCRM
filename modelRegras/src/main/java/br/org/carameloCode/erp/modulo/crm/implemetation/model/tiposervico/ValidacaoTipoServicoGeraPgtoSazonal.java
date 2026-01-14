package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.ValidadorTipoServico;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervico.ValidadoresTipoServico;

@ValidadorTipoServico(validador = ValidadoresTipoServico.GERAPGTOSAZONAL)
public class ValidacaoTipoServicoGeraPgtoSazonal extends ValidacaoGenerica<TipoServico> {

    public ValidacaoTipoServicoGeraPgtoSazonal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        boolean novoValor = (boolean) pValor;
        if (novoValor) {
            getTipoServico().setGeraPgtoRecorrente(false);
        }
        return null;

    }

    public TipoServico getTipoServico() {
        return getObjetoDoAtributo();
    }
}
