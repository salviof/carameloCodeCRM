package br.org.carameloCode.erp.modulo.crm.implemetation.model.disparoemmassa;

import br.org.carameloCode.erp.modulo.crm.api.model.metarelacionamento.CPMetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.disparoEmMassa.DisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa.ValidadorDisparoEmMassa;
import br.org.carameloCode.erp.modulo.crm.api.model.disparoemmassa.ValidadoresDisparoEmMassa;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import java.util.ArrayList;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
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
        if(getDisparoEmMassa().getMetaRelacionamento() != null){
            if(getDisparoEmMassa().getMetaRelacionamento().getTiposRelacionamento() != null){
                if(!getDisparoEmMassa().getRelacionamentos().equals(getDisparoEmMassa().getMetaRelacionamento().getTiposRelacionamento())){
                    throw new ErroValidacao("Os tipos de relacionamento selecionados não correspondem aos tipos de relacionamento definidos na meta de relacionamento");
                }
            }
        }

        MetaRelacionamento valor = (MetaRelacionamento) pValor;
        if(valor != null){
            TipoRelacionamento relacionamento = (TipoRelacionamento) getDisparoEmMassa().getMetaRelacionamento().getCPinst(CPMetaRelacionamento.tiposrelacionamento).getValor();
            relacionamento.setMetaRelacionamento(valor);
            return FabTipoWidgetFormulario.getCampos(relacionamento.getCPinst(CPMetaRelacionamento.tiposrelacionamento));
        }
        return null;
    }

    public DisparoEmMassa getDisparoEmMassa() {
        return getObjetoDoAtributo();
    }
}