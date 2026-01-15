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

@ValidadorPessoa(validador = ValidadoresPessoa.USUARIOSRESPONSAVEIS)
public class ValidacaoPessoaUsuariosResponsaveis extends ValidacaoGenerica<Pessoa> {

    public ValidacaoPessoaUsuariosResponsaveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (getPessoa().getUsuariosResponsaveis() != null && getPessoa().getUsuariosResponsaveis().size() > 5) {
            throw new ErroValidacao("Apenas 5 responsáveis são permitidos");
        }
        return new ArrayList<>();
    }

    public Pessoa getPessoa() {
        return getObjetoDoAtributo();
    }
}
