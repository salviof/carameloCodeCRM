package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import javax.net.ssl.HttpsURLConnection;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValorLogicoPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValoresLogicosPessoaJuridica;

@ValorLogicoPessoaJuridica(calculo = ValoresLogicosPessoaJuridica.SITE)
public class ValorLogicoPessoaJuridicaSite extends ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaJuridicaSite(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        try {
            String site = (String) getPessoa().getSite();
            if (UtilCRCStringValidador.isNuloOuEmbranco(site)) {
                return site;
            }
            if (!site.startsWith("http://")
                    && !site.startsWith("https://")) {

                site = site.replace(" ", "");
                String dominio = site.replace("http://", "");
                dominio = dominio.replace("https://", "");
                InetAddress inetAddress = InetAddress.getByName(dominio);
                if (dominio.indexOf('/') >= 0) {
                    dominio = dominio.substring(0, dominio.indexOf('/'));
                }

                try {
                    URL urlHttps = new URL("https://" + site);
                    HttpsURLConnection con = (HttpsURLConnection) urlHttps.openConnection();
                    con.connect();
                    site = "https://" + site;
                } catch (IOException t) {

                    site = "http://" + site;
                }

            }
            getPessoa().setSite(site);
            return site;
        } catch (UnknownHostException ex) {
            // se site não existe, ignora
        }
        return getPessoa().getSite();

    }

    public PessoaJuridica getPessoa() {
        return (PessoaJuridica) getCampoInst().getObjetoDoAtributo();
    }
}
