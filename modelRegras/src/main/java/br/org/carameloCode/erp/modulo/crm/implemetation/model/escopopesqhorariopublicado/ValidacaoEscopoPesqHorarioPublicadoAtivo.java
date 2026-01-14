package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadorEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadoresEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario.ValidacaoEscopoPesquisaMelhorHorarioAtivo;

@ValidadorEscopoPesqHorarioPublicado(validador = ValidadoresEscopoPesqHorarioPublicado.ATIVO)
public class ValidacaoEscopoPesqHorarioPublicadoAtivo extends ValidacaoEscopoPesquisaMelhorHorarioAtivo {

    public ValidacaoEscopoPesqHorarioPublicadoAtivo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

}
