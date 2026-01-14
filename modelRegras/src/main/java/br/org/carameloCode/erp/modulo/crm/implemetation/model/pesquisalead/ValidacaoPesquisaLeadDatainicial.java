package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.Date;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.CPPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadorPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadoresPesquisaLead;

@ValidadorPesquisaLead(validador = ValidadoresPesquisaLead.DATAINICIAL)
public class ValidacaoPesquisaLeadDatainicial extends ValidacaoGenerica<PesquisaLead> {

    public ValidacaoPesquisaLeadDatainicial(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        Date novaDataInicial = (Date) pValor;
        if (novaDataInicial == null) {
            throw new ErroValidacao("A data inicial não pode ser nula");
        }
        Date dataFinal = (Date) getPesquisaLead().getCPinst(CPPesquisaLead.datafinal).getValor();
        if (novaDataInicial.getTime() >= dataFinal.getTime()) {

            throw new ErroValidacao("A data inicial não pode ser menor que a data final");
        }
        getPesquisaLead().setDatainicial(novaDataInicial);

        return null;
    }

    public PesquisaLead getPesquisaLead() {
        return getObjetoDoAtributo();
    }
}
