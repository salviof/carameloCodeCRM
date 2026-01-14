package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoajuridica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadorPessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoajuridica.ValidadoresPessoaJuridica;

@ValidadorPessoaJuridica(validador = ValidadoresPessoaJuridica.SITE)
public class ValidacaoPessoaJuridicaSite extends ValidacaoGenerica<PessoaJuridica> {

    public ValidacaoPessoaJuridicaSite(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
            if (getCampoInstanciado().isObrigatorio()) {
                throw new ErroValidacao("O site não pode ser nulo");
            } else {
                return null;
            }
        }
        //  if (!FabTipoValidacaoUnitaria.REGEX.getValidador(getCampoInstanciado()).isValorValido(pValor)) {
        //      throw new ErroValidacao("O e-mail não parece válido");
        //  }
        //getPessoa().getComoPessoaJuridica().setSite(pValor.toString());
        String site = (String) pValor;

        URL url = null;
        try {
            url = new URL(site);
        } catch (MalformedURLException ex) {
            throw new ErroValidacao("O endereço  " + site + " não é válido");
        }
        String dominio = url.getHost();

        try {
            InetAddress inetAddress = InetAddress.getByName(dominio);
        } catch (UnknownHostException ex) {
            throw new ErroValidacao("O domínio [" + dominio + "] não está registrado na internet");
        }

        if (site.startsWith("https://")) {
            try {

                URL urlHttps = new URL(site);
                HttpURLConnection con = UtilSBApiRestClient.getHTTPConexaoPadrao(site, true);
                con.connect();
                site = "https://" + site;
            } catch (SSLHandshakeException t) {
                SBCore.enviarAvisoAoUsuario("O endereço " + site + " falhou na validação do ssl");
                //   throw new ErroValidacao("O endereço " + site + "não foi encontrado");
            } catch (IOException ex) {
                throw new ErroValidacao("O endereço " + site + "não foi encontrado");
            }
        }

        return null;
    }

    public Pessoa getPessoa() {
        return getObjetoDoAtributo();
    }
}
