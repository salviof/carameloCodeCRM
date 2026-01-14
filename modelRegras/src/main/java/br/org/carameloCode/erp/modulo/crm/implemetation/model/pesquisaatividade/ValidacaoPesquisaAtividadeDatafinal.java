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

@ValidadorPesquisaAtividade(validador = ValidadoresPesquisaAtividade.DATAFINAL)
public class ValidacaoPesquisaAtividadeDatafinal extends ValidacaoGenerica<PesquisaAtividade> {

    public ValidacaoPesquisaAtividadeDatafinal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {

        Date novaDatatafinal = (Date) pValor;
        if (novaDatatafinal == null) {
            throw new ErroValidacao("A data final não pode ser nula");
        }
        Date dataInicial = (Date) getPesquisaAtividade().getCPinst(CPPesquisaLead.datainicial).getValor();
        if (novaDatatafinal.getTime() <= dataInicial.getTime()) {
            throw new ErroValidacao("A data Final não pode ser menor que a data inicial");
        }
        getPesquisaAtividade().setDatafinal(novaDatatafinal);

        return null;
    }

    public PesquisaAtividade getPesquisaAtividade() {
        return getObjetoDoAtributo();
    }
}
