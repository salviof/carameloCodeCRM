package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadorPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoafisica.ValidadoresPessoaFisica;

@ValidadorPessoaFisica(validador = ValidadoresPessoaFisica.RELACIONAMENTO)
public class ValidacaoPessoaFisicaRelacionamento extends ValidacaoGenerica<PessoaFisica> {

    public ValidacaoPessoaFisicaRelacionamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return null;
    }

    public PessoaFisica getPessoaFisica() {
        return getObjetoDoAtributo();
    }
}
