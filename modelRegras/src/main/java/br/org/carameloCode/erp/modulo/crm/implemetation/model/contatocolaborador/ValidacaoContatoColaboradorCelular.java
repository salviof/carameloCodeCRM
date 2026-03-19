package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatocolaborador;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValidadorContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.api.model.contatocolaborador.ValidadoresContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto.ValidacaoContatoProspectoCelular;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValidadorContatoColaborador(validador = ValidadoresContatoColaborador.CELULAR)
public class ValidacaoContatoColaboradorCelular
        extends
        ValidacaoContatoProspectoCelular {

    public ValidacaoContatoColaboradorCelular(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ContatoColaborador getContatoColaborador() {
        return (ContatoColaborador) getObjetoDoAtributo();
    }
}
