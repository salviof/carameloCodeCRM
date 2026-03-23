package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadorEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadoresEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario.ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaDomingo;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;

@ValidadorEscopoPesqHorarioPublicado(validador = ValidadoresEscopoPesqHorarioPublicado.DIASEMANADOMINGO)
public class ValidacaoEscopoPesqHorarioPublicadoDiaSemanaDomingo extends ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaDomingo {

    public ValidacaoEscopoPesqHorarioPublicadoDiaSemanaDomingo(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public EscopoPesqHorarioPublicado getEscopoPesqHorarioPublicado() {
        return (EscopoPesqHorarioPublicado) getObjetoDoAtributo();
    }
}
