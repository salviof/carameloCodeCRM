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

@ValidadorPesquisaLead(validador = ValidadoresPesquisaLead.DATAFINAL)
public class ValidacaoPesquisaLeadDatafinal extends ValidacaoGenerica<PesquisaLead> {

    public ValidacaoPesquisaLeadDatafinal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        Date novaDatatafinal = (Date) pValor;
        if (novaDatatafinal == null) {
            throw new ErroValidacao("A data final não pode ser nula");
        }
        Date dataInicial = (Date) getPesquisaLead().getCPinst(CPPesquisaLead.datainicial).getValor();
        if (novaDatatafinal.getTime() <= dataInicial.getTime()) {
            throw new ErroValidacao("A data Final não pode ser menor que a data inicial");
        }
        getPesquisaLead().setDatafinal(novaDatatafinal);

        return null;
    }

    public PesquisaLead getPesquisaLead() {
        return getObjetoDoAtributo();
    }
}
