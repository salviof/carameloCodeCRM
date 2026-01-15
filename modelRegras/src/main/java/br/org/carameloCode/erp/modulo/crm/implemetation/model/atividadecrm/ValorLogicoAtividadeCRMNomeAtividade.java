package br.org.carameloCode.erp.modulo.crm.implemetation.model.atividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValorLogicoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.atividadecrm.ValoresLogicosAtividadeCRM;

@ValorLogicoAtividadeCRM(calculo = ValoresLogicosAtividadeCRM.NOMEATIVIDADE)
public class ValorLogicoAtividadeCRMNomeAtividade
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoAtividadeCRMNomeAtividade(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        try {
            StringBuilder builderNomeAtividade = new StringBuilder();
            String tipostr = getAtiviade().getTipoAtividade().getNomeAcao();
            builderNomeAtividade.append(tipostr);
            String dataHoraRealizacaoSTR = "";
            if (getAtiviade().getDataHoraRealizacao() != null) {
                dataHoraRealizacaoSTR = UtilCRCDataHora.getDataHoraString(getAtiviade().getDataHoraRealizacao(), UtilCRCDataHora.FORMATO_TEMPO.HORA_USUARIO);
            } else {
                if (getAtiviade().getDataHoraInicioAtividade() != null) {
                    dataHoraRealizacaoSTR = UtilCRCDataHora.getDataHoraString(getAtiviade().getDataHoraInicioAtividade(), UtilCRCDataHora.FORMATO_TEMPO.HORA_USUARIO);
                }
            }
            builderNomeAtividade.append(dataHoraRealizacaoSTR);
            getAtiviade().setNomeAtividade(builderNomeAtividade.toString());
        } catch (Throwable t) {
            System.out.println("Erro gerando nome da anotação");
        }
        return getAtiviade().getNomeAtividade();
    }

    public AtividadeCRM getAtiviade() {
        return (AtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
