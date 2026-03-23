package br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadorEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadoresEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.escopopesquisamelhorhorario.ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaQuarta;
import br.org.carameloCode.erp.modulo.agenda.entidadesJPA.escopoPesquisa.EscopoPesqHorarioPublicado;

@ValidadorEscopoPesqHorarioPublicado(validador = ValidadoresEscopoPesqHorarioPublicado.DIASEMANAQUARTA)
public class ValidacaoEscopoPesqHorarioPublicadoDiaSemanaQuarta extends ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaQuarta {

    public ValidacaoEscopoPesqHorarioPublicadoDiaSemanaQuarta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public EscopoPesqHorarioPublicado getEscopoPesqHorarioPublicado() {
        return (EscopoPesqHorarioPublicado) getObjetoDoAtributo();
    }
}
