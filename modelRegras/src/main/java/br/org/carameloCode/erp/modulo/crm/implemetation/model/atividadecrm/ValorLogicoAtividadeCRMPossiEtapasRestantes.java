package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.CPAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.POSSIETAPASRESTANTES)
public class ValorLogicoAtividadeCRMPossiEtapasRestantes
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMPossiEtapasRestantes(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        List<ItfAcaoFormulario> etapasRestantes = (List<ItfAcaoFormulario>) getAtividade().getCPinst(CPAtividadeCRM.listabloqueiosrestantes).getValor();
        getAtividade().setPossiEtapasRestantes(!etapasRestantes.isEmpty());

        return getAtividade().isPossiEtapasRestantes();

    }

    public AtividadeCRM getAtividade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }

}
