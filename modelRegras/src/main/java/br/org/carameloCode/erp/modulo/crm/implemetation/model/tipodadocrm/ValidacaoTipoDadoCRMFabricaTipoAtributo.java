package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipodadocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.opcaoPersonalizada.OpcaoPersonalizada;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeVinculadoAEnum;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.ValidadorTipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipodadocrm.ValidadoresTipoDadoCRM;

@ValidadorTipoDadoCRM(validador = ValidadoresTipoDadoCRM.FABRICATIPOATRIBUTO)
public class ValidacaoTipoDadoCRMFabricaTipoAtributo extends ValidacaoGenerica<TipoDadoCRM> {

    public ValidacaoTipoDadoCRMFabricaTipoAtributo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public TipoDadoCRM getTipoDadoCRM() {
        return getObjetoDoAtributo();
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (pValor == null) {
            return null;
        }

        FabTipoAtributoObjeto tipoAtributo = null;
        if (pValor instanceof ComoFabrica) {
            tipoAtributo = (FabTipoAtributoObjeto) pValor;
        }
        if (pValor instanceof ComoEntidadeVinculadoAEnum) {
            tipoAtributo = (FabTipoAtributoObjeto) ((ComoEntidadeVinculadoAEnum) pValor).getEnumVinculado();
        }

        switch (tipoAtributo) {
            case OBJETO_DE_UMA_LISTA:
                getTipoDadoCRM().setNomeClasseAtributoDeclarado(OpcaoPersonalizada.class.getSimpleName());
                break;
            default:
                getTipoDadoCRM().setNomeClasseAtributoDeclarado(null);
                break;

        }
        return null;
    }

    public TipoDadoCRM getTipoDado() {
        return (TipoDadoCRM) getCampoInstanciado().getObjetoDoAtributo();
    }

}
