package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.POSSSUICOLETADADO)
public class ValorLogicoAtividadeCRMPosssuiColetaDado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMPosssuiColetaDado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean definido = false;

    @Override
    public Object getValor(Object... pEntidade) {
        if (!definido) {
            try {
                getAtividade().setPosssuiColetaDado(false);
                if (!getAtividade().getTipoAtividade().getTiposDadosColetarNaAtividade().isEmpty()) {
                    getAtividade().setPosssuiColetaDado(true);
                } else {
                    if (getAtividade().getTipoAtividade().getRelacionamentoGerado() != null) {
                        if (!getAtividade().getTipoAtividade().getRelacionamentoGerado().getDadosNescessarios().isEmpty()) {
                            getAtividade().setPosssuiColetaDado(true);
                        }
                    }
                }
            } finally {
                definido = true;
            }
        }
        return getAtividade().isPosssuiColetaDado();
    }

    public AtividadeCRM getAtividade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
