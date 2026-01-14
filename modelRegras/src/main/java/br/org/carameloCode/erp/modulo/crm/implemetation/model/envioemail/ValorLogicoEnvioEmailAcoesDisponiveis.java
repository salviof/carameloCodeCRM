package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.ACOESDISPONIVEIS)
public class ValorLogicoEnvioEmailAcoesDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAcoesDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getEmail().getAcoesDisponiveis() == null || getEmail().getAcoesDisponiveis().isEmpty()) {
            getEmail().setAcoesDisponiveis(new ArrayList<>());
            if (getEmail().getComoEnvioEmail().isFoiEnviado()) {
                getEmail().getAcoesDisponiveis().add(FabAcaoCRMAtendimento.EMAILS_FRM_ENCAMINHAR.getRegistro());

            } else {

                getEmail().getAcoesDisponiveis().add(FabAcaoCRMAtendimento.EMAILS_FRM_EDITAR.getRegistro());

            }

        }
        return getEmail().getAcoesDisponiveis();
    }

    public EnvioEmail getEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
