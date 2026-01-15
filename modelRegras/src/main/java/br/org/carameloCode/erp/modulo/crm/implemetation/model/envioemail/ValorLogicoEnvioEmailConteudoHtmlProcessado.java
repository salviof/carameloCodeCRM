package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.emailcrm.CPEmailCrm;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.CONTEUDOHTMLPROCESSADO)
public class ValorLogicoEnvioEmailConteudoHtmlProcessado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailConteudoHtmlProcessado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        setValorPorReflexao(getEmail().getCampoInstanciadoByNomeOuAnotacao(CPEmailCrm.texto).getValor());
        return getEmail().getConteudoHtmlProcessado();
    }

    public EnvioEmail getEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
