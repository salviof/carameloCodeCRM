package br.org.carameloCode.erp.modulo.crm.implemetation.model.integracaolink;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.IntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.integracaolink.ValorLogicoIntegracaoLink;
import br.org.carameloCode.erp.modulo.crm.api.model.integracaolink.ValoresLogicosIntegracaoLink;

@ValorLogicoIntegracaoLink(calculo = ValoresLogicosIntegracaoLink.URL)
public class ValorLogicoIntegracaoLinkUrl extends ValorLogicoCalculoGenerico {

    public ValorLogicoIntegracaoLinkUrl(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valordefinido = false;

    @Override
    public Object getValor(Object... pEntidade) {
        String integradorStr = getIntegracao().getTipoDado().getNomeClasseLogica();
        if (getIntegracao().getCodigoPessoa() != null) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            if (!valordefinido) {
                try {
                    Pessoa pesoa = UtilSBPersistencia.getRegistroByID(Pessoa.class, getIntegracao().getCodigoPessoa(), em);
                    DadoCRM dado = new DadoCRM();
                    dado.setTipoDadoCRM(getIntegracao().getTipoDado());
                    dado.setProspectoEmpresa(pesoa);
                    getIntegracao().setUrl((String) dado.getCampoInstanciado().getValor());
                } finally {
                    UtilSBPersistencia.fecharEM(em);
                }

            }
        }

        return getIntegracao().getUrl();
    }

    public IntegracaoLink getIntegracao() {
        return (IntegracaoLink) getCampoInst().getObjetoDoAtributo();
    }
}
