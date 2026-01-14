package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadorContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadoresContatoProspecto;

@ValidadorContatoProspecto(validador = ValidadoresContatoProspecto.NOME)
public class ValidacaoContatoProspectoNome extends ValidacaoGenerica<ContatoProspecto> {

    public ValidacaoContatoProspectoNome(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        return new ArrayList<>();
    }

    public ContatoProspecto getContatoProspecto() {
        return getObjetoDoAtributo();
    }
}
