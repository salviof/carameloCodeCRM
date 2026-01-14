package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatopessoal;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoPessoal.ContatoPessoal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValidadorContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.api.model.contatopessoal.ValidadoresContatoPessoal;

@ValidadorContatoPessoal(validador = ValidadoresContatoPessoal.NOME)
public class ValidacaoContatoPessoalNome extends ValidacaoGenerica<ContatoPessoal> {

    public ValidacaoContatoPessoalNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList<>();
    }

    public ContatoPessoal getContatoPessoal() {
        return getObjetoDoAtributo();
    }
}
