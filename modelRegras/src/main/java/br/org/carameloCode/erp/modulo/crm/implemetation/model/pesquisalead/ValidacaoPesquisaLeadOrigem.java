package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.FabTipoPesquisaLeads;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadorPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValidadoresPesquisaLead;

@ValidadorPesquisaLead(validador = ValidadoresPesquisaLead.ORIGEM)
public class ValidacaoPesquisaLeadOrigem extends ValidacaoGenerica<PesquisaLead> {

    public ValidacaoPesquisaLeadOrigem(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        OrigemProspecto origem = (OrigemProspecto) pValor;
        if (origem instanceof OrigemProspectoPrivado) {
            if (((OrigemProspectoPrivado) origem).getUsuarioDono().equals(SBCore.getUsuarioLogado())) {
                getPesquisaLead().setTipoPesquisa(FabTipoPesquisaLeads.ORIGEM_PRIVADA.getRegistro());
            } else {
                getPesquisaLead().setTipoPesquisa(FabTipoPesquisaLeads.ORIGEM_PRIVADA.getRegistro());
            }

        } else {
            getPesquisaLead().setTipoPesquisa(FabTipoPesquisaLeads.ORIGEM_PUBLICAS.getRegistro());
        }
        return null;
    }

    public PesquisaLead getPesquisaLead() {
        return getObjetoDoAtributo();
    }
}
