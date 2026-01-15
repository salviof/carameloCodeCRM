package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervicosazonal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServicoSazonal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal.ValidadorTipoServicoSazonal;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal.ValidadoresTipoServicoSazonal;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico.ValidacaoTipoServicoGeraPgtoSazonal;

@ValidadorTipoServicoSazonal(validador = ValidadoresTipoServicoSazonal.GERAPGTOSAZONAL)
public class ValidacaoTipoServicoSazonalGeraPgtoSazonal extends ValidacaoTipoServicoGeraPgtoSazonal {

    public ValidacaoTipoServicoSazonalGeraPgtoSazonal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TipoServicoSazonal getTipoServicoSazonal() {
        return (TipoServicoSazonal) getObjetoDoAtributo();
    }
}
