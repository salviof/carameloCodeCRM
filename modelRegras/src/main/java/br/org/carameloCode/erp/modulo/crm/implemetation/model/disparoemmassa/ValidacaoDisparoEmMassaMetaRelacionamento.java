package br.org.carameloCode.erp.modulo.crm.implemetation.model.disparoemmassa;

import br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa.CPDisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento.CPMetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.DisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa.ValidadorDisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa.ValidadoresDisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

import java.util.List;

import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.FabTipoWidgetFormulario;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;

@ValidadorDisparoEmMassa(validador = ValidadoresDisparoEmMassa.METARELACIONAMENTO)
public class ValidacaoDisparoEmMassaMetaRelacionamento extends ValidacaoGenerica<DisparoEmMassa> {

    public ValidacaoDisparoEmMassaMetaRelacionamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        return FabTipoWidgetFormulario.getCampos(getDisparoEmMassa().getCPinst(CPDisparoEmMassa.relacionamentos));
    }

    public DisparoEmMassa getDisparoEmMassa() {
        return getObjetoDoAtributo();
    }
}
