package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValidadorTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValidadoresTipoAtividadeCRM;

@ValidadorTipoAtividadeCRM(validador = ValidadoresTipoAtividadeCRM.RESULTAEMRELACIONAMENTOANTERIOR)
public class ValidacaoTipoAtividadeCRMResultaEmRelacionamentoAnterior extends ValidacaoGenerica<TipoAtividadeCRM> {

    public ValidacaoTipoAtividadeCRMResultaEmRelacionamentoAnterior(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(java.lang.Object o) throws ErroValidacao {
        //nada para validar por enquanto
        return null;
    }

    public TipoAtividadeCRM getTipoAtividadeCRM() {
        return getObjetoDoAtributo();
    }
}
