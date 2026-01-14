package br.org.carameloCode.erp.modulo.crm.implemetation.model.sistemaerpconfiavel;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.SistemaERPConfiavel;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.ValidadorSistemaERPConfiavel;
import br.org.carameloCode.erp.modulo.crm.api.model.sistemaerpconfiavel.ValidadoresSistemaERPConfiavel;

@ValidadorSistemaERPConfiavel(validador = ValidadoresSistemaERPConfiavel.URLPUBLICAENDPOINT)
public class ValidacaoSistemaERPConfiavelUrlPublicaEndPoint extends ValidacaoGenerica<SistemaERPConfiavel> {

    public ValidacaoSistemaERPConfiavelUrlPublicaEndPoint(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        String urlStr = (String) pValor;
        try {
            URL url = new URL(urlStr);
            if (UtilCRCStringValidador.isNuloOuEmbranco(getSistemaERPConfiavel().getDominio())) {
                getSistemaERPConfiavel().setDominio(url.getHost());
            }

        } catch (MalformedURLException ex) {
            throw new ErroValidacao("Url inválida");
        }
        return null;
    }

    public SistemaERPConfiavel getSistemaERPConfiavel() {
        return getObjetoDoAtributo();
    }
}
