package br.org.carameloCode.erp.modulo.crm.implemetation.model.emailrecebido;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValorLogicoEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.api.model.emailrecebido.ValoresLogicosEmailRecebido;

@ValorLogicoEmailRecebido(calculo = ValoresLogicosEmailRecebido.ACOESDISPONIVEIS)
public class ValorLogicoEmailRecebidoAcoesDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEmailRecebidoAcoesDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEmail().getAcoesDisponiveis() == null || getEmail().getAcoesDisponiveis().isEmpty()) {
            getEmail().setAcoesDisponiveis(new ArrayList<>());
            getEmail().getAcoesDisponiveis().add(FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro());
            getEmail().getAcoesDisponiveis().add(FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER.getRegistro());
            getEmail().getAcoesDisponiveis().add(FabAcaoCRMAtendimento.EMAILS_FRM_RESPONDER_EDITANDO_MODELO.getRegistro());

        }
        return getEmail().getAcoesDisponiveis();
    }

    public EmailRecebido getEmail() {
        return (EmailRecebido) getCampoInst().getObjetoDoAtributo();
    }
}
