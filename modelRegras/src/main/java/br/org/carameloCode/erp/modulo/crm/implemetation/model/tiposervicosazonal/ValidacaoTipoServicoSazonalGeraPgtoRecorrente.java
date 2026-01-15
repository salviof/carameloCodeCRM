package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervicosazonal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.servico.TipoServicoSazonal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal.ValidadorTipoServicoSazonal;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicosazonal.ValidadoresTipoServicoSazonal;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico.ValidacaoTipoServicoGeraPgtoRecorrente;

@ValidadorTipoServicoSazonal(validador = ValidadoresTipoServicoSazonal.GERAPGTORECORRENTE)
public class ValidacaoTipoServicoSazonalGeraPgtoRecorrente extends ValidacaoTipoServicoGeraPgtoRecorrente {

    public ValidacaoTipoServicoSazonalGeraPgtoRecorrente(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TipoServicoSazonal getTipoServicoSazonal() {
        return (TipoServicoSazonal) getObjetoDoAtributo();
    }
}
