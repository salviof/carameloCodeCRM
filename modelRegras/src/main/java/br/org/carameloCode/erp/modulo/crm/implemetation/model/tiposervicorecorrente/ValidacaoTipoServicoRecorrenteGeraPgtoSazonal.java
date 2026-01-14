package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervicorecorrente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.servico.TipoServicoRecorrente;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicorecorrente.ValidadorTipoServicoRecorrente;
import br.org.carameloCode.erp.modulo.crm.api.model.tiposervicorecorrente.ValidadoresTipoServicoRecorrente;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.tiposervico.ValidacaoTipoServicoGeraPgtoSazonal;

@ValidadorTipoServicoRecorrente(validador = ValidadoresTipoServicoRecorrente.GERAPGTOSAZONAL)
public class ValidacaoTipoServicoRecorrenteGeraPgtoSazonal extends ValidacaoTipoServicoGeraPgtoSazonal {

    public ValidacaoTipoServicoRecorrenteGeraPgtoSazonal(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TipoServicoRecorrente getTipoServicoRecorrente() {
        return (TipoServicoRecorrente) getObjetoDoAtributo();
    }
}
