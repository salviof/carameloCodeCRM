package br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesqhorariopublicado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadorEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.api.model.escopopesqhorariopublicado.ValidadoresEscopoPesqHorarioPublicado;
import br.org.carameloCode.erp.modulo.crm.implemetation.model.escopopesquisamelhorhorario.ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaQuinta;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesqHorarioPublicado;

@ValidadorEscopoPesqHorarioPublicado(validador = ValidadoresEscopoPesqHorarioPublicado.DIASEMANAQUINTA)
public class ValidacaoEscopoPesqHorarioPublicadoDiaSemanaQuinta extends ValidacaoEscopoPesquisaMelhorHorarioDiaSemanaQuinta {

    public ValidacaoEscopoPesqHorarioPublicadoDiaSemanaQuinta(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public EscopoPesqHorarioPublicado getEscopoPesqHorarioPublicado() {
        return (EscopoPesqHorarioPublicado) getObjetoDoAtributo();
    }
}
