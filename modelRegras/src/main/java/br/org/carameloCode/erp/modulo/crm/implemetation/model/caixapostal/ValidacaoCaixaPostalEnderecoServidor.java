package br.org.carameloCode.erp.modulo.crm.implemetation.model.caixapostal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.caixaPostal.CaixaPostal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostal.ValidadorCaixaPostal;
import br.org.carameloCode.erp.modulo.crm.api.model.caixapostal.ValidadoresCaixaPostal;

@ValidadorCaixaPostal(validador = ValidadoresCaixaPostal.ENDERECOSERVIDOR)
public class ValidacaoCaixaPostalEnderecoServidor extends ValidacaoGenerica<CaixaPostal> {

    public ValidacaoCaixaPostalEnderecoServidor(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        String enderecoServidor = (String) pValor;
        if (enderecoServidor == null) {
            throw new ErroValidacao("O caminho do servidor não pode ser nulo");
        }
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(enderecoServidor);
            getCaixaPostal().setEnderecoServidor(enderecoServidor);
        } catch (UnknownHostException ex) {
            throw new ErroValidacao("O caminho do servidor [" + enderecoServidor + "] não leva a nenhum servidor da internet");
        }

        return null;
    }

    public CaixaPostal getCaixaPostal() {
        return getObjetoDoAtributo();
    }
}
