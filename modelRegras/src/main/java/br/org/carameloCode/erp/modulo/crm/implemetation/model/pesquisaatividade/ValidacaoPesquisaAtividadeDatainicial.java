package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValidadorPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValidadoresPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;

@ValidadorPesquisaAtividade(validador = ValidadoresPesquisaAtividade.DATAINICIAL)
public class ValidacaoPesquisaAtividadeDatainicial extends ValidacaoGenerica<PesquisaAtividade> {

    public ValidacaoPesquisaAtividadeDatainicial(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        Date novaDataInicial = (Date) pValor;
        if (novaDataInicial == null) {
            throw new ErroValidacao("A data inicial não pode ser nula");
        }
        Date dataFinal = (Date) getPesquisaAtividade().getCPinst(CPPesquisaLead.datafinal).getValor();
        if (novaDataInicial.getTime() >= dataFinal.getTime()) {

            throw new ErroValidacao("A data inicial não pode ser menor que a data final");
        }
        getPesquisaAtividade().setDatainicial(novaDataInicial);

        return null;
    }

    public PesquisaAtividade getPesquisaAtividade() {
        return getObjetoDoAtributo();
    }
}
