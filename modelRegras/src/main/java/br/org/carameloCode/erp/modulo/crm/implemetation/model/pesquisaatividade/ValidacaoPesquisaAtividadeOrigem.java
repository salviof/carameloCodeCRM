package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValidadorPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValidadoresPesquisaAtividade;

@ValidadorPesquisaAtividade(validador = ValidadoresPesquisaAtividade.ORIGEM)
public class ValidacaoPesquisaAtividadeOrigem extends ValidacaoGenerica<PesquisaAtividade> {

    public ValidacaoPesquisaAtividadeOrigem(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        OrigemProspecto origem = (OrigemProspecto) pValor;
        getPesquisaAtividade().setOrigem(origem);
        return null;
    }

    public PesquisaAtividade getPesquisaAtividade() {
        return getObjetoDoAtributo();
    }
}
