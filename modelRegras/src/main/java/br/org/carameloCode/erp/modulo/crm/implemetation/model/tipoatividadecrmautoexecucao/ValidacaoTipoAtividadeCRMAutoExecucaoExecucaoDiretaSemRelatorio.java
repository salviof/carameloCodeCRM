package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrmautoexecucao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais.TipoAtividadeCRMAutoExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrmautoexecucao.ValidadorTipoAtividadeCRMAutoExecucao;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrmautoexecucao.ValidadoresTipoAtividadeCRMAutoExecucao;

@ValidadorTipoAtividadeCRMAutoExecucao(validador = ValidadoresTipoAtividadeCRMAutoExecucao.EXECUCAODIRETASEMRELATORIO)
public class ValidacaoTipoAtividadeCRMAutoExecucaoExecucaoDiretaSemRelatorio extends ValidacaoGenerica<TipoAtividadeCRMAutoExecucao> {

    public ValidacaoTipoAtividadeCRMAutoExecucaoExecucaoDiretaSemRelatorio(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList<>();
    }

    public TipoAtividadeCRMAutoExecucao getTipoAtividadeCRMAutoExecucao() {
        return getObjetoDoAtributo();
    }
}
