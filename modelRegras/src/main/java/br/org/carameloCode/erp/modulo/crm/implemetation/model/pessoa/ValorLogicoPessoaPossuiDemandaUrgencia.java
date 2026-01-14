package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.POSSUIDEMANDAURGENCIA)
public class ValorLogicoPessoaPossuiDemandaUrgencia
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaPossuiDemandaUrgencia(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getPessoaJuridica().getRelacionamento().getTempoAceitavelResolucao() != 0) {
            long horaspassadas = UtilCRCDataHora.intervaloTempoHoras((Date) getPessoaJuridica().getCPinst("dataHoraMudancaRelacionamento").getValor(), new Date());
            if (horaspassadas >= getPessoaJuridica().getRelacionamento().getTempoAceitavelResolucao()) {
                getPessoaJuridica().setPossuiDemandaUrgencia(true);
            }
        } else {
            getPessoaJuridica().setPossuiDemandaUrgencia(false);
        }
        return getPessoaJuridica().isPossuiDemandaUrgencia();
    }

    public Pessoa getPessoaJuridica() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
