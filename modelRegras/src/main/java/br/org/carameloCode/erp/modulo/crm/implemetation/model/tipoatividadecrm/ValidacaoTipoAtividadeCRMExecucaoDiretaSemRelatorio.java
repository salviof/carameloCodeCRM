package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValidadorTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValidadoresTipoAtividadeCRM;

@ValidadorTipoAtividadeCRM(validador = ValidadoresTipoAtividadeCRM.EXECUCAODIRETASEMRELATORIO)
public class ValidacaoTipoAtividadeCRMExecucaoDiretaSemRelatorio extends ValidacaoGenerica<TipoAtividadeCRM> {

    public ValidacaoTipoAtividadeCRMExecucaoDiretaSemRelatorio(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        Boolean valor = (Boolean) pValor;
        if (valor != null) {
            if (valor == true && getTipoAtividadeCRM().getModeloEmail() != null) {
                throw new ErroValidacao("Existe um modelo de e-mail vinculado a atividade");
            }
        }

        return null;
    }

    public TipoAtividadeCRM getTipoAtividadeCRM() {
        return getObjetoDoAtributo();
    }
}
