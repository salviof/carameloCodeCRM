package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoafisica;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoaFisica.ContatoPessoaFisica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValidadorContatoPessoaFisica;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoafisica.ValidadoresContatoPessoaFisica;

@ValidadorContatoPessoaFisica(validador = ValidadoresContatoPessoaFisica.NOME)
public class ValidacaoContatoPessoaFisicaNome extends ValidacaoGenerica<ContatoPessoaFisica> {

    public ValidacaoContatoPessoaFisicaNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList<>();
    }

    public ContatoPessoaFisica getContatoPessoaFisica() {
        return getObjetoDoAtributo();
    }
}
