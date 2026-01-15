package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadorPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadoresPessoa;

@ValidadorPessoa(validador = ValidadoresPessoa.RELACIONAMENTO)
public class ValidacaoPessoaRelacionamento extends ValidacaoGenerica<Pessoa> {

    public ValidacaoPessoaRelacionamento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(java.lang.Object o) throws ErroValidacao {

        return new ArrayList<>();
    }

    public Pessoa getPessoa() {
        return getObjetoDoAtributo();
    }
}
