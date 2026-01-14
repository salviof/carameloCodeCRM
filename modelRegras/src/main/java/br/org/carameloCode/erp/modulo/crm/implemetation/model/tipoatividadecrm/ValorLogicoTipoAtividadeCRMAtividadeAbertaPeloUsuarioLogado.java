package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValorLogicoTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValoresLogicosTipoAtividadeCRM;

@ValorLogicoTipoAtividadeCRM(calculo = ValoresLogicosTipoAtividadeCRM.ATIVIDADEABERTAPELOUSUARIOLOGADO)
public class ValorLogicoTipoAtividadeCRMAtividadeAbertaPeloUsuarioLogado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoAtividadeCRMAtividadeAbertaPeloUsuarioLogado(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        //AtividadeCRM  atividadeAbertaPeloUsuarioLogado = (AtividadeCRM) CalculosTipoAtividadeCRM.ATIVIDADE_ABERTA_PELO_USUARIO_LOGADO.getValor(getTipoAtividadeCRM());
        return getTipoAtividadeCRM().getAtividadeAbertaPeloUsuarioLogado();
    }

    public TipoAtividadeCRM getTipoAtividadeCRM() {
        return (TipoAtividadeCRM) getCampoInst().getObjetoDoAtributo();
    }
}
