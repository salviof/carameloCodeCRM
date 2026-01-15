package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValidadorTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValidadoresTipoAtividadeCRM;

@ValidadorTipoAtividadeCRM(validador = ValidadoresTipoAtividadeCRM.MODELOEMAIL)
public class ValidacaoTipoAtividadeCRMModeloEmail extends ValidacaoGenerica<TipoAtividadeCRM> {

    public ValidacaoTipoAtividadeCRMModeloEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (getTipoAtividadeCRM().isExecucaoDiretaSemRelatorio()) {

            if (pValor != null) {
                throw new ErroValidacao("Desmarque este tipo de atividade como uma atividade que conclui automático sem relatório");
            }
        }
        return null;
    }

    public TipoAtividadeCRM getTipoAtividadeCRM() {
        return getObjetoDoAtributo();
    }
}
